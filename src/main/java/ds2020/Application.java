package ds2020;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.*;
import ds2020.entities.*;
import ds2020.repositories.ActivityRepository;
import ds2020.repositories.MedicineTakingTrackerRepository;
import ds2020.repositories.NotTakenMedTrackerRepository;
import ds2020.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
@Validated
public class Application extends SpringBootServletInitializer {

    private final static String QUEUE_NAME = "assig2-sd-queue";
    private final ActivityRepository activityRepository;

    private static final String Q_NAME = "assig3_queue";
    private List<Prescription> prescriptions = new ArrayList<>();
    private PrescriptionRepository prescriptionRepository;
    private MedicineTakingTrackerRepository medicineTakingTrackerRepository;
    private NotTakenMedTrackerRepository notTakenMedTrackerRepository;

    @Autowired
    public Application(ActivityRepository activityRepository, PrescriptionRepository prescriptionRepository, MedicineTakingTrackerRepository medicineTakingTrackerRepository,
                       NotTakenMedTrackerRepository notTakenMedTrackerRepository) {
        this.activityRepository = activityRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.medicineTakingTrackerRepository = medicineTakingTrackerRepository;
        this.notTakenMedTrackerRepository = notTakenMedTrackerRepository;
    }

    @Bean
    public void receive() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://rsvouwnv:AUQf84tBGLs3z5idn8YEkOz2Y26wUaJp@shark.rmq.cloudamqp.com/rsvouwnv");

        factory.setRequestedHeartbeat(30);
        factory.setConnectionTimeout(30000);
        //factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            String message = new String(delivery.getBody(), "UTF-8");
            DateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
            Integer patientId;
            String start, end, activity;

            Activity a = new Activity();
            patientId = Integer.parseInt(message.substring(0, 1));
            start = message.substring(2, 21);
            end = message.substring(23, 42);
            activity = message.substring(42, message.length());
            activity = activity.replaceAll("\\s+", "");
            Date startDate = null;

            Date endDate = null;
            try {
                startDate = format.parse(start);
                endDate = format.parse(end);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            a.setPatientId(Long.valueOf(patientId));
            a.setStartTime(startDate);
            a.setEndTime(endDate);
            a.setActivity(activity);

            System.out.println(" [x] Received '" + a.toString() + "'");
            //System.out.println(a.toString());
            long time = a.getEndTime().getTime() - a.getStartTime().getTime();
            if (a.getActivity().equals("Sleeping") && time > 25200000) {
                activityRepository.save(a);
                System.out.println("*** Saved " + a.toString() + " ***");
            } else if (a.getActivity().equals("Leaving") && time > 18000000) {
                activityRepository.save(a);
                System.out.println("*** Saved " + a.toString() + " ***");
            } else if (a.getActivity().equals("Toileting") || a.getActivity().equals("Grooming") || a.getActivity().equals("Showering")) {
                if (time > 1800000) {
                    activityRepository.save(a);
                    System.out.println("*** Saved " + a.toString() + " ***");
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

    @Bean
    public void startServer() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://rsvouwnv:AUQf84tBGLs3z5idn8YEkOz2Y26wUaJp@shark.rmq.cloudamqp.com/rsvouwnv");

        try {
            Connection conn = factory.newConnection();
            Channel chn = conn.createChannel();
            chn.queueDeclare(Q_NAME, false, false, false, null);
            chn.queuePurge(Q_NAME); chn.basicQos(1);

            System.out.println("Listening to RPC requests");

            Object monitor = new Object();
            DeliverCallback callback = (cons, deliv) -> {
                AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().correlationId(deliv.getProperties().getCorrelationId()).build();

                try {
                    String body = new String(deliv.getBody(), "UTF-8");
                    String[] bodyParts = body.split(",");
                    String msg = bodyParts[0];

                    if (msg.equals("download")) {
                        System.out.println("Fetching prescriptions");
                        List<RPCObject> objects = new ArrayList<>();
                        this.prescriptions = prescriptionRepository.findAll();

                        for (Prescription p : prescriptions) {
                            for (Medicine m : p.getMedicine()) {
                                objects.add(new RPCObject(m.getName(), p.getPrescriptionId(), p.getPatient().getPatientId(), p.getStartTime(), p.getEndTime()));
                            }
                        }

                        for (RPCObject o : objects) {
                            System.out.println(o.toString());
                        }
                        Gson g = new Gson();
                        Type objToBeSent = new TypeToken<ArrayList<RPCObject>>(){}.getType();
                        String message = g.toJson(objects, objToBeSent);

                        chn.basicPublish("", deliv.getProperties().getReplyTo(), properties, message.getBytes());
                        chn.basicAck(deliv.getEnvelope().getDeliveryTag(), false);
                        synchronized (monitor) {
                            monitor.notify();
                        }
                    } else if (msg.equals("taken")) {
                        String med_name = bodyParts[1];
                        Long prescription_id = Long.parseLong(bodyParts[2]);
                        Long patient_id = Long.parseLong(bodyParts[3]);
                        Date startTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.UK).parse(bodyParts[4]);
                        Date endTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.UK).parse(bodyParts[5]);
                        startTime.setHours(startTime.getHours() + 2);
                        endTime.setHours(endTime.getHours() + 2);
                        MedicineTakingTracker medicineTakingTracker = new MedicineTakingTracker(med_name, prescription_id, patient_id, startTime, endTime);
                        System.out.println(medicineTakingTracker.toString());
                        medicineTakingTrackerRepository.save(medicineTakingTracker);

                        chn.basicPublish("", deliv.getProperties().getReplyTo(), properties, "taken med saved".getBytes());
                        chn.basicAck(deliv.getEnvelope().getDeliveryTag(), false);
                        synchronized (monitor) {
                            monitor.notify();
                        }
                    } else if (msg.equals("not_taken")) {
                        String med_name = bodyParts[1];
                        Long prescription_id = Long.parseLong(bodyParts[2]);
                        Long patient_id = Long.parseLong(bodyParts[3]);
                        NotTakenMedTracker notTakenMedTracker = new NotTakenMedTracker(med_name, prescription_id, patient_id);
                        System.out.println(notTakenMedTracker.toString());
                        notTakenMedTrackerRepository.save(notTakenMedTracker);

                        chn.basicPublish("", deliv.getProperties().getReplyTo(), properties, "not taken med saved".getBytes());
                        chn.basicAck(deliv.getEnvelope().getDeliveryTag(), false);
                        synchronized (monitor) {
                            monitor.notify();
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            };

            chn.basicConsume(Q_NAME, false, callback, (consumerTag -> {
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Application.class, args);
    }
}

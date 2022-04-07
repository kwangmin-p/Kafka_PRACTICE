import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class PracticeKafkaProducer {
    private final static String TOPIC_NAME = "test_topic";
    private final static String BOOTSTRAP_SERVERS = "yourCloudIp:9092";
    public static void main(String[] args){
        Properties configs = new Properties();
//        Producer setting
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        int loopCnt = 10;
        for(int index = 0; index < loopCnt; index++){
//            data pub
            String data = "produce index : " + index;
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, data);
            try{
                producer.send(record);
                System.out.println("Send to " + TOPIC_NAME + " , data : " + data);
                Thread.sleep(1000);
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }
}

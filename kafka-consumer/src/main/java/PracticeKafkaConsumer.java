import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class PracticeKafkaConsumer {
    private static String TOPIC_NAME = "test_topic";
    private static String GROUP_ID = "test_group"; //group 별로 topic에 대한 인덱싱을 따로 가져감
    private static String BOOTSTRAP_SERVERS = "yourCloudIp:9092";
    public static void main(String[] args){
//        Consumer setting
        Properties configs = new Properties();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);

//        kafka consumer sub
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for(ConsumerRecord<String, String> record : records){
                System.out.println(record.value());
            }
        }
    }
}

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.Metadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.Closeable;
import java.io.IOException;

public class Producer implements Closeable {
  final String topic;
  final KafkaProducer producer;

  public Producer(String topic) {
    final Properties properties = new Properties();
    properties.put("bootstrap.servers", "localhost:9092");
    properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    properties.put("acks", "1");
    properties.put("retries", "3");
    properties.put("retry.backoff.ms", "500");

    producer = new KafkaProducer(properties);
    this.topic = topic;
  }

  public void produce(final String key, final String value) throws Exception {
    ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);
    Future<RecordMetadata> futureMetadata = producer.send(record);
    RecordMetadata metadata = futureMetadata.get();
  }

  public void close() throws IOException {
    producer.close();
  }

}
package kafka.learning.statisticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@SpringBootApplication
public class StatisticserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticserviceApplication.class, args);
    }

    @Bean
    JsonMessageConverter jsonMessageConverter() {
        return new JsonMessageConverter();
    }

    @Bean
    DefaultErrorHandler errorHandler(KafkaOperations<String, Object> kafkaTemplate){
        // Template to send messages to the dead-letter topic
        // DeadLetterPublishingRecoverer is a MessageRecoverer that sends the failed record to a dead-letter topic.
        return new DefaultErrorHandler(new DeadLetterPublishingRecoverer(kafkaTemplate), new FixedBackOff(1000L, 2L));
    }


}

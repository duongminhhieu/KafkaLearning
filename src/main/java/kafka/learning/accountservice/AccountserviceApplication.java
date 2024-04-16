package kafka.learning.accountservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountserviceApplication.class, args);
    }

    // create a topic Kafka
    @Bean
    NewTopic notification() {
        return new NewTopic("notification", 2, (short) 1);
    }

    @Bean
    NewTopic statistic() {
        return new NewTopic("statistic", 2, (short) 1);
    }

}

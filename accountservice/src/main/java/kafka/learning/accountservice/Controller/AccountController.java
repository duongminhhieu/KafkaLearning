package kafka.learning.accountservice.Controller;
import kafka.learning.accountservice.Model.AccountDTO;
import kafka.learning.accountservice.Model.MessageDTO;
import kafka.learning.accountservice.Model.StatisticDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/new")
    public AccountDTO createAccount(@RequestBody AccountDTO accountDTO) {

        StatisticDTO statisticDTO = new StatisticDTO("New account created" + accountDTO.getEmail(), new Date());

        // create MessageDTO
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTo(accountDTO.getEmail());
        messageDTO.setToName(accountDTO.getName());
        messageDTO.setSubject("Welcome to our system");
        messageDTO.setMessage("Welcome to our system, we are happy to have you here");



        // send to kafka
        for(int i = 0; i < 100; i ++)
            kafkaTemplate.send("notification", messageDTO).whenCompleteAsync((result, ex) -> {
                if (ex != null) {
                    System.out.println("Error sending message: " + ex.getMessage());
                } else {
                    System.out.println("Message sent: " + result.getRecordMetadata().offset());
                }
            });

        kafkaTemplate.send("statistic", statisticDTO);

        return accountDTO;
    }
}

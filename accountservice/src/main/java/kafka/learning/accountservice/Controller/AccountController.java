package kafka.learning.accountservice.Controller;

import kafka.learning.accountservice.Model.Account;
import kafka.learning.accountservice.Model.Message;
import kafka.learning.accountservice.Model.Statistic;
import kafka.learning.accountservice.Repositories.AccountRepo;
import kafka.learning.accountservice.Repositories.MessageRepo;
import kafka.learning.accountservice.Repositories.StatisticRepo;
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

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private StatisticRepo statisticRepo;

    @PostMapping("/new")
    public Account createAccount(@RequestBody Account accountDTO) {

        Statistic statisticDTO = new Statistic("New account created" + accountDTO.getEmail(), new Date());
        statisticDTO.setStatus(false);

        // create MessageDTO
        Message messageDTO = new Message();
        messageDTO.setTo(accountDTO.getEmail());
        messageDTO.setToName(accountDTO.getName());
        messageDTO.setSubject("Welcome to our system");
        messageDTO.setMessage("Welcome to our system, we are happy to have you here");
        messageDTO.setStatus(false);


        // save to db
        accountRepo.save(accountDTO);
        messageRepo.save(messageDTO);
        statisticRepo.save(statisticDTO);

//        // send to kafka
//        for(int i = 0; i < 100; i ++)
//            kafkaTemplate.send("notification", messageDTO).whenCompleteAsync((result, ex) -> {
//                if (ex != null) {
//                    System.out.println("Error sending message: " + ex.getMessage());
//                } else {
//                    System.out.println("Message sent: " + result.getRecordMetadata().offset());
//                }
//            });
//
//        kafkaTemplate.send("statistic", statisticDTO);

        return accountDTO;
    }
}

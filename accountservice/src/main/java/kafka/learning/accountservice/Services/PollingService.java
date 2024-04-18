package kafka.learning.accountservice.Services;


import kafka.learning.accountservice.Model.Message;
import kafka.learning.accountservice.Model.Statistic;
import kafka.learning.accountservice.Repositories.AccountRepo;
import kafka.learning.accountservice.Repositories.MessageRepo;
import kafka.learning.accountservice.Repositories.StatisticRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PollingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private StatisticRepo statisticRepo;


    @Scheduled(fixedDelay = 1000)
    public void producer() {

        // get list Message
        List<Message> messageList = messageRepo.findByStatus(false);

        for(Message message: messageList) {
            kafkaTemplate.send("notification", message).whenCompleteAsync((result, ex) -> {
                if (ex != null) {
                    logger.error("Error sending message: " + ex);
                } else {
                    logger.info("Message sent: " + result.getRecordMetadata().offset());
                    message.setStatus(true);
                    messageRepo.save(message);
                }
            });
        }

        // get list Statistic
        List<Statistic> statisticList = statisticRepo.findByStatus(false);

        for(Statistic statistic: statisticList) {
            kafkaTemplate.send("statistic", statistic).whenCompleteAsync((result, ex) -> {
                if (ex != null) {
                    logger.error("Error sending statistic: " + ex);
                } else {
                    logger.info("Statistic sent: " + result.getRecordMetadata().offset());
                    statistic.setStatus(true);
                    statisticRepo.save(statistic);
                }
            });
        }

    }



    @Scheduled(fixedDelay = 60000)
    public void delete() {

        // get list Message
        List<Message> messageList = messageRepo.findByStatus(true);
        messageRepo.deleteAllInBatch(messageList);

        // get list Statistic
        List<Statistic> statisticList = statisticRepo.findByStatus(true);
        statisticRepo.deleteAllInBatch(statisticList);


    }






}

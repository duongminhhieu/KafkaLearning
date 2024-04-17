package kafka.learning.notificationservice.Services;
import kafka.learning.notificationservice.Models.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EmailService emailService;


    @KafkaListener(id = "notificationGroup", topics = "notification")
    public void listenNotification(MessageDTO messageDTO) {
        // log
        logger.info("Notification received: " + messageDTO.getTo());

        // send email
        emailService.sendEmail(messageDTO);

    }

}

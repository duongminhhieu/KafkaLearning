package kafka.learning.notificationservice.Services;


import jakarta.mail.internet.MimeMessage;
import kafka.learning.notificationservice.Models.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    // create logger
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    String from;

    @Async
    public void sendEmail(MessageDTO messageDTO){
        try {

            //log
            logger.info("Sending email to: " + messageDTO.getTo());

            // create MimeMessage
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            // load template
            Context context = new Context();
            context.setVariable("name", messageDTO.getToName());
            context.setVariable("message", messageDTO.getMessage());
            String html = templateEngine.process("welcome-email", context);

            // set email
            helper.setFrom(from);
            helper.setTo(messageDTO.getTo());
            helper.setSubject(messageDTO.getSubject());
            helper.setText(html, true);

            // send email
            javaMailSender.send(mimeMessage);

            //log
            logger.info("Email sent to: " + messageDTO.getTo());

        } catch (Exception e) {
            logger.error( "Error: " + e.getMessage());
        }
    }



}

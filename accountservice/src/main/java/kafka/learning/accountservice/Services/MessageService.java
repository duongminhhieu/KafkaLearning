package kafka.learning.accountservice.Services;


import kafka.learning.accountservice.Repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {


    @Autowired
    private MessageRepo messageRepo;



}

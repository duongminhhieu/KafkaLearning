package kafka.learning.statisticservice.Services;


import kafka.learning.statisticservice.Entities.Statistic;
import kafka.learning.statisticservice.Repositories.StatisticRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;


@Service
public class StatisticService {

    // logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatisticRepo statisticRepo;



    @RetryableTopic(attempts = "5", dltTopicSuffix = "-dlt" , backoff = @Backoff(delay = 2_000, multiplier = 2))
    // Retry 5 times with 2 seconds delay and 2 multiplier
    @KafkaListener(id = "statistic-group", topics = "statistic")
    public void listenStatistic(Statistic statistic) {

        logger.info("Statistic received: " + statistic.getMessage());
        // statisticRepo.save(statistic);
        throw new RuntimeException("Error");
    }

    @KafkaListener(id = "dltGroup", topics = "statistic.DLT")
    public void listenDLT(Statistic statistic) {
        logger.info("DLT received: " + statistic.getMessage());

        // save to database to re-send to queue
    }


}

package kafka.learning.statisticservice.Services;


import kafka.learning.statisticservice.Entities.Statistic;
import kafka.learning.statisticservice.Repositories.StatisticRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class StatisticService {

    // logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatisticRepo statisticRepo;


    @KafkaListener(id = "statistic-group", topics = "statistic")
    public void listenStatistic(Statistic statistic) {

        logger.info("Statistic received: " + statistic.getMessage());
        statisticRepo.save(statistic);
    }


}

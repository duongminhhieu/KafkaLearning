package kafka.learning.statisticservice.Repositories;

import kafka.learning.statisticservice.Entities.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepo extends JpaRepository<Statistic, Integer > {
}

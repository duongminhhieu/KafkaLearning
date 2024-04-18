package kafka.learning.accountservice.Repositories;

import kafka.learning.accountservice.Model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticRepo extends JpaRepository<Statistic, Integer > {
    List<Statistic> findByStatus(boolean status);
}

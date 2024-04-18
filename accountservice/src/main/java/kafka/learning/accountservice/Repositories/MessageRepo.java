package kafka.learning.accountservice.Repositories;

import kafka.learning.accountservice.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Integer > {

    List<Message> findByStatus(boolean status);
}

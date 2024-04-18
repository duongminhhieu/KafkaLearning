package kafka.learning.accountservice.Repositories;

import kafka.learning.accountservice.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Integer > {
}

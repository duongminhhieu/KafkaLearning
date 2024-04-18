package kafka.learning.accountservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;
    private Date createdDate;
    private Boolean status;


    public Statistic(String s, Date date) {
        this.message = s;
        this.createdDate = date;
    }

    public Statistic() {

    }
}

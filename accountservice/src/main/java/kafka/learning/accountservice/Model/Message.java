package kafka.learning.accountservice.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "to_email")
    private String to;
    private String toName;
    private String subject;
    private String message;
    private Boolean status;

}

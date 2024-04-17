package kafka.learning.accountservice.Model;

import java.util.Date;

public class StatisticDTO {

    private String message;
    private Date createdDate;

    public StatisticDTO(String message, Date createdDate) {
        this.message = message;
        this.createdDate = createdDate;
    }

    public StatisticDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by gayashan on 9/23/2017.
 */

@Entity
@Access(AccessType.FIELD)
@Table(name = "Appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue
    private int id;
    private Timestamp startTime;
    private Timestamp endTime;

    String description;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}

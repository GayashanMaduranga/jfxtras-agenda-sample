import org.hibernate.Session;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by gayashan on 9/23/2017.
 */
public class MyTest {

    @Test
    public void canAddAppointment(){

        Session session = UserSession.getSession();

        //AppointmentEntity appointmentEntity = new AppointmentEntity();
        //appointmentEntity.setStartTime(LocalDate.now().atTime(4, 00));
        //appointmentEntity.setEndTime(LocalDate.now().atTime(15, 30));
        //appointmentEntity.setDescription("OK");

        Student s = new Student();
        s.setName("nos");

        session.beginTransaction();
        session.save(s);
        session.getTransaction().commit();


    }
}

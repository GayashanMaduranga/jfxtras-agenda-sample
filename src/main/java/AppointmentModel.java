import jfxtras.scene.control.agenda.Agenda;
import org.hibernate.Session;

import java.sql.Timestamp;

/**
 * Created by gayashan on 9/24/2017.
 */
public class AppointmentModel {

    private static Session session = UserSession.getSession();

    public void addNewAppointment(Agenda.AppointmentImplLocal newAppointment){

        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setStartTime(Timestamp.valueOf(newAppointment.getStartLocalDateTime()));
        appointmentEntity.setEndTime(Timestamp.valueOf(newAppointment.getEndLocalDateTime()));
        appointmentEntity.setDescription(newAppointment.getDescription());




        session.beginTransaction();
        session.save(appointmentEntity);
        session.getTransaction().commit();

    }
}

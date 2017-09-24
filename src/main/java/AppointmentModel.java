import jfxtras.scene.control.agenda.Agenda;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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


    public List<AppointmentEntity> getAppointments(LocalDateTime startTime,LocalDateTime endTime){


        String hql = "FROM AppointmentEntity a where a.startTime between :startTime and :endTime";
        Query query = session.createQuery(hql);
        query.setParameter("startTime",Timestamp.valueOf(startTime));
        query.setParameter("endTime",Timestamp.valueOf(endTime));



        List results = query.list();
        return results;
    }
}

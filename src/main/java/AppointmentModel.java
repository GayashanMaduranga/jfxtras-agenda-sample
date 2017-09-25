import jfxtras.scene.control.agenda.Agenda;
import org.hibernate.Query;
import org.hibernate.Session;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gayashan on 9/24/2017.
 */
public class AppointmentModel {

    private static Session session = UserSession.getSession();

    public int addNewAppointment(Agenda.AppointmentImplLocal newAppointment){

        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setStartTime(Timestamp.valueOf(newAppointment.getStartLocalDateTime()));
        appointmentEntity.setEndTime(Timestamp.valueOf(newAppointment.getEndLocalDateTime()));
        appointmentEntity.setDescription(newAppointment.getDescription());




        session.beginTransaction();
        session.save(appointmentEntity);
        session.getTransaction().commit();

        //returns generated id value
        return appointmentEntity.getId();

    }


    private List<AppointmentEntity> getAppointmentEntities(LocalDateTime startTime,LocalDateTime endTime){


        String hql = "FROM AppointmentEntity a where a.startTime between :startTime and :endTime";
        Query query = session.createQuery(hql);
        query.setParameter("startTime",Timestamp.valueOf(startTime));
        query.setParameter("endTime",Timestamp.valueOf(endTime));



        List results = query.list();
        return results;
    }

    public List<Appointment> getAppointments(LocalDateTime startTime,LocalDateTime endTime){
        List<AppointmentEntity> entityList =getAppointmentEntities(startTime,endTime);
        List<Appointment> appointmentList = new ArrayList<>();



        for(AppointmentEntity entity:entityList){

            Agenda.AppointmentImplLocal appointmentImplLocal= new Appointment()
                    .withStartLocalDateTime(entity.getStartTime().toLocalDateTime())
                    .withEndLocalDateTime(entity.getEndTime().toLocalDateTime())
                    .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));
            appointmentImplLocal.setDescription(entity.getDescription());

            Appointment appointment = (Appointment)appointmentImplLocal;
            appointment.setId(entity.getId());
            appointmentList.add(appointment);


        }

        return appointmentList;
    }


    public void deleteAppointment(int id){

        AppointmentEntity entity = (AppointmentEntity)session.get(AppointmentEntity.class,id);

        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }


    public void updateAppointment(Appointment newAppointment) {

        AppointmentEntity entity = (AppointmentEntity)session.get(AppointmentEntity.class,newAppointment.getId());

        entity.setStartTime(Timestamp.valueOf(newAppointment.getStartLocalDateTime()));
        entity.setEndTime(Timestamp.valueOf(newAppointment.getEndLocalDateTime()));
        entity.setDescription(newAppointment.getDescription());

       if(entity==null)
           System.out.println("NULL");
       else{
           System.out.println(entity.getId() + entity.getStartTime().toLocalDateTime().toLocalDate().toString());
       }
           System.out.println(newAppointment.toString());

       try {
           session.beginTransaction();
           session.update(entity);
           session.getTransaction().commit();
       }catch (Exception e){
           e.printStackTrace();
       }


    }
}

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import jfxtras.scene.control.agenda.Agenda;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by gayashan on 9/14/2017.
 */
public class AgendaController implements Initializable{

    @FXML
    private Agenda agenda;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        agenda.appointments().addAll(
//                new Agenda.AppointmentImplLocal()
//                        .withStartLocalDateTime(LocalDate.now().atTime(4, 00))
//                        .withEndLocalDateTime(LocalDate.now().atTime(15, 30))
//                        .withDescription("It's time")
//                        .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
//        );



        agenda.setAllowDragging(true);
        agenda.setAllowResize(true);
        agenda.newAppointmentCallbackProperty().set( (localDateTimeRange) -> {
            Agenda.AppointmentImplLocal appointmentImplLocal= new Agenda.AppointmentImplLocal()
                    .withStartLocalDateTime(localDateTimeRange.getStartLocalDateTime())
                    .withEndLocalDateTime(localDateTimeRange.getEndLocalDateTime())
                    .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));

            System.out.println(appointmentImplLocal.getStartLocalDateTime().toLocalDate().toString());
            return appointmentImplLocal; // it is better to have a map of appointment groups to get from


        });
//
//        agenda.appointmentChangedCallbackProperty().set(param ->{
//
//
//                    if(param!=null) {
//                        System.out.println("Triggerd");
//                        System.out.println(param.getStartLocalDateTime().toLocalDate().toString());
//                    }else {
//                        System.out.println("deleted");
//                    }
//                    return null;
//                }
//        );
//
//
//        agenda.actionCallbackProperty().set(param ->
//                {
//
//                    if(param!=null)
//                        System.out.println("Deleted");
//
//                    agenda.selectedAppointments().remove(agenda.actionCallbackProperty());
//                    agenda.refresh();
//                    return null;
//                }
//        );


//        agenda.selectedAppointments().remove();




//        agenda.actionCallback().set( (appointment) -> {
//            System.out.println("Action was triggered on " + appointment.getDescription());
//        });

//        List<Agenda.Appointment> appointment = agenda.getAp;

        //agenda.setStyle("-fx-background-color: #154687");

//        agenda.setStyle("-fx-progress-color: #408768");

//        agenda.appointments();


    }

}



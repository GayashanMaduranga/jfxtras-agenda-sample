import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import jfxtras.scene.control.agenda.Agenda;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TextArea;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.LocalTimeTextField;


/**
 * Created by gayashan on 9/14/2017.
 */
public class AgendaController implements Initializable {

    AppointmentModel appointmentModel = new AppointmentModel();
    @FXML
    private Agenda agenda;
    @FXML
    private CalendarPicker calander;
    @FXML
    private LocalTimeTextField startTime;
    @FXML
    private LocalTimeTextField endTime;
    @FXML
    private TextArea desctiption;

//    private List<> ;

    @FXML
    void addAppointment(ActionEvent event) {


        Date selected = calander.getCalendar().getTime();
        LocalDate date = selected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Agenda.AppointmentImplLocal newAppointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(startTime.getLocalTime().atDate(date))
                .withEndLocalDateTime(endTime.getLocalTime().atDate(date))
                .withDescription(desctiption.getText())
                .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));

        agenda.appointments().add(newAppointment);
        appointmentModel.addNewAppointment(newAppointment);


    }

    @FXML
    void deleteAppointment(ActionEvent event) {


    }

    @FXML
    void updateAppointment(ActionEvent event) {

    }


    private void updateAppointment(Agenda.AppointmentImplLocal newAppointment) {

//        AppointmentEntity appointmentEntity ;
//        appointmentEntity.setStartTime(Timestamp.valueOf(newAppointment.getStartLocalDateTime()));
//        appointmentEntity.setEndTime(Timestamp.valueOf(newAppointment.getEndLocalDateTime()));
//        appointmentEntity.setDescription(newAppointment.getDescription());
//
//
//        agenda.appointments().add(newAppointment);
//
//        session.beginTransaction();
//        session.update(appointmentEntity);
//        session.getTransaction().commit();

    }

    private void updateAgenda(){

        agenda.localDateTimeRangeCallbackProperty().set(param -> {


            List<Agenda.AppointmentImplLocal> list = appointmentModel.getAppointments(param.getStartLocalDateTime(), param.getEndLocalDateTime());
            agenda.appointments().clear();
            agenda.appointments().addAll(list);
                    return null;
                }

        );


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        updateAgenda();
        agenda.setAllowDragging(true);
        agenda.setAllowResize(true);
        agenda.newAppointmentCallbackProperty().set((localDateTimeRange) -> {
            Agenda.AppointmentImplLocal appointmentImplLocal = new Agenda.AppointmentImplLocal()
                    .withStartLocalDateTime(localDateTimeRange.getStartLocalDateTime())
                    .withEndLocalDateTime(localDateTimeRange.getEndLocalDateTime())
                    .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));


            appointmentModel.addNewAppointment(appointmentImplLocal);
            return appointmentImplLocal;


        });


        calander.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            Date cal = calander.getCalendar().getTime();
            LocalDate ld = cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            LocalTime lt = LocalTime.NOON;

            agenda.setDisplayedLocalDateTime(LocalDateTime.of(ld, lt));

            updateAgenda();


        });
//
//        agenda.appointmentChangedCallbackProperty().set(param ->{
//
//
//
//            if(!agenda.appointments().contains(param))
//                System.out.println("Deleted change");
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


//        agenda.actionCallbackProperty().set(param ->
//                {
//                    System.out.println("OK");
//
//                    if(!agenda.appointments().contains(param))
//                        System.out.println("Deleted");
//
//
//
//
//                    agenda.selectedAppointments().remove(agenda.actionCallbackProperty());
//                    agenda.refresh();
//                    return null;
//                }
//        );
//
//        Callback<Agenda.Appointment, Void> callback = agenda.getEditAppointmentCallback();

//        agenda.editAppointmentCallbackProperty().set(new Callback<Agenda.Appointment, Void>() {
//            @Override
//            public Void call(Agenda.Appointment param) {
//
//
//                return callback.call(param);
//            }
//        });

//        agenda.editAppointmentCallbackProperty().addListener(new ChangeListener<Callback<Agenda.Appointment, Void>>() {
//            @Override
//            public void changed(ObservableValue<? extends Callback<Agenda.Appointment, Void>> observable, Callback<Agenda.Appointment, Void> oldValue, Callback<Agenda.Appointment, Void> newValue) {
//                System.out.println("OK" + oldValue.toString());
//            }
//        });
//
//
//      agenda.getEditAppointmentCallback();


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



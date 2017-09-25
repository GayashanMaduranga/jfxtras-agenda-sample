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

    private Appointment selectedAppointment;


    @FXML
    void addAppointment(ActionEvent event) {


        int id;

        Date selected = calander.getCalendar().getTime();
        LocalDate date = selected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Agenda.AppointmentImplLocal newAppointment = new Appointment()
                .withStartLocalDateTime(startTime.getLocalTime().atDate(date))
                .withEndLocalDateTime(endTime.getLocalTime().atDate(date))
                .withDescription(desctiption.getText())
                .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));

        id = appointmentModel.addNewAppointment(newAppointment);

        //System.out.println("ID ID :: " + id);
        Appointment a = (Appointment)newAppointment;
        a.setId(id);

        System.out.println(a.getId() + "alsdfalsdkf");

        agenda.appointments().add(a);
        agenda.refresh();

        updateAppointment();

    }

    @FXML
    void deleteAppointment(ActionEvent event) {

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n\n\n\n\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5");
        System.out.println(selectedAppointment.getId());
        appointmentModel.deleteAppointment(selectedAppointment.getId());
        updateAgenda();
        agenda.refresh();

    }

    @FXML
    void updateAppointment(ActionEvent event) {
        Date selected;
        if( calander.getCalendar()==null){
            selected = Date.from(selectedAppointment.getStartLocalDateTime().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());

        }else {
            selected = calander.getCalendar().getTime();
        }
        LocalDate date = selected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        selectedAppointment.setStartLocalDateTime(startTime.getLocalTime().atDate(date));
        selectedAppointment.setEndLocalDateTime(endTime.getLocalTime().atDate(date));
        selectedAppointment.setDescription(desctiption.getText());
        appointmentModel.updateAppointment(selectedAppointment);
        updateAgenda();
        agenda.refresh();



    }


    private void updateAppointment() {



    }

    private void updateAgenda(){;
        agenda.localDateTimeRangeCallbackProperty().set(param -> {


            List<Appointment> list = appointmentModel.getAppointments(param.getStartLocalDateTime(), param.getEndLocalDateTime());
            agenda.appointments().clear();
            agenda.appointments().addAll(list);
                    return null;
                }

        );


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            updateAgenda();
        }catch (Exception e){
            e.printStackTrace();

        }

        agenda.setAllowDragging(true);
        agenda.setAllowResize(true);
        agenda.newAppointmentCallbackProperty().set((localDateTimeRange) -> {
            Agenda.AppointmentImplLocal appointmentImplLocal = new Appointment()
                    .withStartLocalDateTime(localDateTimeRange.getStartLocalDateTime())
                    .withEndLocalDateTime(localDateTimeRange.getEndLocalDateTime())
                    .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));


            int id = appointmentModel.addNewAppointment(appointmentImplLocal);

            Appointment a = (Appointment)appointmentImplLocal;
            a.setId(id);

            return a;

        });


        calander.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            Date cal = calander.getCalendar().getTime();
            LocalDate ld = cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            LocalTime lt = LocalTime.NOON;

            agenda.setDisplayedLocalDateTime(LocalDateTime.of(ld, lt));

            updateAgenda();


        });

        agenda.appointmentChangedCallbackProperty().set(param ->{


                    appointmentModel.updateAppointment((Appointment)param);
                    return null;
                }
        );
//

//
        agenda.actionCallbackProperty().set(param ->
                {
                    selectedAppointment = (Appointment)param;
                    startTime.setLocalTime(selectedAppointment.getStartLocalDateTime().toLocalTime());
                    endTime.setLocalTime(selectedAppointment.getEndLocalDateTime().toLocalTime());
                    desctiption.setText(selectedAppointment.getDescription());
                    return null;
                }
        );
//
//        Callback<Agenda.Appointment, Void> callback = agenda.getEditAppointmentCallback();



//        agenda.editAppointmentCallbackProperty().set(param ->{
//
//            System.out.println("Edit");
//            return null;
//        } );

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



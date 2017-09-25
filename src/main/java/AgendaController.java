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
    private CalendarPicker calendar;
    @FXML
    private LocalTimeTextField startTime;
    @FXML
    private LocalTimeTextField endTime;
    @FXML
    private TextArea description;

    private Appointment selectedAppointment;


    @FXML
    void addAppointment(ActionEvent event) {


        int id;

        Date selected = calendar.getCalendar().getTime();
        LocalDate date = selected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Agenda.AppointmentImplLocal newAppointment = new Appointment()
                .withStartLocalDateTime(startTime.getLocalTime().atDate(date))
                .withEndLocalDateTime(endTime.getLocalTime().atDate(date))
                .withDescription(description.getText())
                .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));

        id = appointmentModel.addNewAppointment(newAppointment);

        Appointment a = (Appointment)newAppointment;
        a.setId(id);

        agenda.appointments().add(a);
        agenda.refresh();

        updateAppointment();

    }

    @FXML
    void deleteAppointment(ActionEvent event) {

        System.out.println(selectedAppointment.getId());
        appointmentModel.deleteAppointment(selectedAppointment.getId());
        updateAgenda();
        agenda.refresh();

    }

    @FXML
    void updateAppointment(ActionEvent event) {
        Date selected;
        if( calendar.getCalendar()==null){
            selected = Date.from(selectedAppointment.getStartLocalDateTime().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());

        }else {
            selected = calendar.getCalendar().getTime();
        }
        LocalDate date = selected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        selectedAppointment.setStartLocalDateTime(startTime.getLocalTime().atDate(date));
        selectedAppointment.setEndLocalDateTime(endTime.getLocalTime().atDate(date));
        selectedAppointment.setDescription(description.getText());
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


        calendar.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            Date cal = calendar.getCalendar().getTime();
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

        agenda.actionCallbackProperty().set(param ->
                {
                    selectedAppointment = (Appointment)param;
                    startTime.setLocalTime(selectedAppointment.getStartLocalDateTime().toLocalTime());
                    endTime.setLocalTime(selectedAppointment.getEndLocalDateTime().toLocalTime());
                    description.setText(selectedAppointment.getDescription());
                    return null;
                }
        );

    }


}



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import jfxtras.scene.control.agenda.Agenda;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.control.TextArea;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.LocalTimeTextField;
import org.hibernate.Session;


/**
 * Created by gayashan on 9/14/2017.
 */
public class AgendaController implements Initializable{

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

    private static Session session = UserSession.getSession();

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




        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setStartTime(Timestamp.valueOf(newAppointment.getStartLocalDateTime()));
        appointmentEntity.setEndTime(Timestamp.valueOf(newAppointment.getEndLocalDateTime()));
        appointmentEntity.setDescription(newAppointment.getDescription());


        agenda.appointments().add(newAppointment);

       session.beginTransaction();
       session.save(appointmentEntity);
       session.getTransaction().commit();

    }

    @FXML
    void deleteAppointment(ActionEvent event) {




    }

    @FXML
    void updateAppointment(ActionEvent event) {

    }




    @FXML
    void calanderButtonSelected(MouseEvent event) {
        System.out.println("OK");
        calander.contextMenuProperty().addListener(observable -> {
            System.out.println("HEllo");
            return;
        });

    }















    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        agenda.appointments().addAll(
//                new Agenda.AppointmentImplLocal()
//                        .withStartLocalDateTime(LocalDate.now().atTime(4, 00))
//                        .withEndLocalDateTime(LocalDate.now().atTime(15, 30))
//                        .withDescription("It's time")
//                        .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
//        );



        calander.contextMenuProperty().addListener(observable -> {
            System.out.println("HEllo");
            return;
        });

//        calander.getChildrenUnmodifiable().forEach(node -> node.setOnMouseClicked(event -> {
//
//            System.out.println("OK 234234");
//        }));



        agenda.setAllowDragging(true);
        agenda.setAllowResize(true);
        agenda.newAppointmentCallbackProperty().set( (localDateTimeRange) -> {
            Agenda.AppointmentImplLocal appointmentImplLocal= new Agenda.AppointmentImplLocal()
                    .withStartLocalDateTime(localDateTimeRange.getStartLocalDateTime())
                    .withEndLocalDateTime(localDateTimeRange.getEndLocalDateTime())
                    .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));

            System.out.println(appointmentImplLocal.getStartLocalDateTime().toLocalDate().toString());
            return appointmentImplLocal;


        });





        calander.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                if (event.getButton().equals(MouseButton.PRIMARY)) {
//                    Calendar cal = calendarpicker.getCalendar();
//                    if (cal != null) {
//                        selectedDate = cal.getTime();
//                        log.debug(String.format("(selectedDate): %s" , selectedDate));
//                        log.debug(String.format("(displayed): %s" , calendarpicker.getDisplayedCalendar().getTime()));
//                    }
//                }
                //calander.


                Date cal = calander.getCalendar().getTime();
                LocalDate ld = cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

               // LocalDate ld = LocalDate.of(2010,10,10);
                LocalTime lt = LocalTime.NOON;

                agenda.setDisplayedLocalDateTime(LocalDateTime.of(ld,lt));
            }
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



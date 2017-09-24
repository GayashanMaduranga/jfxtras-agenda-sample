import jfxtras.scene.control.agenda.Agenda;

/**
 * Created by gayashan on 9/24/2017.
 */
public class Appointment extends Agenda.AppointmentImplLocal{

    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

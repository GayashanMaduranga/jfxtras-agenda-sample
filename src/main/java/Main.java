/**
 * Created by gayashan on 9/23/2017.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{



        try {
            Parent root = FXMLLoader.load(getClass().getResource("/agendaSample.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Sample Agenda");
            primaryStage.setMaximized(false);
            primaryStage.setMinHeight(715.0);
            primaryStage.setMinWidth(1299.0);
            primaryStage.show();
        } catch (IOException ex) {
//            Logger.getLogger(StoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}

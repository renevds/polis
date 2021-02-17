package polis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import prog2.util.Viewport;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("polis.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("polis/style.css");
        stage.setScene(scene);
        stage.setTitle("Polis by René Van Der Schueren");
        stage.show();
    }

}

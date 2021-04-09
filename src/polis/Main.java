package polis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image("polis/favicon.png")) ;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("polis.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("polis/style.css");
        stage.setScene(scene);
        stage.setTitle("Polis by René Van Der Schueren");
        ((PolisController)loader.getController()).setStage(stage);
        stage.show();
    }

}

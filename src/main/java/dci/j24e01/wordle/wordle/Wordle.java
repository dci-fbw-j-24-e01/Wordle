package dci.j24e01.wordle.wordle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Wordle extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Wordle.class.getResource("wordle.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 435, 700);
        stage.setTitle("Wordle");
        Image image = new Image(Wordle.class.getResource("/images/test.png").toString());
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
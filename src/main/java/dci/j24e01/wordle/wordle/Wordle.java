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
        Scene scene = new Scene(fxmlLoader.load(), 350, 500);
        stage.setTitle("Wordle");
        System.out.println(Wordle.class.getResourceAsStream("/logo.png"));
        //stage.getIcons().add(new Image(Wordle.class.getResourceAsStream("/logo.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
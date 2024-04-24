package dci.j24e01.wordle.wordle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WordleController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
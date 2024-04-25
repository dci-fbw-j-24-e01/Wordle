package dci.j24e01.wordle.wordle;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Optional;

public class Game {
    ArrayList<String> words;
    String secret;

    public Game() {
        words = new ArrayList<>();
        //secret = Dictionary.getRandomWord();
        secret = "ready";
    }

    public boolean guessed(String word) {
        return word.equals(secret);
    }

    public boolean checkWord(String word) {
        if (word.length() != 5) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Enter a 5 letter word");
            alert.show();
            return false;
        } else if (!Dictionary.isValid(word)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Enter a valid word");
            alert.show();
            return false;
        }
        return Dictionary.words.contains(word);
    }

    public void setLabelsContent(String word, Label[] labels) {

        for (int i = 0; i < 5; i++) {
            labels[i].setText(word.charAt(i) + "");
            labels[i].setStyle("");
            labels[i].setAlignment(Pos.CENTER);

            if (secret.charAt(i) == word.charAt(i)) {
                labels[i].setStyle("-fx-background-color: rgb(0, 255, 0); -fx-font-size:24;");

            } else if (secret.contains(word.charAt(i) + "")) {
                labels[i].setStyle("-fx-background-color: rgb(255, 200, 0); -fx-font-size:24;");

            } else {
                labels[i].setStyle("-fx-background-color: rgb(219, 219, 217); -fx-font-size:24;");
            }

        }
        System.out.println(secret);
    }

    public void endGame(boolean won) {

        ButtonType customYesButton = new ButtonType("Restart", ButtonBar.ButtonData.YES);
        ButtonType customNoButton = new ButtonType("Exit", ButtonBar.ButtonData.NO);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", customYesButton, customNoButton);
        if (won) {
            alert.setContentText("Congratulations! You won!!!");
        } else {
            alert.setContentText("Whooops... You Lose =( The secret word was " + secret + ".");
        }
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == customNoButton) {
                System.exit(0);
            }
        }

    }
}

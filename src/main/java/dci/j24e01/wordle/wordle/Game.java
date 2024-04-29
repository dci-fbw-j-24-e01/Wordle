package dci.j24e01.wordle.wordle;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

public class Game {
    ArrayList<String> words;
    String secret;
    char[] secretArray;

    public Game() {
        words = new ArrayList<>();
        //secret = Dictionary.getRandomWord();
        secret = "ready";
        secretArray = secret.toCharArray();
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
        char[] chars = word.toCharArray();
        isCorrectPosition(chars);
        contains(chars);

        for (int i = 0; i < 5; i++) {
            labels[i].setText(word.charAt(i) + "");
            labels[i].setStyle("");
            labels[i].setAlignment(Pos.CENTER);

            if (chars[i] == ' ') {
                labels[i].setStyle("-fx-background-color: rgb(0, 255, 0); -fx-font-size:24;");
            } else if (chars[i] == '.') {
                labels[i].setStyle("-fx-background-color: rgb(255, 200, 0); -fx-font-size:24;");
            } else {
                labels[i].setStyle("-fx-background-color: rgb(175, 175, 175); -fx-font-size:24;");
            }

        }
        System.out.println(secret);
    }

    public void endGame(boolean won) {

        ButtonType customYesButton = new ButtonType("Restart", ButtonBar.ButtonData.YES);
        ButtonType customNoButton = new ButtonType("Exit", ButtonBar.ButtonData.NO);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", customYesButton, customNoButton);
        if (won) {
            alert.setTitle("You're a winner! Awesome!");
            alert.setHeaderText("Congratulations! You won!!!");
            ImageView icon = new ImageView("file:src/main/java/dci/j24e01/wordle/wordle/resources/winner.png");
            icon.setFitHeight(70);
            icon.setFitWidth(70);
            alert.setGraphic(icon);
        } else {
            alert.setTitle("Better luck next time!");
            alert.setHeaderText("Whooops... You Lose :( \nThe secret word was \"" + secret + "\".");
            ImageView icon = new ImageView("file:src/main/java/dci/j24e01/wordle/wordle/resources/broken_cup.png");
            icon.setFitHeight(70);
            icon.setFitWidth(70);
            alert.setGraphic(icon);
        }
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == customNoButton) {
                System.exit(0);
            }
        }
    }

    private char[] isCorrectPosition (char[] chars) {
        for (int i = 0; i < 5; i++) {
            if (chars[i] == secretArray[i]) {
                chars[i] = ' ';
            }
        }
        return chars;
    }

    private char[] contains(char[] chars) {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (chars[j] == secretArray[i]) {
                    chars[j] = '.';
                    break;
                }
            }
        }
        return chars;
    }
}
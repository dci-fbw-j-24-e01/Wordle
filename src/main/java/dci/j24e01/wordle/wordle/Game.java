package dci.j24e01.wordle.wordle;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Optional;

public class Game {

    ArrayList<String> words;
    String secret;
    char[] secretArray;

    public Game() {
        words = new ArrayList<>();
        secret = Dictionary.getRandomWord();
    }

    public boolean guessed(String word) {
        return word.equals(secret);
    }

    public boolean checkWord(String word) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (word.length() != 5) {
            alert.setContentText("Enter a 5 letter word");
            alert.show();
            return false;
        } else if (!Dictionary.isValid(word)) {
            alert.setContentText("Enter a valid word");
            alert.show();
            return false;
        }
        return Dictionary.words.contains(word);
    }

    public void setContent(String word, Label[] labels) {
        secretArray = secret.toCharArray();
        char[] chars = word.toCharArray();

        isCorrectPosition(chars);
        contains(chars);

        String fontSize = " -fx-font-size:24;";
        String GREEN = " -fx-background-color: rgb(0, 255, 0); -fx-background-radius: 5;";
        String ORANGE = " -fx-background-color: rgb(255, 200, 0); -fx-background-radius: 5;";
        String DARK_GREY = " -fx-background-color: rgb(175, 175, 175); -fx-background-radius: 5;";
        String setBorder = " -fx-border-color: rgb(150, 150, 150); -fx-border-radius: 5;";

        for (int i = 0; i < 5; i++) {
            labels[i].setText(word.charAt(i) + "");
            labels[i].setStyle("");
            labels[i].setAlignment(Pos.CENTER);
            int index = (int) word.charAt(i) - 97;

            if (chars[i] == ' ') {
                labels[i].setStyle(GREEN + fontSize);
                WordleController.buttons[index].setStyle(GREEN + setBorder);
            } else if (chars[i] == '.') {
                labels[i].setStyle(ORANGE + fontSize);
                if (!WordleController.buttons[index].getStyle().equals(GREEN)) {
                    WordleController.buttons[index].setStyle(ORANGE + setBorder);
                }
            } else {
                labels[i].setStyle(DARK_GREY + fontSize);
                if (!WordleController.buttons[index].getStyle().equals(GREEN) &&
                        !WordleController.buttons[index].getStyle().equals(ORANGE)) {
                    WordleController.buttons[index].setStyle(DARK_GREY + setBorder);
                }
            }
        }
    }

    public void endGame(boolean won) {

        ButtonType customYesButton = new ButtonType("Restart", ButtonBar.ButtonData.YES);
        ButtonType customNoButton = new ButtonType("Exit", ButtonBar.ButtonData.NO);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", customYesButton, customNoButton);
        Image image;

        if (won) {
            alert.setTitle("You're a winner! Awesome!");
            alert.setHeaderText("Congratulations! You won!!!");
            image = new Image(Wordle.class.getResource("/images/winner.png").toString(), 70.0, 70.0, false,false);
        } else {
            alert.setTitle("Better luck next time!");
            alert.setHeaderText("Whooops... You Lose :( \nThe secret word was \"" + secret + "\".");
            image = new Image(Wordle.class.getResource("/images/broken_cup.png").toString(), 70.0, 70.0, false,false);
        }

        ImageView icon = new ImageView(image);
        alert.setGraphic(icon);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == customNoButton) {
                System.exit(0);
            }
        }
    }

    private void isCorrectPosition (char[] chars) {
        for (int i = 0; i < 5; i++) {
            if (chars[i] == secretArray[i]) {
                chars[i] = ' ';
                secretArray[i] = ' ';
            }
        }
    }

    private void contains(char[] chars) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (chars[i] == ' ') {
                    continue;
                }
                if (chars[j] == secretArray[i]) {
                    chars[j] = '.';
                    break;
                }
            }
        }
    }

}

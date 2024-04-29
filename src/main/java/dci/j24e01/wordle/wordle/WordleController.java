package dci.j24e01.wordle.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URISyntaxException;


public class WordleController {

    @FXML
    private TextField input;
    private Game game;
    @FXML
    private HBox Line0;
    @FXML
    private HBox Line1;
    @FXML
    private HBox Line2;
    @FXML
    private HBox Line3;
    @FXML
    private HBox Line4;
    @FXML
    private HBox Line5;
    @FXML
    private Button giveup;

    private HBox[] boxes;

    private int line;

    @FXML
    public void initialize() {
        restart();
    }

    @FXML
    public void onEnterButtonClick() {
        String inputText = input.getText().toLowerCase();
        boolean won = false;
        boolean success = game.checkWord(inputText);
        if (success) {
            Label[] labels = new Label[5];
            for (int i = 0; i < 5; i++) {
                Label label = (Label) boxes[line].getChildren().get(i);
                labels[i] = label;
            }
            game.setLabelsContent(inputText, labels);
            line++;
            if (game.guessed(inputText)) {
                game.endGame(true);
                reset();
                restart();
            }

        }
        input.clear();
        if (line > 5 && !won) {
            game.endGame(false);
            reset();
            restart();
        }
        if (line > 0) {
            giveup.setVisible(true);
        }
    }
    @FXML
    public void onEnterButtonClick(ActionEvent ae){
        onEnterButtonClick();
    }

    @FXML
    public void letterButtonClicked() {
        System.out.println("test");
    }
    @FXML
    public void onGiveUpButtonClick() {
        reset();
        restart();
        input.setText("");
    }

    public void restart() {
        game = new Game();
        boxes = new HBox[6];
        boxes[0] = Line0;
        boxes[1] = Line1;
        boxes[2] = Line2;
        boxes[3] = Line3;
        boxes[4] = Line4;
        boxes[5] = Line5;
        line = 0;
    }

    private void reset() {
        for (HBox box : boxes) {
            for(int i = 0; i < 5; i++) {
                Label label = (Label) box.getChildren().get(i);
                label.setStyle("-fx-background-color: rgb(219, 219, 219);");
                label.setText("");
            }
        }
        giveup.setVisible(false);
    }
    @FXML
    private void letterButtonClicked(ActionEvent event) throws URISyntaxException {

        Button letterButton = (Button) event.getSource();
        String letter = letterButton.getText();
        input.setText(input.getText() + letter);
    }
}
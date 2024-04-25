package dci.j24e01.wordle.wordle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class WordleController {

    @FXML
    private TextField input;
    @FXML
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

    private HBox[] boxes;

    private int line;

    @FXML
    public void initialize() {
        restart();
    }

    @FXML
    public void onEnterButtonClick() {
        String inputText = input.getText();
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
    }
}
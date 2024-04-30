package dci.j24e01.wordle.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class WordleController {


    private final Game game = new Game();
    public static Button[] buttons = new Button[26];
    private HBox[] boxes;
    private int line;
    @FXML
    private TextField input;
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
    private HBox buttonsrow0;
    @FXML
    private HBox buttonsrow1;
    @FXML
    private HBox buttonsrow2;
    @FXML
    private Button giveup;

    @FXML
    public void initialize() {
        restart();

        getButtonsArray(buttonsrow0, 0);
        getButtonsArray(buttonsrow1, 9);
        getButtonsArray(buttonsrow2, 17);
    }

    @FXML
    public void onEnterButtonClick() {
        String inputText = input.getText().toLowerCase();
        boolean won = false;

        setOnSuccess(inputText);

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
    public void onGiveUpButtonClick() {
        reset();
        restart();
        input.setText("");
    }

    public void restart() {
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
            resetBox(box);
        }
        for (Button button : buttons) {
            button.setStyle("");
        }
        giveup.setVisible(false);
    }

    @FXML
    private void letterButtonClicked(ActionEvent event) {
        Button letterButton = (Button) event.getSource();
        String letter = letterButton.getText().toLowerCase();
        input.setText(input.getText() + letter);
    }

    private Label[] getLabelsArray() {
        Label[] labels = new Label[5];
        for (int i = 0; i < 5; i++) {
            Label label = (Label) boxes[line].getChildren().get(i);
            labels[i] = label;
        }
        return labels;
    }

    private void getButtonsArray(HBox row, int startIndex) {
        for (int i = 0; i < row.getChildren().size(); i++) {
            buttons[i + startIndex] = (Button) row.getChildren().get(i);
        }
    }

    private void resetBox(HBox box) {
        for(int i = 0; i < 5; i++) {
            Label label = (Label) box.getChildren().get(i);
            label.setStyle("-fx-background-color: rgb(219, 219, 219);");
            label.setText("");
        }
    }

    private void setOnSuccess(String inputText) {
        if (game.checkWord(inputText)) {
            game.setContent(inputText, getLabelsArray());
            line++;
            if (game.guessed(inputText)) {
                game.endGame(true);
                reset();
                restart();
            }
        }
    }
}
package dci.j24e01.wordle.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class WordleController {

    private Game game;
    public static Button[] buttons = new Button[26];
    private HBox[] boxes;
    private int line;
    public static int wins;
    public static int loses;
    public static int rate;

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
    private HBox buttonsRow0;
    @FXML
    private HBox buttonsRow1;
    @FXML
    private HBox buttonsRow2;
    @FXML
    private Button giveUp;
    @FXML
    private Label losesLabel;
    @FXML
    private Label winsLabel;
    @FXML
    private Label winRateLabel;

    @FXML
    public void initialize() {

        restart();
        getButtonsArray(buttonsRow0, 0);
        getButtonsArray(buttonsRow1, 9);
        getButtonsArray(buttonsRow2, 17);
    }

    @FXML
    public void onEnterClick() {

        String inputText = input.getText().toLowerCase();
        boolean won = false;

        setOnSuccess(inputText);

        input.clear();
        if (line > 5 && !won) {
            loses++;
            updateWinRate();
            game.endGame(false);
            reset();
            restart();
        }
        if (line > 0) {
            giveUp.setVisible(true);
        }
    }

    @FXML
    private void letterButtonClicked(ActionEvent event) {
        Button letterButton = (Button) event.getSource();
        String letter = letterButton.getText().toLowerCase();
        input.setText(input.getText() + letter);
    }

    @FXML
    public void onGiveUpButtonClick() {
        reset();
        restart();
        loses++;
        updateWinRate();
        game.endGame(false);
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
            resetBox(box);
        }
        for (Button button : buttons) {
            button.setStyle("");
        }
        giveUp.setVisible(false);
        updateWinRate();
    }

    private void resetBox(HBox box) {
        for(int i = 0; i < 5; i++) {
            Label label = (Label) box.getChildren().get(i);
            label.setStyle("-fx-background-color: rgb(219, 219, 219);");
            label.setText("");
        }
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

    private void setOnSuccess(String inputText) {
        if (game.checkWord(inputText)) {
            game.setLabelsAndButtonsContent(inputText, getLabelsArray());
            line++;
            if (game.guessed(inputText)) {
                wins++;
                updateWinRate();
                game.endGame(true);
                reset();
                restart();
            }
        }
    }

    public void updateWinRate() {
        rate = (int) (100.0 / (wins + loses) * wins);
        winRateLabel.setText("Winrate: " + rate + "%");
        winsLabel.setText("Wins: " + wins);
        losesLabel.setText("Loses: " + loses);
        if (rate < 30) {
            winRateLabel.setTextFill(Color.RED);
        } else if (rate < 65) {
            winRateLabel.setTextFill(Color.DARKORANGE);
        } else {
            winRateLabel.setTextFill(Color.GREEN);
        }
    }
}
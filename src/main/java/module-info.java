module dci.j24e01.wordle.wordle {
    requires javafx.controls;
    requires javafx.fxml;


    opens dci.j24e01.wordle.wordle to javafx.fxml;
    exports dci.j24e01.wordle.wordle;
}
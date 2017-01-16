package panes;

import components.ComponentBuilder;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.Controller;

public class CalculatingPane extends VBox {

    private static TextField alphabet;
    private TextField inputWord;
    private Rule rule = new Rule();
    private Region btnAdd;
    private Region btnCalculate;

    public static final CalculatingPane instance = new CalculatingPane();

    private CalculatingPane() {
        setAlignment(Pos.CENTER);
        setSpacing(10);

        alphabet = new TextField();
        alphabet.setPromptText("Enter the alphabet");
        alphabet.setMinHeight(MainPane.FIELD_HEIGHT);
        alphabet.setFocusTraversable(false);

        inputWord = new TextField();
        inputWord.setPromptText("Enter input word");
        inputWord.setMinHeight(MainPane.FIELD_HEIGHT);
        inputWord.setFocusTraversable(false);

        btnAdd = ComponentBuilder.getButton("add", 16, 100, 30, 1, MainPane.TEXT_COLOR, MainPane.BUTTON_COLOR);
        btnCalculate = ComponentBuilder.getButton("calculate", 16, 100, 30, 1, MainPane.TEXT_COLOR, MainPane.BUTTON_COLOR);

        btnAdd.setOnMouseClicked(event -> Controller.add(rule.getLeftWord(), rule.getRightWord()));
        btnCalculate.setOnMouseClicked(event -> Controller.calculate());

        HBox buttons = new HBox(btnAdd, btnCalculate);
        buttons.setSpacing(20);

        getChildren().add(alphabet);
        getChildren().add(inputWord);
        getChildren().add(rule);
        getChildren().add(buttons);
    }

    private static class Rule extends HBox {

        private TextField leftWord;
        private TextField rightWord;
        private Label lblArrow;

        {
            setFillHeight(false);
            setAlignment(Pos.CENTER);
            setSpacing(10);

            leftWord = new TextField();
            rightWord = new TextField();

            leftWord.setFocusTraversable(false);
            rightWord.setFocusTraversable(false);

            leftWord.setMinHeight(MainPane.FIELD_HEIGHT);
            rightWord.setMinHeight(MainPane.FIELD_HEIGHT);

            leftWord.setPromptText("Left side");
            rightWord.setPromptText("Right side");

            lblArrow = new Label("→");
            lblArrow.setMinHeight(MainPane.FIELD_HEIGHT);
            lblArrow.setFont(Font.font(22));
            lblArrow.setMinWidth(22);
            lblArrow.setTextFill(Color.CORAL);
            lblArrow.setEffect(new DropShadow(5, Color.LIGHTCORAL));

            getChildren().add(leftWord);
            getChildren().add(lblArrow);
            getChildren().add(rightWord);
        }

        public TextField getLeftField() {
            return leftWord;
        }

        public TextField getRightField() {
            return rightWord;
        }

        public String getLeftWord() {
            return leftWord.getText();
        }

        public String getRightWord() {
            return rightWord.getText();
        }

    }

    public static TextField getAlphabet() {
        return alphabet;
    }

    public TextField getInputField() {
        return inputWord;
    }

    public TextField getLeftField() {
        return rule.getLeftField();
    }

    public TextField getRightField() {
        return rule.getRightField();
    }

    public Region getBtnAdd() {
        return btnAdd;
    }

    public Region getBtnCalculate() {
        return btnCalculate;
    }

    public TextField getLeft() {
        return this.rule.getLeftField();
    }

    public TextField getRight() {
        return this.rule.getRightField();
    }

}

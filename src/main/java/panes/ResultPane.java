package panes;

import javafx.beans.InvalidationListener;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.Controller;
import main.Rule;
import components.ComponentBuilder;

import java.util.List;

public class ResultPane extends VBox {

    private static class RulesList extends StackPane {

        private Label message = new Label("No rules");

        {
            message.setScaleX(2.0);
            message.setScaleY(2.0);
        }

        public RulesList(ListView<Rule> listView) {
            getChildren().add(message);
            getChildren().add(listView);
        }

    }

    public static ResultPane instance = new ResultPane();
    private static TextField outputWord;
    private static ListView<Rule> listView;
    private static Region btnRemove;
    private static boolean visible = true;

    private ResultPane() {
        setAlignment(Pos.CENTER);
        setSpacing(10);

        outputWord = new TextField();
        outputWord.setPromptText("Output word");
        outputWord.setMinHeight(MainPane.FIELD_HEIGHT);
        outputWord.setFocusTraversable(false);
        outputWord.setEditable(false);

        listView = new ListView<>();
        listView.setFocusTraversable(false);
        listView.getItems().addListener((InvalidationListener) observable -> setListVisible(!listView.getItems().isEmpty()));

        btnRemove = ComponentBuilder.getButton("remove", MainPane.BUTTON_OPACITY, MainPane.TEXT_COLOR, MainPane.BUTTON_COLOR);
        btnRemove.setOnMouseClicked(event -> Controller.remove(listView.getSelectionModel().getSelectedIndex(), listView));



        setListVisible(false);

        getChildren().add(outputWord);
        getChildren().add(new RulesList(listView));
        getChildren().add(btnRemove);
    }

    public void add(Rule rule) {
        listView.getItems().add(rule);
    }

    public List<Rule> getRules() {
        return listView.getItems();
    }

    public static void setListVisible(boolean flag) {
        if (visible == flag) return;
        visible = flag;
        outputWord.setVisible(flag);
        listView.setVisible(flag);
        btnRemove.setVisible(flag);
    }

    public static TextField getOutputField() {
        return outputWord;
    }

    public Region getBtnRemove() {
        return btnRemove;
    }

    public static ListView<Rule> getListView() {
        return listView;
    }
}

package main;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import panes.CalculatingPane;
import panes.ResultPane;

import java.util.Collections;
import java.util.HashSet;

public class Controller {

    private static boolean calcLock;
    private static boolean isRun;
    public static HashSet<String> alphabet = new HashSet<>();
    private static String input;

    public static void add(String leftWord, String rightWord) {
        ResultPane.instance.add(new Rule(leftWord, rightWord));
    }

    public static void calculate() {
        new Thread(() -> {

            if (calcLock) return;
            calcLock = true;
            isRun = true;

            Controller.setAlphabet(CalculatingPane.getAlphabet().getText().replaceAll(" ", "").split(","));

            String inputText = CalculatingPane.instance.getInputField().getText();
            TextField output = ResultPane.getOutputField();

            if (wordBelongToAlphabet(inputText)) {
                output.setText(getResult(inputText));

                boolean f = Controller.wordBelongToAlphabet(ResultPane.getOutputField().getText());
                if (!f) {
                    Platform.runLater(() -> Notifications.create()
                            .text("Output word doesn't belong to the alphabet!")
                            .hideAfter(Duration.seconds(5))
                            .showWarning());
                }
            } else {
                Platform.runLater(() -> Notifications.create()
                        .text("Input word doesn't belong to the alphabet!")
                        .hideAfter(Duration.seconds(5))
                        .showWarning());
            }

            isRun = false;
            calcLock = false;
        }).start();
    }

    private static String getResult(String input) {
        for (Rule r : ResultPane.instance.getRules()) {

            if (input.contains(r.getLeft())) {

                if (!r.getRight().isEmpty() && r.getRight().charAt(0) == '.') {
                    return input.replaceFirst(r.getLeft(), r.getRight().substring(1));
                }

                return getResult(input.replaceFirst(r.getLeft(), r.getRight()));
            }

        }
        return input;
    }

    public static void remove(int index, ListView<Rule> listView) {
        if (index == -1) return;
        listView.getItems().remove(index);
    }

    public static boolean wordBelongToAlphabet(String word) {
        for (int i = 0; i < word.length(); i++)
            if (!contains(Controller.alphabet, String.valueOf(word.charAt(i))))
                return false;
        return true;
    }

    public static void stop() {
        isRun = false;
    }

    public static void setAlphabet(String[] alphabet) {
        Controller.alphabet.clear();
        Controller.alphabet.add("/");
        Collections.addAll(Controller.alphabet, alphabet);
    }

    static boolean contains(HashSet<String> set, String input) {
        for (String s : set) {
            if (s.equals(input)) return true;
        }
        return false;
    }

}

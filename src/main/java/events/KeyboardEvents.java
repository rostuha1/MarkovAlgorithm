package events;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import panes.CalculatingPane;
import panes.ControlPane;
import panes.ResultPane;

public class KeyboardEvents {

    private static TextField input = CalculatingPane.instance.getInputField();
    private static TextField left = CalculatingPane.instance.getLeftField();
    private static TextField right = CalculatingPane.instance.getRightField();

    private static Region add = CalculatingPane.instance.getBtnAdd();
    private static Region calculate = CalculatingPane.instance.getBtnCalculate();
    private static Region remove = ResultPane.instance.getBtnRemove();
    private static Region stop = ControlPane.instance.getBtnStop();

    private static int fieldIndex;

    public static void setAction(Scene scene) {

        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.TAB) getNextField().requestFocus();
        });

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case CONTROL: {
                    Mouse.click(add);
                    left.clear();
                    right.clear();
                    fieldIndex = 2;
                    left.requestFocus();
                }
                break;
                case ENTER:
                    Mouse.click(calculate);
                    break;
                case BACK_SPACE:
                    Mouse.click(remove);
                    break;
                case ESCAPE:
                    Mouse.click(stop);
                    break;
            }
        });

    }

    private static TextField getNextField() {
        TextField res = null;
        switch (fieldIndex) {
            case 0:
                res = input;
                break;
            case 1:
                res = left;
                break;
            case 2:
                res = right;
                break;
        }
        fieldIndex++;
        if (fieldIndex > 2) fieldIndex = 0;
        return res;
    }

}

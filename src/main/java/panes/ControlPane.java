package panes;

import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.Controller;
import components.ComponentBuilder;

public class ControlPane extends VBox {

    private Region btnStop;

    public static ControlPane instance = new ControlPane();

    private ControlPane() {
        setAlignment(Pos.CENTER);
        setSpacing(10);

        btnStop = ComponentBuilder.getButton("stop", MainPane.BUTTON_OPACITY, MainPane.TEXT_COLOR, MainPane.BUTTON_COLOR);
        btnStop.setOnMouseClicked(event -> Controller.stop());

        getChildren().add(SpeedChanger.instance);
        getChildren().add(btnStop);
    }

    public Region getBtnStop() {
        return btnStop;
    }


}

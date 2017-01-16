package panes;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MainPane extends HBox {

    public static final double BUTTON_OPACITY = 0.5;
    public static final Color BUTTON_COLOR = Color.web("0x195519");
    public static final Color TEXT_COLOR = Color.web("0x58B858");
    public static final int FIELD_HEIGHT = 30;

    public static final MainPane instance = new MainPane();

    private MainPane() {
        setAlignment(Pos.CENTER);
        setMaxSize(0, 0);
        setSpacing(10);

        getChildren().add(CalculatingPane.instance);
//        getChildren().add(ControlPane.instance);
        getChildren().add(ResultPane.instance);

        setScaleX(1.5);
        setScaleY(1.5);
    }

}

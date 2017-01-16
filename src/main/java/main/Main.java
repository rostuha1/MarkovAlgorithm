package main;

import animation.AnimatedCircles;
import events.KeyboardEvents;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import panes.MainPane;
import panes.MenuPane;

public class Main extends Application {

    private static BorderPane root = new BorderPane();
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        root.setPrefSize(WindowSettings.width, WindowSettings.height);
        scene = new Scene(root);

        primaryStage.setFullScreen(WindowSettings.fullscreen);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setScene(scene);

        appInit();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void appInit() {

        root.setStyle("-fx-background-color: rgb(35, 40, 30)");
        AnimatedCircles.createSpawnNodes(root);

        root.setTop(new MenuPane());
        root.setCenter(MainPane.instance);
        KeyboardEvents.setAction(scene);

    }

    public static Scene getScene() {
        return scene;
    }
}

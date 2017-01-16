package panes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import main.Controller;
import main.Main;
import main.Rule;
import org.controlsfx.control.Notifications;

import java.io.*;

public class MenuPane extends VBox {

    private MenuBar menuBar = new MenuBar();
    private String text =
            "Нормальний алгоритм Маркова (НАМ) обчислює\n" +
            "вихідне слово на основі заданого алфавіту,\n" +
            "вхідного слова, та набору правил.\n" +
            "На початку кожного слова стоїть символ '/'.\n" +
            "Символи алфавіту потрібно вводити через кому.";

    public MenuPane() {

        menuBar.setStyle("-fx-font-size: 16");

        Menu file = new Menu("File");
        Menu help = new Menu("Help");

        MenuItem open = new MenuItem("Open file");
        MenuItem save = new MenuItem("Save file");
        MenuItem about = new MenuItem("About");

        open.setOnAction(event -> open());
        save.setOnAction(event -> save());
        about.setOnAction(event -> about());

        file.getItems().addAll(open, save);
        help.getItems().addAll(about);

        menuBar.getMenus().addAll(file, help);

        getChildren().addAll(menuBar);

    }

    private void about() {
        Notifications.create()
                .text(text)
                .hideAfter(Duration.seconds(10))
                .showInformation();
    }

    private void save() {
        int size = ResultPane.getListView().getItems().size();

        try {
            OutputStream f = new FileOutputStream("file" + (int) (Math.random() * 100) + ".txt", true);
            OutputStreamWriter writer = new OutputStreamWriter(f);
            BufferedWriter out = new BufferedWriter(writer);
            out.write(CalculatingPane.getAlphabet().getText() + "\n");
            for (int i = 0; i < size; i++) {
                out.write(ResultPane.getListView().getItems().get(i).toString() + "\n");
                out.flush();
            }
        } catch (IOException ex) {}
    }

    private void open() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(Main.getScene().getWindow());
        if (selectedFile != null) {
            ObservableList<Rule> rules = FXCollections.observableArrayList();

            FileInputStream fstream = null;
            try {
                fstream = new FileInputStream(selectedFile);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    if (strLine.contains("→")) {
                        int arrowIndex = strLine.indexOf('→');
                        String left = strLine.substring(0, arrowIndex - 1).trim();
                        String right = strLine.substring(arrowIndex + 1).trim();
                        rules.add(new Rule(left, right));

                    } else {
                        CalculatingPane.getAlphabet().setText(strLine);
                        Controller.setAlphabet(strLine.replaceAll(" ", "").split(","));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fstream != null) fstream.close();
                } catch (Exception ignore) {
                }
            }

            ResultPane.getOutputField().clear();
            CalculatingPane.instance.getInputField().clear();
            CalculatingPane.instance.getLeft().clear();
            CalculatingPane.instance.getRight().clear();

            ResultPane.getListView().setItems(rules);
            ResultPane.setListVisible(true);
        }
    }


}

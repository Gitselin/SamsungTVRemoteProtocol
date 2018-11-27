package View;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.lang.invoke.SwitchPoint;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

public class GUIMainScene extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("[Screen Name]");

        ArrayList<BorderPane> settings = new ArrayList<>(getPanels());

        FlowPane flowPane = new FlowPane(Orientation.VERTICAL,64,16);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.getChildren().addAll(settings);

        Label csInfoLabel = new Label("Current Screen");
        Label screenText = new Label("ME46C");
        Label powerInfo = new Label("Current Power status: Screen Turned on for 2h 34m");

        Button dbButton = new Button("Access DB logs");
        Button SaveChangesButton = new Button("Save Changes");
        dbButton.setPrefSize(256,64);
        SaveChangesButton.setPrefSize(256,64);

        VBox topBox = new VBox();
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(csInfoLabel,screenText,powerInfo);
        topBox.setPrefSize(300,200);
        topBox.setStyle("-fx-background-color: #eaeaea"); //TODO Access by ID instead

        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(SaveChangesButton,dbButton);
        bottomBox.setPrefSize(300,200);
        bottomBox.setSpacing(16);
        bottomBox.setStyle("-fx-background-color: #eaeaea"); //TODO Access by ID instead

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(flowPane);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 1980, 1080);
        File css = new File("res/css/minimal.css");
        try {
            scene.getStylesheets().add(css.toURI().toURL().toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ArrayList<BorderPane> getPanels(){
        ArrayList<BorderPane> panels = new ArrayList<>();
        int prefButtonWidth = 128;
        int prefButtonHeight = 64;
        Random r = new Random();

        String[] settingLabels = {"Power","Volume","Brightness","Direction","Construction Info","Perform Checkup","Network Connection","Consumption","etc"}; //TODO remove this after JSON implementation

        for(int i = 0; i < 8; i++){ //TODO Loop the length of the JSON object list

            Label text = new Label(settingLabels[i]);
            text.setFont(new Font("Arial",24));

            HBox rightBox = new HBox();
            rightBox.setSpacing(16);
            rightBox.setAlignment(Pos.CENTER_RIGHT);

            int switchExample = r.nextInt(3)+1; //TODO Replace this switch with JSON GUI identifier
            System.out.println(switchExample);
            switch (switchExample){
                case 1:
                    ToggleSwitch toggleSwitch = new ToggleSwitch();
                    toggleSwitch.setPrefSize(prefButtonWidth,prefButtonHeight);
                    rightBox.getChildren().add(toggleSwitch);
                    break;
                case 2:
                    ObservableList<String> options = FXCollections.observableArrayList("Option 1", "Option 2", "Option 3");
                    ComboBox comboBox = new ComboBox(options);
                    comboBox.setPrefSize(prefButtonWidth,prefButtonHeight);
                    rightBox.getChildren().add(comboBox);
                    break;
                default:

            }

            HBox leftBox = new HBox();
            leftBox.setSpacing(16);
            leftBox.setAlignment(Pos.CENTER_LEFT);

            leftBox.getChildren().addAll(text);

            BorderPane settingsPane = new BorderPane();
            settingsPane.setPrefSize(400,64);
            settingsPane.setLeft(leftBox);
            settingsPane.setRight(rightBox);
            settingsPane.setStyle("-fx-border-width: 2");

            panels.add(settingsPane);
        }

        return panels;
    }

    private void dialogOption(int choice){
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("True False Dialog");
    }
}

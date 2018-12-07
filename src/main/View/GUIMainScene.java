package View;

import Controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GUIMainScene extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String testResponse = "null";
        ArrayList<String> statusTexts = new ArrayList<>();
        try {
            Controller ctrl = new Controller();
            String[] keylist = ctrl.getGET_KEY_LIST();
            String keyToSend = keylist[0];
            String[][] response = ctrl.sendRequest(keyToSend);
            testResponse = Arrays.deepToString(response[1]);
            statusTexts.addAll(Arrays.asList(response[1]).subList(6, Integer.parseInt(response[1][3]) + 4));
        } catch (ParseException | InterruptedException | IOException e){
            e.printStackTrace();
        }



        primaryStage.setTitle("Screen Controller - ME46C");

        ArrayList<BorderPane> maintenancePanels = new ArrayList<>(getPanels()); //Generate Settings

        FlowPane maintenancePane = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        maintenancePane.setAlignment(Pos.CENTER);
        maintenancePane.getChildren().addAll(maintenancePanels);
        maintenancePane.setId("sp1");

        ArrayList<BorderPane> picturePanels = new ArrayList<>(getPanels()); //Generate Settings TODO this is a copy, replace this

        FlowPane picturePane = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        picturePane.setAlignment(Pos.CENTER);
        picturePane.getChildren().addAll(picturePanels);
        picturePane.setId("sp2");


        ArrayList<BorderPane> miscPanels = new ArrayList<>(getPanels()); //Generate Settings TODO this is a copy, replace this

        FlowPane settingsPane2 = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        settingsPane2.setAlignment(Pos.CENTER);
        settingsPane2.getChildren().addAll(miscPanels);
        settingsPane2.setId("sp3");


        ArrayList<BorderPane> dbPanels = new ArrayList<>(getPanels()); //Generate Settings TODO this is a copy, replace this

        FlowPane dbPane = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        dbPane.setAlignment(Pos.CENTER);
        dbPane.getChildren().addAll(dbPanels);
        dbPane.setId("sp4");

        StackPane settingsPanes = new StackPane();
        settingsPanes.getChildren().addAll(maintenancePane,picturePane,settingsPane2,dbPane);
        for(Node node: settingsPanes.getChildren()){
            if (node.getId().equals("sp1")){
                node.setVisible(true);
            }else {
                node.setVisible(false);
            }
        }

        Label csInfoLabel = new Label(testResponse); //Setup top labels
        Label screenText = new Label("ME46C");
        Label powerInfo = new Label("Current Power status: Screen Turned on for 2h 34m /n New line");

        TextArea statusArea = new TextArea();
        statusArea.setEditable(false);
        statusArea.setPrefSize(128,128);
        statusArea.setWrapText(true);
        for(int i = 0; i < statusTexts.size(); i++){
            statusArea.appendText("Val " + (i+1) + ": " + statusTexts.get(i));
            if (i+1!=statusTexts.size()){
                statusArea.appendText("\n");
            }
        }


        ToggleGroup toggleGroup = new ToggleGroup(); //Setup of toggle buttons

        ToggleButton maintenanceButton = new ToggleButton("Maintenance");
        maintenanceButton.setPrefSize(256,64);
        maintenanceButton.setToggleGroup(toggleGroup);
        maintenanceButton.setUserData(maintenancePane.getId());
        maintenanceButton.setSelected(true);

        ToggleButton pictureButton = new ToggleButton("Picture");
        pictureButton.setPrefSize(256,64);
        pictureButton.setToggleGroup(toggleGroup);
        pictureButton.setUserData(picturePane.getId());

        ToggleButton miscButton = new ToggleButton("Miscellaneous");
        miscButton.setPrefSize(256,64);
        miscButton.setToggleGroup(toggleGroup);
        miscButton.setUserData(settingsPane2.getId());

        ToggleButton dbButton = new ToggleButton("Database");
        dbButton.setPrefSize(256,64);
        dbButton.setToggleGroup(toggleGroup);
        dbButton.setUserData(dbPane.getId());

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() { //Toggle listner
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(newValue == null){
                    for(Node node: settingsPanes.getChildren()){
                        node.setVisible(false);
                    }
                } else {
                    String valueOfToggle = (String)toggleGroup.getSelectedToggle().getUserData();
                    for (Node node: settingsPanes.getChildren()) {
                        if(node.getId().equals(valueOfToggle)){
                            node.setVisible(true);
                        } else {
                            node.setVisible(false);
                        }
                    }

                }
            }
        });

        //------- FINAL SCENE SETUP ------- (No new nodes below this line)

        VBox topBox = new VBox(); //Setup of Top text components
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.getChildren().addAll(csInfoLabel,screenText,powerInfo);

        HBox buttonBox = new HBox(); //Setup of button components
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(maintenanceButton,pictureButton,miscButton,dbButton);
        buttonBox.setSpacing(16);

        BorderPane topPane = new BorderPane(); //Top pane for Info labels and buttons
        //topPane.setLeft(topBox);
        topPane.setBottom(buttonBox);
        topPane.setTop(statusArea);
        topPane.setStyle("-fx-background-color: #eaeaea"); //TODO Access by ID instead

        BorderPane root = new BorderPane(); //Setting up Root pane
        root.setTop(topPane);
        root.setCenter(settingsPanes);

        Scene scene = new Scene(root, 1980, 1080); //Creating Scene
        File css = new File("res/css/minimal.css"); //Adding CSS
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

            Label text = new Label(settingLabels[r.nextInt(8)]);
            text.setFont(new Font("Arial",24));

            HBox rightBox = new HBox();
            rightBox.setSpacing(16);
            rightBox.setAlignment(Pos.CENTER_RIGHT);

            int switchExample = r.nextInt(4)+1; //TODO Replace this switch with JSON GUI identifier
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
                case 3:
                    Slider slider = new Slider();
                    slider.setMin(0);
                    slider.setMax(100);
                    slider.setShowTickLabels(true);
                    slider.setShowTickMarks(true);
                    slider.setMajorTickUnit(50);
                    slider.setMinorTickCount(5);
                    slider.setBlockIncrement(10);
                    slider.setPrefSize(prefButtonWidth,prefButtonHeight);
                    rightBox.getChildren().add(slider);
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
}

package View;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import sun.reflect.generics.tree.Tree;

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

        ArrayList<BorderPane> settings = new ArrayList<>(getPanels()); //Generate Settings

        FlowPane settingsPane = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        settingsPane.setAlignment(Pos.CENTER);
        settingsPane.getChildren().addAll(settings);
        settingsPane.setId("sp1");

        ArrayList<BorderPane> settings1 = new ArrayList<>(getPanels()); //Generate Settings TODO this is a copy, replace this

        FlowPane settingsPane1 = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        settingsPane1.setAlignment(Pos.CENTER);
        settingsPane1.getChildren().addAll(settings1);
        settingsPane1.setId("sp2");


        ArrayList<BorderPane> settings2 = new ArrayList<>(getPanels()); //Generate Settings TODO this is a copy, replace this

        FlowPane settingsPane2 = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        settingsPane2.setAlignment(Pos.CENTER);
        settingsPane2.getChildren().addAll(settings2);
        settingsPane2.setId("sp3");


        ArrayList<BorderPane> settings3 = new ArrayList<>(getPanels()); //Generate Settings TODO this is a copy, replace this

        FlowPane settingsPane3 = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        settingsPane3.setAlignment(Pos.CENTER);
        settingsPane3.getChildren().addAll(settings3);
        settingsPane3.setId("sp4");


        ArrayList<BorderPane> settings4 = new ArrayList<>(getPanels()); //Generate Settings TODO this is a copy, replace this

        FlowPane settingsPane4 = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        settingsPane4.setAlignment(Pos.CENTER);
        settingsPane4.getChildren().addAll(settings4);
        settingsPane4.setId("sp5");

        StackPane settingsPanes = new StackPane();
        settingsPanes.getChildren().addAll(settingsPane,settingsPane1,settingsPane2,settingsPane3,settingsPane4);
        for(Node node: settingsPanes.getChildren()){
            if (node.getId().equals("sp1")){
                node.setVisible(true);
            }else {
                node.setVisible(false);
            }
        }

        Label csInfoLabel = new Label("Current Screen"); //Setup top labels
        Label screenText = new Label("ME46C");
        Label powerInfo = new Label("Current Power status: Screen Turned on for 2h 34m");

        ToggleGroup toggleGroup = new ToggleGroup(); //Setup of toggle buttons

        ToggleButton powerMenu = new ToggleButton("Power");
        powerMenu.setPrefSize(256,64);
        powerMenu.setToggleGroup(toggleGroup);
        powerMenu.setUserData(settingsPane.getId());
        powerMenu.setSelected(true);

        ToggleButton statusMenu = new ToggleButton("Status");
        statusMenu.setPrefSize(256,64);
        statusMenu.setToggleGroup(toggleGroup);
        statusMenu.setUserData(settingsPane1.getId());

        ToggleButton advancedMenu = new ToggleButton("Advanced Control");
        advancedMenu.setPrefSize(256,64);
        advancedMenu.setToggleGroup(toggleGroup);
        advancedMenu.setUserData(settingsPane2.getId());

        ToggleButton dbButton = new ToggleButton("Access DB logs");
        dbButton.setPrefSize(256,64);
        dbButton.setToggleGroup(toggleGroup);
        dbButton.setUserData(settingsPane3.getId());

        //TODO Save changes shouldn't be a menu option
        ToggleButton SaveChangesButton = new ToggleButton("Save Changes");
        SaveChangesButton.setPrefSize(256,64);
        SaveChangesButton.setToggleGroup(toggleGroup);
        SaveChangesButton.setUserData(settingsPane4.getId());

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
        buttonBox.getChildren().addAll(powerMenu,statusMenu,advancedMenu,dbButton,SaveChangesButton);
        buttonBox.setSpacing(16);

        BorderPane topPane = new BorderPane(); //Top pane for Info labels and buttons
        topPane.setTop(topBox);
        topPane.setBottom(buttonBox);
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

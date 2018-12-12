package View;

import Controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class GUIMainScene extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ArrayList<String> statusTexts = new ArrayList<>();
        try {
            Controller ctrl = new Controller();
            String[] keylist = ctrl.getKEY_LIST();
            String keyToSend = keylist[0];
            String[][] response = ctrl.sendRequest(keyToSend);

            statusTexts.addAll(Arrays.asList(response[1]).subList(6, Integer.parseInt(response[1][3]) + 4));
        } catch (SocketException e){
            statusTexts.add(e.toString());
            e.printStackTrace();
        }
        catch (ParseException | InterruptedException | IOException e){
            statusTexts.add(e.toString());
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
        int prefButtonWidth = 156;
        int prefButtonHeight = 64;
        Random r = new Random();

        String[] settingLabels = {"Power","Volume","Brightness","Direction","Construction Info","Perform Checkup","Network Connection","Consumption","etc"}; //TODO remove this after JSON implementation

        for(int i = 0; i < 6; i++){ //TODO Loop the length of the JSON object list

            Label text = new Label(settingLabels[r.nextInt(8)]);
            text.setFont(new Font("Arial",24));

            HBox rightBox = new HBox();
            rightBox.setSpacing(16);
            rightBox.setAlignment(Pos.CENTER_RIGHT);

            Button changeSettings = new Button();
            changeSettings.setText("Change Setting");
            changeSettings.setPrefSize(prefButtonWidth,prefButtonHeight);


            switch (r.nextInt(3)+1){
                case 1:
                    Dialog toggleDialog = new Dialog();
                    toggleDialog.setTitle("Toggle Window");
                    toggleDialog.setHeaderText("Change [SETTING]");

                    ButtonType saveButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                    toggleDialog.getDialogPane().getButtonTypes().addAll(saveButton,ButtonType.CANCEL);

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20,150,10,10));

                    ToggleButton button = new ToggleButton();
                    grid.add(new Label("Toggle:"),0,0);
                    grid.add(button,0,1);

                    button.setSelected(false);

                    toggleDialog.getDialogPane().setContent(grid);

                    toggleDialog.setResult(button.isSelected());

                    toggleDialog.setResultConverter(dialogButton -> {
                        if(dialogButton == saveButton){
                            return button.isSelected();
                        }
                        return null;
                    });

                    changeSettings.setOnAction(event -> {
                        Optional<Boolean> result = toggleDialog.showAndWait();
                        System.out.println("Value: " + result.get().booleanValue());
                    });
                    break;
                case 2:
                    Dialog dropDownDialog = new Dialog();
                    dropDownDialog.setTitle("Toggle Window");
                    dropDownDialog.setHeaderText("Change [SETTING]");

                    ButtonType saveButtonDrop = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                    dropDownDialog.getDialogPane().getButtonTypes().addAll(saveButtonDrop,ButtonType.CANCEL);

                    GridPane gridDrop = new GridPane();
                    gridDrop.setHgap(10);
                    gridDrop.setVgap(10);
                    gridDrop.setPadding(new Insets(20,150,10,10));

                    Slider slider = new Slider();
                    slider.setMin(0);
                    slider.setMax(100);
                    slider.setValue(50); //get value from protocol TODO
                    slider.setShowTickLabels(true);
                    slider.setShowTickMarks(true);
                    slider.setMajorTickUnit(50);
                    slider.setMinorTickCount(5);
                    slider.setBlockIncrement(10);

                    gridDrop.add(new Label("Slider:"),0,0);
                    gridDrop.add(slider,0,1);

                    dropDownDialog.getDialogPane().setContent(gridDrop);

                    dropDownDialog.setResult(slider.getValue());

                    dropDownDialog.setResultConverter(dialogButton -> {
                        if(dialogButton == saveButtonDrop){
                            return slider.getValue();
                        }
                        return null;
                    });

                    changeSettings.setOnAction(event -> {
                        Optional<Boolean> result = dropDownDialog.showAndWait();
                        System.out.println("Value: " + result);
                    });
                    break;
                case 3:
                    Dialog comboBoxDialog = new Dialog();
                    comboBoxDialog.setTitle("Toggle Window");
                    comboBoxDialog.setHeaderText("Change [SETTING]");

                    ButtonType saveButtonCombo = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                    comboBoxDialog.getDialogPane().getButtonTypes().addAll(saveButtonCombo,ButtonType.CANCEL);

                    GridPane gridCombo = new GridPane();
                    gridCombo.setHgap(10);
                    gridCombo.setVgap(10);
                    gridCombo.setPadding(new Insets(20,150,10,10));

                    ObservableList<String> options =
                            FXCollections.observableArrayList(
                                    "Put in",
                                    "Different",
                                    "Items",
                                    "Here"
                            );
                    final ComboBox comboBox = new ComboBox(options);

                    gridCombo.add(new Label("Combo:"),0,0);
                    gridCombo.add(comboBox,0,1);

                    comboBoxDialog.getDialogPane().setContent(gridCombo);

                    comboBoxDialog.setResult(comboBox.getValue());

                    comboBoxDialog.setResultConverter(dialogButton -> {
                        if(dialogButton == saveButtonCombo){
                            return comboBox.getValue();
                        }
                        return null;
                    });

                    changeSettings.setOnAction(event -> {
                        Optional<Boolean> result = comboBoxDialog.showAndWait();
                        System.out.println("Value: " + result);
                    });
                    break;
                default:

            }

            rightBox.getChildren().add(changeSettings);

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

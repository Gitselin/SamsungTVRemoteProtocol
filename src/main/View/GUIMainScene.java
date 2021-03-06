package View;

import Controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.util.*;

public class GUIMainScene extends Application {
    Controller ctrl = new Controller();
    File css = new File("res/css/minimal.css"); //Adding CSS

    public GUIMainScene() throws IOException, ParseException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ArrayList<String> statusInfo = new ArrayList<>();
        ArrayList<String> statusLabel = new ArrayList<>();

        ArrayList<String> videoInfo = new ArrayList<>();
        ArrayList<String> videoLabel = new ArrayList<>();

        try {
            String[] keylist = ctrl.getGET_KEY_LIST();

            String requestStatus = keylist[0];

            String[][] responseStatus = ctrl.sendRequest(requestStatus);
            statusLabel.addAll(Arrays.asList(responseStatus[0]).subList(6, Integer.parseInt(responseStatus[1][3]) + 4));
            statusInfo.addAll(Arrays.asList(responseStatus[1]).subList(6, Integer.parseInt(responseStatus[1][3]) + 4));

            for (int i = 2; i < keylist.length; i++) {
                String[][] responseVideo = ctrl.sendRequest(keylist[i]);
                videoLabel.addAll(Collections.singleton(responseVideo[0][6]));
                videoInfo.addAll(Collections.singleton(responseVideo[1][6]));
        }


        } catch (IOException e){
            statusInfo.add(e.toString());
            e.printStackTrace();
        }


        primaryStage.setTitle("Avian Screen Management - ME46C");

        Image logo = new Image("file:res/images/avian.png");
        ImageView logoView = new ImageView();
        logoView.setImage(logo);
        logoView.setFitHeight(128);
        logoView.setFitWidth(128);

        ArrayList<BorderPane> maintenancePanels = new ArrayList<>(getPanels(videoLabel,videoInfo)); //Generate Settings

        FlowPane maintenancePane = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        maintenancePane.setAlignment(Pos.CENTER);
        maintenancePane.getChildren().addAll(maintenancePanels);
        maintenancePane.setId("sp1");

        ArrayList<BorderPane> picturePanels = new ArrayList<>(getPanels(videoLabel,videoInfo)); //Generate Settings TODO this is a copy, replace this

        FlowPane picturePane = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        picturePane.setAlignment(Pos.CENTER);
        picturePane.getChildren().addAll(picturePanels);
        picturePane.setId("sp2");


        ArrayList<BorderPane> miscPanels = new ArrayList<>(getPanels(videoLabel,videoInfo)); //Generate Settings TODO this is a copy, replace this

        FlowPane miscPane = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        miscPane.setAlignment(Pos.CENTER);
        miscPane.getChildren().addAll(miscPanels);
        miscPane.setId("sp3");
        ArrayList<BorderPane> dbPanels = new ArrayList<>(getPanels(videoLabel,videoInfo)); //Generate Settings TODO this is a copy, replace this

        FlowPane dbPane = new FlowPane(Orientation.VERTICAL,64,16); //Setup Settings pane
        dbPane.setAlignment(Pos.CENTER);
        dbPane.getChildren().addAll(dbPanels);
        dbPane.setId("sp4");

        StackPane settingsPanes = new StackPane();
        settingsPanes.getChildren().addAll(maintenancePane,picturePane,miscPane,dbPane);
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
        for(int i = 0; i < statusInfo.size(); i++){
            statusArea.appendText(statusLabel.get(i) + ": " + statusInfo.get(i));
            if (i+1!=statusInfo.size()){
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
        miscButton.setUserData(miscPane.getId());

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

        HBox topBox = new HBox();
        topBox.getChildren().addAll(logoView,statusArea);
        topBox.setSpacing(16);

        BorderPane topPane = new BorderPane(); //Top pane for Info labels and buttons
        topPane.setTop(topBox);
        topPane.setBottom(buttonBox);
        topPane.setStyle("-fx-background-color: #eaeaea"); //TODO Access by ID instead

        BorderPane root = new BorderPane(); //Setting up Root pane
        root.setTop(topPane);
        root.setCenter(settingsPanes);

        Scene scene = new Scene(root, 1980, 1080); //Creating Scene
        try {
            scene.getStylesheets().add(css.toURI().toURL().toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        primaryStage.setScene(scene);
        primaryStage.show();
        }

        private ArrayList<BorderPane> getPanels(ArrayList<String> labels, ArrayList<String> statusArray){
            ArrayList<BorderPane> panels = new ArrayList<>();
            int prefButtonWidth = 156;
            int prefButtonHeight = 64;
            int prefWindowWidth = 768;
            int prefWindowHeight = 512;

            for(int i = 0; i < labels.size(); i++){
                Label text = new Label(labels.get(i));
                text.setFont(new Font("Arial",24));
                int currentSetting = i+2;

                HBox rightBox = new HBox();
                rightBox.setSpacing(16);
                rightBox.setAlignment(Pos.CENTER_RIGHT);

                Button changeSettings = new Button();
                changeSettings.setText("Edit");
                changeSettings.setPrefSize(prefButtonWidth,prefButtonHeight);

                Label status = new Label(statusArray.get(i));

                String choice;
                if (statusArray.get(i).equalsIgnoreCase("on") || statusArray.get(i).equalsIgnoreCase("off") && !labels.get(i).equals("Color Tone"))
                {
                    choice = "boolean";
                } else if(statusArray.get(i).chars().allMatch( Character::isDigit)){
                    choice = "slider";
                } else {
                    choice = "combobox";
                }

                switch (choice){
                    case "boolean":
                        Dialog toggleDialog = new Dialog();
                        toggleDialog.setTitle("Avian - Change " + labels.get(i));
                        toggleDialog.setHeaderText("Change " + labels.get(i));
                        toggleDialog.getDialogPane().setPrefSize(prefWindowWidth,prefWindowHeight);

                        ButtonType saveButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                        toggleDialog.getDialogPane().getButtonTypes().addAll(saveButton,ButtonType.CANCEL);
                        try {
                            toggleDialog.getDialogPane().getStylesheets().add(css.toURI().toURL().toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        GridPane grid = new GridPane();
                        grid.setHgap(10);
                        grid.setVgap(10);
                        grid.setPadding(new Insets(20,150,10,10));

                        ToggleButton button = new ToggleButton();
                        button.setPrefSize(256,128);
                        grid.add(new Label("Toggle:"),0,0);
                        grid.add(button,0,1);

                        if (statusArray.get(i).toLowerCase().equals("on")){
                            button.setSelected(true);
                        } else {
                            button.setSelected(false);
                        }
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
                            if (result.get()){
                                int[] value = {0};
                                sendValue(currentSetting,value);
                                status.setText("On");
                            } else {
                                int[] value = {1};
                                sendValue(currentSetting,value);
                                status.setText("Off");
                            }
                        });
                        break;
                    case "slider":
                        Dialog sliderDialog = new Dialog();
                        sliderDialog.setTitle("Avian - Change " + labels.get(i));
                        sliderDialog.setHeaderText("Change " + labels.get(i));
                        sliderDialog.getDialogPane().setPrefSize(prefWindowWidth,prefWindowHeight);
                        try {
                            sliderDialog.getDialogPane().getStylesheets().add(css.toURI().toURL().toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        ButtonType saveButtonDrop = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                        sliderDialog.getDialogPane().getButtonTypes().addAll(saveButtonDrop,ButtonType.CANCEL);

                        GridPane gridDrop = new GridPane();
                        gridDrop.setHgap(10);
                        gridDrop.setVgap(10);
                        gridDrop.setPadding(new Insets(20,150,10,10));

                        Slider slider = new Slider();
                        slider.setMin(0);
                        slider.setMax(100);
                        slider.setValue(Integer.parseInt(statusArray.get(i)));
                        slider.setShowTickLabels(true);
                        slider.setShowTickMarks(true);
                        slider.setMajorTickUnit(5);
                        slider.setMinorTickCount(0);
                        slider.setBlockIncrement(10);
                        slider.setPrefSize(512,256);

                        gridDrop.add(new Label("Slider:"),0,0);
                        gridDrop.add(slider,0,1);

                        sliderDialog.getDialogPane().setContent(gridDrop);

                        sliderDialog.setResult(slider.getValue());

                        sliderDialog.setResultConverter(dialogButton -> {
                                if(dialogButton == saveButtonDrop){
                                    int calculation = Math.round((long)slider.getValue());
                                        return calculation;
                                    }
                                    return null;
                                });

                                changeSettings.setOnAction(event -> {
                                    Optional<Integer> result = sliderDialog.showAndWait();
                                    status.setText(result.get().toString());
                                    int[] value = {result.get()};
                                    Platform.runLater( () -> sendValue(currentSetting,value));
                                });
                                break;
                    case "combobox":
                        Dialog comboBoxDialog = new Dialog();
                        comboBoxDialog.setTitle("Avian - Change " + labels.get(i));
                        comboBoxDialog.setHeaderText("Change " + labels.get(i));
                        comboBoxDialog.getDialogPane().setPrefSize(prefWindowWidth,prefWindowHeight);
                        try {
                            comboBoxDialog.getDialogPane().getStylesheets().add(css.toURI().toURL().toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        ButtonType saveButtonCombo = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                        comboBoxDialog.getDialogPane().getButtonTypes().addAll(saveButtonCombo,ButtonType.CANCEL);

                        GridPane gridCombo = new GridPane();
                        gridCombo.setHgap(10);
                        gridCombo.setVgap(10);
                        gridCombo.setPadding(new Insets(20,150,10,10));

                        ObservableList<String> options = //TODO fetch data from JSON
                                FXCollections.observableArrayList(
                                        "Cool",
                                        "Normal",
                                        "Warm",
                                        "Off"
                                );
                        final ComboBox comboBox = new ComboBox(options);
                        comboBox.setValue(statusArray.get(i)); //TODO maybe choose by ID instead of choose from the statusArray

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
                            Optional<String> result = comboBoxDialog.showAndWait();
                            result.ifPresent(status::setText);
                            int[] value = new int[1];
                            switch (result.get()){
                                case "Cool":
                                    value[0] = 1;
                                    sendValue(currentSetting,value);
                                    break;
                                case "Normal":
                                    value[0] = 2;
                                    sendValue(currentSetting,value);
                                    break;
                                case "Warm":
                                    value[0] = 3;
                                    sendValue(currentSetting,value);
                                    break;
                                case "Off":
                                    value[0] = 80;
                                    sendValue(currentSetting,value);
                                    break;
                            }
                    });
                    break;
                default:

            }

            rightBox.getChildren().addAll(status,changeSettings);

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

    private void sendValue(int setting, int[] value) {
        try {
            String[] setKeyList = ctrl.getSET_KEY_LIST();
            String request = setKeyList[setting];
            String[][] responseStatus = ctrl.sendRequest(request,value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

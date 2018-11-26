package View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUIMainScene extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("[Screen Name]");

        ArrayList<HBox> settings = new ArrayList<>();
        String[] settingLabels = {"Power","Volume","Brightness","Direction","Construction Info","Perform Checkup","Network Connection","Consumption"};

        for(int i = 0; i < 8; i++){
            Button button = new Button();
            button.setText("This is button number " + i);
            button.setPrefSize(128,64);

            Label text = new Label(settingLabels[i]);
            text.setFont(new Font("Arial",24));

            HBox hBox = new HBox();
            hBox.setSpacing(16);
            hBox.getChildren().addAll(text,button);
            hBox.setAlignment(Pos.CENTER_RIGHT);


            settings.add(hBox);
        }



        FlowPane flowPane = new FlowPane(Orientation.VERTICAL,64,16);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.getChildren().addAll(settings);

        Label csInfoLabel = new Label("Current Screen");
        Label screenText = new Label("ME46C");
        Label powerInfo = new Label("Current Power status: Screen Turned on for 2h 34m");
        screenText.setFont(new Font("Arial",20));
        screenText.setFont(new Font("Arial",34));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(csInfoLabel,screenText,powerInfo);
        vBox.setPrefSize(300,300);

        VBox bottomBox = new VBox();
        bottomBox.setPrefSize(300,200);

        BorderPane root = new BorderPane();
        root.setTop(vBox);
        root.setCenter(flowPane);
        root.setBottom(bottomBox);

        primaryStage.setScene(new Scene(root, 1980, 1080));
        primaryStage.show();
    }
}

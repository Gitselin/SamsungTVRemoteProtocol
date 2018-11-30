package View;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class ToggleSwitch extends HBox { //Gotten from https://gist.github.com/TheItachiUchiha/12e40a6f3af6e1eb6f75

    private final Button button = new Button();
    private final Button button2 = new Button();

    private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
    public SimpleBooleanProperty switchOnProperty() { return switchedOn; }

    private void init() {

        button2.setStyle("-fx-background-color: none !important;");
        getChildren().addAll(button2, button);
        button.setOnAction((e) -> {
            switchedOn.set(!switchedOn.get());
        });
        button2.setOnMouseClicked((e) -> {
            switchedOn.set(!switchedOn.get());
        });
        setStyle();
        bindProperties();
    }

    private void setStyle() {
        //Default Width
        setWidth(20);
        button2.setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: red; -fx-text-fill:black; -fx-background-radius: 4;");
        setAlignment(Pos.CENTER_LEFT);
    }

    private void bindProperties() {
        button2.prefWidthProperty().bind(widthProperty().divide(2));
        button2.prefHeightProperty().bind(heightProperty());
        button.prefWidthProperty().bind(widthProperty().divide(2));
        button.prefHeightProperty().bind(heightProperty());
    }

    public ToggleSwitch() {
        init();
        switchedOn.addListener((a,b,c) -> {
            if (c) {
                setStyle("-fx-background-color: green;");
                button2.toFront();
            }
            else {
                setStyle("-fx-background-color: red;");
                button.toFront();
            }
        });
    }
}
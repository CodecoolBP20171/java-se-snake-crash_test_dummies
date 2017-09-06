package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PauseMenu {
    public static void display() {
        Stage window = new Stage();
        window.setWidth(300);
        window.setHeight(200);
        window.setTitle("Game paused");
        window.initModality(Modality.APPLICATION_MODAL);
        Label text = new Label("Game paused");

        Button resumeButton = new Button("Close");
        resumeButton.setOnMouseClicked(e -> {
            window.close();
            Globals.paused = false;
            Globals.gameLoop.start();
        });

        VBox layout = new VBox(75);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(text, resumeButton);
        window.setScene(new Scene(layout));
        window.show();
    }
}


package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverScreen{

    public static void display() {

        Stage window = Globals.window;
        VBox layout = new VBox(50);
        layout.getStylesheets().add("css/gameover.css");

        Label p1ScoreLabel = new Label("Player 1 score: " + Globals.scores.get("Player1").toString());
        layout.getChildren().add(p1ScoreLabel);
        if (Globals.scores.get("Player2") != null) {
            Label p2ScoreLabel = new Label("Player 2 score: " + Globals.scores.get("Player2").toString());
            layout.getChildren().add(p2ScoreLabel);
        }

        Button restartButton = new Button("Restart game");
        restartButton.setOnMouseClicked(e -> {
            Globals.paused = false;
            Globals.window.setScene(Globals.splashScene);
        });
        layout.getChildren().add(restartButton);

        Scene gameOverScene = new Scene(layout, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        layout.setAlignment(Pos.BOTTOM_CENTER);
        window.setScene(gameOverScene);
    }


}

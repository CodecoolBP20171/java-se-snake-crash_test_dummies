package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverScreen{

    public static void display() {

        Stage window = Globals.window;

        Label p1ScoreLabel = new Label("Player 1 score: ");
        Label p1ScoreValue = new Label(Globals.scores.get("Player1").toString());

        Button restartButton = new Button("Restart game");
        restartButton.setOnMouseClicked(e -> {
            Globals.paused = false;
            Globals.window.setScene(Globals.splashScene);
        });

        VBox layout = new VBox(25);
        Scene gameOverScene = new Scene(layout, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        layout.setAlignment(Pos.BASELINE_CENTER);
        layout.getChildren().addAll(p1ScoreLabel, p1ScoreValue, restartButton);
        window.setScene(gameOverScene);
    }


}

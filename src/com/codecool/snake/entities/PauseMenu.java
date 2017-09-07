package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.enemies.*;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
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
            Globals.paused = false;
            Globals.gameLoop.start();
            window.close();
        });

        Button restartButton = new Button("Restart game");
        restartButton.setOnMouseClicked(e -> {
            for (GameEntity entity : Globals.gameObjects) {
                Globals.removeGameObject(entity);
            }
            Globals.players.clear();
            Globals.paused = false;
            Globals.clearGameObjects();
            Globals.window.setScene(Globals.splashScene);
            window.close();
        });

        VBox layout = new VBox(25);
        Scene pauseScene = new Scene(layout);

        pauseScene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                window.close();
                Globals.paused = false;
                Globals.gameLoop.start();
            }
        });

        layout.setAlignment(Pos.BASELINE_CENTER);
        layout.getChildren().addAll(text, restartButton, resumeButton);
        window.setScene(pauseScene);
        window.show();
    }
}


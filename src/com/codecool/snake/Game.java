package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    public Game() {
        if(Globals.coop) {
            new SnakeHead(this, "Player1", 700, 500);
            new SnakeHead(this, "Player2", 300, 500);
        } else {
            new SnakeHead(this, "Player1", 500, 500);
        }

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.player1Left  = true; break;
                case RIGHT: Globals.player1Right  = true; break;
                case Q:  Globals.player2Left  = true; break;
                case W: Globals.player2Right  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.player1Left  = false; break;
                case RIGHT: Globals.player1Right  = false; break;
                case Q:  Globals.player2Left  = false; break;
                case W: Globals.player2Right  = false; break;

            }
        });

        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }
}

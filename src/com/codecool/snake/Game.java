package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.stats.DisplayHealth;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Game extends Pane {

    public Game() {
        List<SnakeHead> players = new ArrayList<>(2);
        SnakeHead player1, player2;

        if(Globals.coop) {
            player1 = new SnakeHead(this, "Player1", ((int)Globals.WINDOW_WIDTH/3)*2, (int)Globals.WINDOW_HEIGHT/2);
            player2 = new SnakeHead(this, "Player2", (int)Globals.WINDOW_WIDTH/3, (int)Globals.WINDOW_HEIGHT/2);
            players.add(player2);
            new DisplayHealth(this, player1);
            new DisplayHealth(this, player2);
        } else {
            player1 = new SnakeHead(this, "Player1", (int)Globals.WINDOW_WIDTH/2, (int)Globals.WINDOW_HEIGHT/2);
            new DisplayHealth(this, player1);
        }

        players.add(player1);

        new SimpleEnemy(this, players);
        new SimpleEnemy(this, players);
        new SimpleEnemy(this, players);
        new SimpleEnemy(this, players);

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
                case ESCAPE: Globals.paused = !Globals.paused; break;
            }
        });

        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }
}

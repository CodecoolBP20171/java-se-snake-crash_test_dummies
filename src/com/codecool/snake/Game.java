package com.codecool.snake;

import com.codecool.snake.entities.SpawnController;
import com.codecool.snake.entities.enemies.CirclingEnemy;
import com.codecool.snake.entities.enemies.MobSpawner;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.stats.DisplayHealth;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Game extends Pane {

    public Game() {
        Globals.gameOver = false;
        Globals.players.clear();

        if(Globals.coop) {
            Globals.players.add(new SnakeHead(this, "Player1", ((int)Globals.WINDOW_WIDTH/3)*2, (int)Globals.WINDOW_HEIGHT/2));
            Globals.players.add(new SnakeHead(this, "Player2", (int)Globals.WINDOW_WIDTH/3, (int)Globals.WINDOW_HEIGHT/2));
            new DisplayHealth(this, Globals.players.get(0));
            new DisplayHealth(this, Globals.players.get(1));
        } else {
            Globals.players.add(new SnakeHead(this, "Player1", (int)Globals.WINDOW_WIDTH/2, (int)Globals.WINDOW_HEIGHT/2));
            new DisplayHealth(this, Globals.players.get(0));
        }

        Globals.mobSpawner = new MobSpawner(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        Globals.currentGame = this;
        Globals.spawnController = new SpawnController(this);
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

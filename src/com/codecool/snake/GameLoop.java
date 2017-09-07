package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameOverScreen;
import com.codecool.snake.entities.PauseMenu;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameLoop extends AnimationTimer {

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        int playersLeft = 0;
        if(!Globals.gameOver) {

            if (Globals.paused) {
                resetControlBooleans();
                PauseMenu.display();
                Globals.gameLoop.stop();
            } else {

                for (GameEntity gameObject : Globals.gameObjects) {
                    if (gameObject instanceof Animatable) {
                        Animatable animObject = (Animatable)gameObject;
                        animObject.step();
                    }
                }

                Globals.gameObjects.addAll(Globals.newGameObjects);
                Globals.newGameObjects.clear();

                Globals.gameObjects.removeAll(Globals.oldGameObjects);
                Globals.oldGameObjects.clear();

                for (GameEntity gameObject : Globals.gameObjects) {
                    if (gameObject instanceof SnakeHead) {
                        ((SnakeHead) gameObject).addToScore(1);
                        playersLeft++;
                    }
                }
                if (playersLeft == 0) {
                    Globals.gameOver = true;
                }
            }

        } else {
            System.out.println("scores: "+Globals.scores);
            System.out.println("Game over!");
            resetControlBooleans();
            GameOverScreen.display();
            Globals.gameLoop.stop();
        }

    }

    public void resetControlBooleans() {
        Globals.player2Right = false;
        Globals.player2Left = false;
        Globals.player1Right = false;
        Globals.player1Left = false;
    }
}

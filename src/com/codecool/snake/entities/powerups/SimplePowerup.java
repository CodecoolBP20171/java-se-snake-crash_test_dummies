package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class SimplePowerup extends GameEntity implements Interactable {

    public static int counter = 0;
    public static final int SCORE_AMOUNT = 5000;

    public SimplePowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupBerry);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        counter++;
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(4);
        counter--;
        snakeHead.addToScore(SCORE_AMOUNT);
        destroy();
    }

    @Override
    public String getMessage() {
        return "got simple powerup";
    }
}

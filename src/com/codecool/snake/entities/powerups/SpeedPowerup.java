package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class SpeedPowerup extends GameEntity implements Interactable {

    private static final float SPEED = 2.5f;
    private static final int SPEED_BUFF_TIMER = 180;
    public static final int SCORE_AMOUNT = 2500;

    public SpeedPowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupSpeed);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeSpeed(SPEED);
        snakeHead.setSpeedBuffTimer(SPEED_BUFF_TIMER);
        snakeHead.addToScore(SCORE_AMOUNT);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got speed powerup";
    }
}

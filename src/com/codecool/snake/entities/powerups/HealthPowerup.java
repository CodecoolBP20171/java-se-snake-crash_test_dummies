package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class HealthPowerup extends GameEntity implements Interactable {

    private static final int healAmount = 10;
    public static int counter = 0;

    public HealthPowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupHealth);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        counter++;
    }
    @Override
    public void apply(SnakeHead snakeHead) {
        if (snakeHead.getHealth() <= 90) {
            snakeHead.changeHealth(healAmount);
        }
        counter--;
        destroy();
    }

    @Override
    public String getMessage() {
        return "got health powerup";
    }
}

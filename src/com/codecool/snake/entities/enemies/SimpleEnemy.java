package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Random;

import static com.codecool.snake.Globals.SPAWN_CLEARANCE_MULTIPLIER;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;

    public SimpleEnemy(Pane pane, List<SnakeHead> players) {
        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        int speed = 1;
        Random rnd = new Random();
        boolean locationIsOccupied = true;
        while (locationIsOccupied) {
            double newX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
            double newY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;

            for (SnakeHead player : players) {
                if (
                        newX > player.getX() - (Globals.PLAYER_SPRITE_SIZE * SPAWN_CLEARANCE_MULTIPLIER) &&
                        newX < player.getX() + (Globals.PLAYER_SPRITE_SIZE * SPAWN_CLEARANCE_MULTIPLIER) &&
                        newY > player.getY() - (Globals.PLAYER_SPRITE_SIZE * SPAWN_CLEARANCE_MULTIPLIER) &&
                        newY < player.getY() + (Globals.PLAYER_SPRITE_SIZE * SPAWN_CLEARANCE_MULTIPLIER)
                ) {
                    locationIsOccupied = true;
                    break;
                } else {
                    setX(newX);
                    setY(newY);
                    locationIsOccupied = false;
                }
            }
        }

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        int height = (int) Globals.simpleEnemy.getHeight();
        int width = (int) Globals.simpleEnemy.getWidth();
        if (isOutOfBounds(width, height)) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}

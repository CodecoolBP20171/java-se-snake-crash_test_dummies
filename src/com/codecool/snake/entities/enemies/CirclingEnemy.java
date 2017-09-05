package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class CirclingEnemy extends GameEntity implements Animatable, Interactable {
    private static final int DAMAGE = 10;
    private static final float TURN_RATE = 1.75f;
    private static final double DEFAULT_SPEED = 2.1;
    private static final int SPAWN_CONSTRAINT = 150;

    private Direction turningDirection;
    private Point2D heading;

    public CirclingEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy); // TODO create custom image for circling enemy
        pane.getChildren().add(this);
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        // TODO calculate the enemy circle's origin point and if that point is closer
        // TODO     to the border than the radius of the circle, move it in by that much.
        if (getX() < SPAWN_CONSTRAINT) {
            setX(SPAWN_CONSTRAINT);
        } else if (getX() > Globals.WINDOW_WIDTH - SPAWN_CONSTRAINT) {
            setX(Globals.WINDOW_WIDTH - SPAWN_CONSTRAINT);
        }
        if (getY() < SPAWN_CONSTRAINT) {
            setY(SPAWN_CONSTRAINT);
        } else if (getY() > Globals.WINDOW_HEIGHT - SPAWN_CONSTRAINT) {
            setY(Globals.WINDOW_HEIGHT - SPAWN_CONSTRAINT);
        }

        // Choose turning direction
        if (rnd.nextInt(2) == 0) { turningDirection = Direction.LEFT;
        } else { turningDirection = Direction.RIGHT; }

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, DEFAULT_SPEED);
    }

    @Override
    public void step() {
        double direction = getRotate();
        if (turningDirection == Direction.LEFT) {
            direction -= TURN_RATE;
        } else {
            direction += TURN_RATE;
        }
        setRotate(direction);
        heading = Utils.directionToVector(direction, DEFAULT_SPEED);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-DAMAGE);
        destroy();
    }

    @Override
    public String getMessage() { return String.format("%s damage", DAMAGE); }

    private enum Direction {
        LEFT, RIGHT
    }
}

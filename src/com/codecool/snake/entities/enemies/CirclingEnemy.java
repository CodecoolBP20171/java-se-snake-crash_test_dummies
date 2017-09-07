package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import javafx.scene.layout.Pane;

import java.util.Random;

public class CirclingEnemy extends AbstractEnemy implements Animatable {
    private static final float TURN_RATE = 1.75f;
    private static final double SPEED = 2.1;

    private Direction turnDirection;

    public CirclingEnemy(Pane pane) {
        super(pane, Globals.simpleEnemy);   // TODO create custom image for circling enemy
        this.heading = Utils.directionToVector(getRotate(), SPEED);

        Random rnd = new Random();
        if (rnd.nextInt(2) == 0) { turnDirection = Direction.LEFT;
        } else { turnDirection = Direction.RIGHT; }
    }

    private void changeDirection() {
        if (turnDirection == Direction.LEFT) {
            turnDirection = Direction.RIGHT;
        } else {
            turnDirection = Direction.LEFT;
        }
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
            return;
        }

        Random rnd = new Random();
        if (rnd.nextInt(421) == 0) {   // 0,0023% chance of turning
            changeDirection();
        }

        double direction = getRotate();
        if (this.turnDirection == Direction.LEFT) {
            direction -= TURN_RATE;
        } else {
            direction += TURN_RATE;
        }
        setRotate(direction);
        heading = Utils.directionToVector(direction, SPEED);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    private enum Direction {
        LEFT, RIGHT
    }
}

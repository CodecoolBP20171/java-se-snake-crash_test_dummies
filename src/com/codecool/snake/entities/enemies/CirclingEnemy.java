package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


public class CirclingEnemy extends AbstractEnemy implements Animatable {
    private static final float TURN_RATE = 2;
    private static final double SPEED = 3.75;
    private Direction turnDirection;

    public CirclingEnemy(Pane pane) {
        super(pane, Globals.circlingEnemy);
        this.heading = Utils.directionToVector(getRotate(), SPEED);

        if (Globals.RND.nextInt(2) == 0) { turnDirection = Direction.LEFT;
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
        if (isOutOfBounds((int)Globals.simpleEnemy.getWidth(), (int)Globals.simpleEnemy.getHeight())) {
            destroy();
            Globals.mobSpawner.decrementCirclingEnemyCounter();
            return;
        }

        if (Globals.RND.nextInt(421) == 0) {   // 0,0023% chance of turning
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

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-DAMAGE);
        Globals.mobSpawner.decrementCirclingEnemyCounter();
        destroy();
    }

    private enum Direction {
        LEFT, RIGHT
    }
}

package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SimpleEnemy extends AbstractEnemy implements Animatable {

    private static final int SPEED = 2;

    public SimpleEnemy(Pane pane) { //  Globals.simpleEnemy
        super(pane, Globals.simpleEnemy);
        this.heading = Utils.directionToVector(getRotate(), SPEED);
    }

    @Override
    public void step() {
        // TODO reverse the direction of the enemy when bouncing form a wall
        if (getX() > Globals.WINDOW_WIDTH || getX() < 0) {
            heading = new Point2D(- heading.getX(), heading.getY());
        } else if (getY() > Globals.WINDOW_HEIGHT || getY() < 0) {
            heading = new Point2D(heading.getX(), - heading.getY());
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-DAMAGE);
        Globals.mobSpawner.decrementSimpleEnemyCounter();
        destroy();
    }
}

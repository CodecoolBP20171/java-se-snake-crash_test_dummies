package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class FollowingEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    private static final double DEFAULT_SPEED = 1.5;
    private static final float TURN_RATE = 10;

    public FollowingEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy); // TODO add custom texture
        pane.getChildren().add(this);
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextDouble() * 360;
        setRotate(0);
        heading = Utils.directionToVector(direction, DEFAULT_SPEED);
    }

    @Override
    public void step() {
        Vec2d origin = new Vec2d(getX(), getY());
        Vec2d goal = new Vec2d(Globals.WINDOW_WIDTH / 2, Globals.WINDOW_HEIGHT / 2);
        Vec2d destination = new Vec2d(getX() + heading.getX(), getY() + heading.getY());
        double distanceFromGoal = Integer.MAX_VALUE;
        double angle;

        for (SnakeHead player : SnakeHead.getPlayers()) {  // TODO use agreed upon implementation of player listing
            double distanceFromPlayer = heading.distance(player.getX(), player.getY());
            if (distanceFromPlayer < distanceFromGoal) {
                distanceFromGoal = distanceFromPlayer;
                goal.set(player.getX(), player.getY());
            }
        }


        double a = origin.distance(destination);    // origin to destination
        double b = origin.distance(goal);           // origin to goal
        double c = destination.distance(goal);      // destination to goal
        angle = Math.toDegrees(Math.acos(
                (c*c - a*a - b*b) /
                (-2 * a * b)
        ));

        if (angle > 15) {
            setRotate(getRotate() + TURN_RATE);
            heading = Utils.directionToVector(getRotate(), DEFAULT_SPEED);
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
        return String.format("%s damage", damage);
    }
}

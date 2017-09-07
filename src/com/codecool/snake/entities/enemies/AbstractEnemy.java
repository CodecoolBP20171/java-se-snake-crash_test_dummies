package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

public abstract class AbstractEnemy  extends GameEntity implements Interactable {

    protected static final int DAMAGE = 10;
    private static final int SPAWN_CONSTRAINT = 70;

    protected Point2D heading;

    public AbstractEnemy(Pane pane, Image image) {
        super(pane);
        setImage(image);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setSpawnpoint(rnd);
        setRotate(rnd.nextDouble() * 360);
    }

    private void setSpawnpoint(Random randomGenerator) {
        setX(SPAWN_CONSTRAINT + randomGenerator.nextDouble() * (Globals.WINDOW_WIDTH - SPAWN_CONSTRAINT * 2));
        setY(SPAWN_CONSTRAINT + randomGenerator.nextDouble() * (Globals.WINDOW_HEIGHT - SPAWN_CONSTRAINT * 2));
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-DAMAGE);
        destroy();
    }

    @Override
    public String getMessage() {
        return String.format("%s damage", DAMAGE);
    }
}

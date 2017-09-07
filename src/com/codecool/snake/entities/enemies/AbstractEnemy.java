package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public abstract class AbstractEnemy  extends GameEntity implements Interactable {

    protected static final int DAMAGE = 10;
    private static final int SPAWN_CONSTRAINT = 70;
    private static final int SPAWN_CLEARANCE_MULTIPLIER = 5;

    protected Point2D heading;

    public AbstractEnemy(Pane pane, Image image) {
        super(pane);
        setImage(image);
        pane.getChildren().add(this);

        setSpawnpoint();
        setRotate(Globals.RND.nextDouble() * 360);
    }

    private void setSpawnpoint() {
        boolean locationIsOccupied = true;
        while (locationIsOccupied) {
            double newX = SPAWN_CONSTRAINT + Globals.RND.nextDouble() * (Globals.WINDOW_WIDTH - SPAWN_CONSTRAINT * 2);
            double newY = SPAWN_CONSTRAINT + Globals.RND.nextDouble() * (Globals.WINDOW_HEIGHT - SPAWN_CONSTRAINT * 2);

            for (SnakeHead player : Globals.players) {
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

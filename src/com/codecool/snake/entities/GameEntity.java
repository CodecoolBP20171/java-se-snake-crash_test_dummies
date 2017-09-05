package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected Pane pane;

    protected GameEntity(Pane pane) {
        this.pane = pane;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    public void destroy() {
        if (getParent() != null) {
            pane.getChildren().remove(this);
        }
        Globals.removeGameObject(this);
    }

    protected boolean isOutOfBounds(int width, int height) {
        if (getX() > Globals.WINDOW_WIDTH || getX() < -1 * width ||
            getY() > Globals.WINDOW_HEIGHT || getY() < -1 * height) {
            return true;
        }
        return false;
    }
}

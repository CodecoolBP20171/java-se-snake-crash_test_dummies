package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private String playerName;
    private int imageWidth;
    private int imageHeight;

    public SnakeHead(Pane pane, String playerName, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        this.playerName = playerName;
        if (this.playerName.equals("Player1")) {
            setImage(Globals.p1snakeHead);
            this.imageHeight = (int) Globals.p1snakeHead.getHeight();
            this.imageWidth = (int) Globals.p1snakeHead.getWidth();
        } else {
            setImage(Globals.p2snakeHead);
            this.imageHeight = (int) Globals.p2snakeHead.getHeight();
            this.imageWidth = (int) Globals.p2snakeHead.getWidth();

        }
        pane.getChildren().add(this);

        addPart(4);
    }

    public void step() {
        double dir = getRotate();

        if (this.playerName.equals("Player1")) {

            if (Globals.player1Left) {
                dir = dir - turnRate;
            }
            if (Globals.player1Right) {
                dir = dir + turnRate;
            }

        } else if (this.playerName.equals("Player2")) {

            if (Globals.player2Left) {
                dir = dir - turnRate;
            }
            if (Globals.player2Right) {
                dir = dir + turnRate;
            }
        }

        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        if (isOutOfBounds(imageWidth, imageHeight)) {
            System.out.println("out of bounds");
            if(getX() >= Globals.WINDOW_WIDTH) {
                setX(-1 * imageWidth + heading.getX());
            } else if (getX() <= -1 * imageWidth) {
                setX(Globals.WINDOW_WIDTH + heading.getX());
            } else if (getY() >= Globals.WINDOW_HEIGHT) {
                setY(-1 * imageHeight + heading.getY());
            } else if (getY() <= -1 * imageHeight) {
                setY(Globals.WINDOW_HEIGHT + heading.getY());
            }
        }

        // check for game over condition
        if (health <= 0) {
            System.out.println("Game Over");
            Globals.gameLoop.stop();
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail, playerName);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }
}

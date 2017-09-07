package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float DEFAULT_SPEED = 2;
    private float speed = DEFAULT_SPEED;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private int speedBuffTimer = 0;
    private static final float turnRate = 2;
    private String playerName;
    private int imageWidth;
    private int imageHeight;
    private int score;


    public SnakeHead(Pane pane, String playerName, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        score = 0;
        this.health = 100;

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

        if(speedBuffTimer > 0) {
            --speedBuffTimer;
        } else {
            this.speed = DEFAULT_SPEED;
        }

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

                    // if SnakeHead interacts with SnakeBody, then check if the two are from different players, otherwise, getMessage() spams.
                    if(!interactable.getClass().getSimpleName().equals("SnakeBody")){
                        System.out.println(interactable.getMessage());
                    } else {
                        if (!((SnakeBody) interactable).getPlayerName().equals(this.getPlayerName())) {
                            System.out.println(interactable.getMessage());
                        }
                    }
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
            updateScores();
            removeTail();
            destroy();


        }
    }

    public void removeTail(){
        for (GameEntity entity: Globals.gameObjects) {
            if (entity instanceof SnakeBody) {
                SnakeBody snakeBody = (SnakeBody) entity;
                if (snakeBody.getPlayerName().equals(getPlayerName())) {
                    snakeBody.destroy();
                }
            }
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public void addToScore(int addition){
        this.score += addition;
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail, playerName);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        this.health += diff;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    public void updateScores() {
        Globals.scores.put(this.playerName, this.score);
    }

    public int getHealth() {
        return health;
    }

    public void changeSpeed(float speed) {
        this.speed = speed;
    }
    public void setSpeedBuffTimer(int speedBuffTimer) {
        this.speedBuffTimer = speedBuffTimer;
    }
}

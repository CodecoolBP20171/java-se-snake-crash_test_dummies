package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javafx.stage.Screen;
import javafx.stage.Stage;


// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    public static final double WINDOW_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
    public static Stage window;
    public static Scene splashScene;

    public static final short PLAYER_SPRITE_SIZE = 30;
    public static final short SPAWN_CLEARANCE_MULTIPLIER = 6;

    public static Image p1snakeHead = new Image("p1_snake_head.png");
    public static Image p1snakeBody = new Image("p1_snake_body.png");
    public static Image p2snakeHead = new Image("p2_snake_head.png");
    public static Image p2snakeBody = new Image("p2_snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image powerupHealth = new Image("powerup_health.png");
    public static Image powerupSpeed = new Image("powerup_speed.png");
    //.. put here the other images you want to use

    public static boolean coop = false;
    public static boolean paused = false;
    public static boolean player1Left;
    public static boolean player1Right;
    public static boolean player2Left;
    public static boolean player2Right;
    public static List<SnakeHead> players = new ArrayList<>(2);

    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}

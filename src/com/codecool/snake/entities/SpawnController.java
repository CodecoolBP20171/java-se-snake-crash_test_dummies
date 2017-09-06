package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import javafx.scene.layout.Pane;

public class SpawnController extends GameEntity implements Animatable {

    public static final int MAX_CONCURRENT_ENEMY_NR = 10;
    public static final int ENEMY_SPAWN_INTERVAL = 100;
    public static int enemy_timer;
    public Pane pane;

    public SpawnController(Pane pane) {
        super(pane);
    }

    @Override
    public void step() {
        if (enemy_timer == 0) {
            new SimpleEnemy(Globals.currentGame, Globals.players);
            enemy_timer = ENEMY_SPAWN_INTERVAL;
        } else {
            --enemy_timer;
        }

    }
}

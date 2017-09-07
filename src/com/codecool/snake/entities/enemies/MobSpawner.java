package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import javafx.scene.layout.Pane;

public class MobSpawner extends GameEntity implements Animatable{
    private static final int MIN_TIMEOUT = 90;  // 1.5 seconds
    private static final int MAX_TIMEOUT = 420; // 7 seconds
    private static final int DIFFICULTY_TIMEOUT = 420;  // 7 seconds

    private static int CIRCLING_STARTING_COUNT;
    private static int SIMPLE_STARTING_COUNT;
    private static int FOLLOWING_STARTING_COUNT;

    private static int CIRCLING_CURRENT_COUNT;
    private static int FOLLOWING_CURRENT_COUNT;
    private static int SIMPLE_CURRENT_COUNT;

    private static int CIRCLING_MAX_COUNT;
    private static int FOLLOWING_MAX_COUNT;
    private static int SIMPLE_MAX_COUNT;

    private int currentTimer;
    private int difficulty;
    private int difficultyTimer;
    private Pane pane;
    private int timeout;

    static {
        CIRCLING_CURRENT_COUNT = 0;
        FOLLOWING_CURRENT_COUNT = 0;
        SIMPLE_CURRENT_COUNT = 0;

        CIRCLING_STARTING_COUNT = 3;
        FOLLOWING_STARTING_COUNT = 1;
        SIMPLE_STARTING_COUNT = 3;

        CIRCLING_MAX_COUNT = 7;
        FOLLOWING_MAX_COUNT = 3;
        SIMPLE_MAX_COUNT = 7;
    }

    public MobSpawner(Pane pane) {
        super(pane);

        this.currentTimer = 0;
        this.difficulty = 0;
        this.difficultyTimer = 0;
        this.pane = pane;
        setTimeout();

        for (int i = 0; i < CIRCLING_STARTING_COUNT; i++) {
            spawnCirclingEnemy();
        }
        for (int i = 0; i < FOLLOWING_STARTING_COUNT; i++) {
            spawnFollowingEnemy();
        }
        for (int i = 0; i < SIMPLE_STARTING_COUNT; i++) {
            spawnSimpleEnemy();
        }
    }

    private void setTimeout() {
        timeout = MIN_TIMEOUT + Globals.RND.nextInt(MAX_TIMEOUT - MIN_TIMEOUT);
    }

    private void spawnCirclingEnemy() {
        if (CIRCLING_CURRENT_COUNT < CIRCLING_MAX_COUNT) {
            new CirclingEnemy(pane);
            CIRCLING_CURRENT_COUNT++;
        }
    }

    private void spawnFollowingEnemy() {
        if (FOLLOWING_CURRENT_COUNT < FOLLOWING_MAX_COUNT) {
            new FollowingEnemy(pane);
            FOLLOWING_CURRENT_COUNT++;
        }
    }

    private void spawnSimpleEnemy() {
        if (SIMPLE_CURRENT_COUNT < SIMPLE_MAX_COUNT) {
            new SimpleEnemy(pane);
            SIMPLE_CURRENT_COUNT++;
        }
    }

    public void decrementCirclingEnemyCounter() {
        CIRCLING_CURRENT_COUNT--;
    }

    public void decrementFollowingEnemyCounter() {
        FOLLOWING_CURRENT_COUNT--;
    }

    public void decrementSimpleEnemyCounter() {
        SIMPLE_CURRENT_COUNT--;
    }

    @Override
    public void step() {
        currentTimer++;
        difficultyTimer++;

        if (difficultyTimer == DIFFICULTY_TIMEOUT) {
            difficulty++;
            SIMPLE_MAX_COUNT++;
            CIRCLING_MAX_COUNT++;
            if (difficulty % 3 == 0) {
                FOLLOWING_MAX_COUNT++;
            }
            difficultyTimer = 0;
        }

        if (currentTimer == timeout) {
            // 50% to spawn CirclingEnemy
            // 40% to spawn SimpleEnemy
            // 10% to spawn FollowingEnemy
            if (Globals.RND.nextInt(2) == 0) {
                spawnCirclingEnemy();
            } else if (Globals.RND.nextInt(11) < 5) {
                spawnSimpleEnemy();
            } else {
                spawnFollowingEnemy();
            }

            currentTimer = 0;
            setTimeout();
        }
    }
}

package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import javafx.scene.layout.Pane;

public class MobSpawner extends GameEntity implements Animatable{
    private static final int MIN_TIMEOUT = 90;  // 1.5 seconds
    private static final int MAX_TIMEOUT = 420; // 7 seconds
    private static final int DIFFICULTY_TIMEOUT = 420;  // 7 seconds

    private static int circlingStartingCount;
    private static int followingStartingCount;
    private static int simpleStartingCount;

    private static int circlingCurrentCount;
    private static int followingCurrentCount;
    private static int simpleCurrentCount;

    private static int circlingMaxCount;
    private static int followingMaxCount;
    private static int simpleMaxCount;

    private int currentTimer;
    private int difficulty;
    private int difficultyTimer;
    private Pane pane;
    private int timeout;

    static {
        circlingCurrentCount = 0;
        followingCurrentCount = 0;
        simpleCurrentCount = 0;

        circlingStartingCount = 3;
        followingStartingCount = 1;
        simpleStartingCount = 3;

        circlingMaxCount = 7;
        followingMaxCount = 3;
        simpleMaxCount = 7;
    }

    public MobSpawner(Pane pane) {
        super(pane);

        this.currentTimer = 0;
        this.difficulty = 0;
        this.difficultyTimer = 0;
        this.pane = pane;
        setTimeout();

        for (int i = 0; i < circlingStartingCount; i++) {
            spawnCirclingEnemy();
        }
        for (int i = 0; i < followingStartingCount; i++) {
            spawnFollowingEnemy();
        }
        for (int i = 0; i < simpleStartingCount; i++) {
            spawnSimpleEnemy();
        }
    }

    private void setTimeout() {
        timeout = MIN_TIMEOUT + Globals.RND.nextInt(MAX_TIMEOUT - MIN_TIMEOUT);
    }

    private void spawnCirclingEnemy() {
        if (circlingCurrentCount < circlingMaxCount) {
            new CirclingEnemy(pane);
            circlingCurrentCount++;
        }
    }

    private void spawnFollowingEnemy() {
        if (followingCurrentCount < followingMaxCount) {
            new FollowingEnemy(pane);
            followingCurrentCount++;
        }
    }

    private void spawnSimpleEnemy() {
        if (simpleCurrentCount < simpleMaxCount) {
            new SimpleEnemy(pane);
            simpleCurrentCount++;
        }
    }

    public void decrementCirclingEnemyCounter() {
        circlingCurrentCount--;
    }

    public void decrementFollowingEnemyCounter() {
        followingCurrentCount--;
    }

    public void decrementSimpleEnemyCounter() {
        simpleCurrentCount--;
    }

    @Override
    public void step() {
        currentTimer++;
        difficultyTimer++;

        if (difficultyTimer == DIFFICULTY_TIMEOUT) {
            difficulty++;
            simpleMaxCount++;
            circlingMaxCount++;
            if (difficulty % 3 == 0) {
                followingMaxCount++;
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

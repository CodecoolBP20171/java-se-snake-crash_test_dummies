package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthPowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.SpeedPowerup;
import javafx.scene.layout.Pane;

import java.util.Random;

public class SpawnController extends GameEntity implements Animatable {

    public static final int MAX_CONCURRENT_ENEMY_NR = 10;
    public static final int ENEMY_SPAWN_INTERVAL = 100;
    public static int enemyTimer = 0;
    
    public static final int HEALTH_POWERUP_CHANCE = 50;
    public static final int SPEED_POWERUP_CHANCE = 30;
    public static final int LENGTH_POWERUP_CHANCE = 20;

    public static final int POWERUP_TIMER_MIN = 120;
    public static final int POWERUP_TIMER_MAX = 300;
    public static int powerupTimer = 0;

    public SpawnController(Pane pane) {
        super(pane);
    }

    @Override
    public void step() {
        Random randomizer = new Random();
        if (powerupTimer == 0) {

            int buffChoicePercent = randomizer.nextInt(100);
            if (buffChoicePercent <= HEALTH_POWERUP_CHANCE){
                new HealthPowerup(Globals.currentGame);
            }

            if (buffChoicePercent <= SPEED_POWERUP_CHANCE) {
                new SpeedPowerup(Globals.currentGame);
            }

            if (buffChoicePercent <= LENGTH_POWERUP_CHANCE) {
                new SimplePowerup(Globals.currentGame);
            }

            powerupTimer = randomizer.nextInt(POWERUP_TIMER_MAX-POWERUP_TIMER_MIN) + POWERUP_TIMER_MIN;
        } else {
            --powerupTimer;
        }

        if (enemyTimer == 0) {
            new SimpleEnemy(Globals.currentGame, Globals.players);
            enemyTimer = ENEMY_SPAWN_INTERVAL;
        } else {
            --enemyTimer;
        }

    }
}

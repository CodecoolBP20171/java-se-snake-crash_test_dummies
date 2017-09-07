package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthPowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.SpeedPowerup;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnController extends GameEntity implements Animatable {
    
    public static final int HEALTH_POWERUP_CHANCE = 50;
    public static final int SPEED_POWERUP_CHANCE = 30;
    public static final int LENGTH_POWERUP_CHANCE = 20;

    public static final int POWERUP_TIMER_MIN = 120;
    public static final int POWERUP_TIMER_MAX = 300;
    public static int powerupTimer = 0;
    public static final int POWERUP_CAP = 15;
    public static List<String> roll = new ArrayList<>();

    static {
        for (int i = 0; i < HEALTH_POWERUP_CHANCE; i++) {
            roll.add("health");
        }
        for (int i = 0; i < SPEED_POWERUP_CHANCE; i++) {
            roll.add("speed");
        }
        for (int i = 0; i < LENGTH_POWERUP_CHANCE ; i++) {
            roll.add("length");
        }
    }

    public SpawnController(Pane pane) {
        super(pane);
    }

    @Override
    public void step() {
        Random randomizer = new Random();
        if (powerupTimer == 0 && isUnderPowerupCap()) {
            String choice = roll.get(randomizer.nextInt(100));

            if (choice.equals("health") && isUnderPowerupCap()) {
                new HealthPowerup(Globals.currentGame);
            } else if (choice.equals("speed") && isUnderPowerupCap()) {
                new SpeedPowerup(Globals.currentGame);
            } else if (choice.equals("length") && isUnderPowerupCap()) {
                new SimplePowerup(Globals.currentGame);
            }
            powerupTimer = randomizer.nextInt(POWERUP_TIMER_MAX-POWERUP_TIMER_MIN) + POWERUP_TIMER_MIN;
        } else if (powerupTimer == 0) {
            powerupTimer = randomizer.nextInt(POWERUP_TIMER_MAX-POWERUP_TIMER_MIN) + POWERUP_TIMER_MIN;
        } else {
            powerupTimer--;
        }
    }
    public boolean isUnderPowerupCap() {
        int powerupSum = HealthPowerup.counter + SpeedPowerup.counter + SimplePowerup.counter;
        if (powerupSum >= POWERUP_CAP) {
            return false;
        } else return true;
    }
}

package com.codecool.snake.entities.stats;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.control.Label;
import com.codecool.snake.entities.GameEntity;
import javafx.scene.layout.Pane;

public class DisplayScores extends GameEntity implements Animatable {

    public Label displayScores;
    private SnakeHead player;

    public DisplayScores(Pane pane, SnakeHead player) {
        super(pane);

        this.displayScores = new Label();
        this.player = player;

        if (player.getPlayerName() == "Player2") {
            this.displayScores.setLayoutX(Globals.WINDOW_WIDTH-230);
            this.displayScores.setLayoutY(75);
            this.displayScores.setStyle("-fx-background-color: chocolate; -fx-padding: 10px;");
            this.displayScores.setPrefWidth(200);
        } else {
            this.displayScores.setLayoutX(30);
            this.displayScores.setLayoutY(75);
            this.displayScores.setStyle("-fx-background-color: chocolate; -fx-padding: 10px;");
            this.displayScores.setPrefWidth(200);
        }

        pane.getChildren().add(displayScores);
    }


    @Override
    public void step() {
        displayScores.setText(player.getPlayerName() + " score: " + String.valueOf(player.getScore()));
    }
}

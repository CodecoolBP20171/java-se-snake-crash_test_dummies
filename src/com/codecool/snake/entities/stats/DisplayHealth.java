package com.codecool.snake.entities.stats;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.control.Label;
import com.codecool.snake.entities.GameEntity;
import javafx.scene.layout.Pane;


public class DisplayHealth extends GameEntity implements Animatable{

    public Label displayHealth;
    private SnakeHead player;

    public DisplayHealth(Pane pane, SnakeHead player) {
        super(pane);

        this.displayHealth = new Label();
        this.player = player;
        this.displayHealth.setText(String.valueOf(player.getHealth()));

        if (player.getPlayerName() == "Player2") {
            this.displayHealth.setLayoutX(Globals.WINDOW_WIDTH-230);
            this.displayHealth.setLayoutY(30);
            this.displayHealth.setStyle("-fx-background-color: red; -fx-padding: 10px;");
            this.displayHealth.setPrefWidth(player.getHealth()*2);
        } else {
            this.displayHealth.setLayoutX(30);
            this.displayHealth.setLayoutY(30);
            this.displayHealth.setStyle("-fx-background-color: green; -fx-padding: 10px;");
            this.displayHealth.setPrefWidth(player.getHealth()*2);
        }

        //displayHealth.setFitWidth(200);
        //this.setStyle("-fx-background-color: blue;");
        //this.setStyle("-fx-background-color: blue;");




        pane.getChildren().add(displayHealth);
    }

    @Override
    public void step() {
        displayHealth.setText(String.valueOf(player.getHealth()));
        displayHealth.setPrefWidth(player.getHealth()*2);
    }
}

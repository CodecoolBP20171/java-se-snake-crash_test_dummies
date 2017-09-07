package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Snake Game by Crash Test Dummies");

        Game game = new Game();
        Scene gameScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        gameScene.getStylesheets().add("css/game.css");

        Pane gridSplash = new Pane();

        Button buttonOnePlayer = new Button("Start one Player mode");
        buttonOnePlayer.setLayoutX(Globals.WINDOW_WIDTH*0.03);
        buttonOnePlayer.setLayoutY(Globals.WINDOW_HEIGHT*0.15);

        buttonOnePlayer.setOnAction(e -> {
                    Globals.coop = false;
                    primaryStage.setScene(gameScene);
                    game.start();
                }
        );

        Button buttonTwoPlayer = new Button("Start two Player mode");
        buttonTwoPlayer.setLayoutX(Globals.WINDOW_WIDTH*0.03);
        buttonTwoPlayer.setLayoutY(Globals.WINDOW_HEIGHT*0.15+100);

        buttonTwoPlayer.setOnAction(e -> {
                    Globals.coop = true;
                    primaryStage.setScene(gameScene);
                    game.start();
                }
        );

        ImageView logo = new ImageView(new Image("logo.png"));
        logo.setFitWidth(logo.getImage().getWidth()/5);
        logo.setFitHeight(logo.getImage().getHeight()/5);
        logo.setX(Globals.WINDOW_WIDTH*0.03+120);
        logo.setY(Globals.WINDOW_HEIGHT-130);

        Label developedBy = new Label("Developed by ");
        developedBy.setId("devel");
      
        developedBy.setLayoutX(Globals.WINDOW_WIDTH*0.03);
        developedBy.setLayoutY(Globals.WINDOW_HEIGHT-80);

        gridSplash.getChildren().addAll(buttonOnePlayer, buttonTwoPlayer, logo, developedBy);
        gridSplash.getStylesheets().add("css/splash.css");

        Scene splashScene = new Scene(gridSplash, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

        primaryStage.setScene(splashScene);
        primaryStage.show();
    }
}

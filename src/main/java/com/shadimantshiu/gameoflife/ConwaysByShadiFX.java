package com.shadimantshiu.gameoflife;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;

public class ConwaysByShadiFX extends Application {


    private static final int CELL_SIZE = 8;  // Smaller cells for more cells on screen
    private static final int GRID_WIDTH = 100;  // Larger grid width
    private static final int GRID_HEIGHT = 80;  // Larger grid height
    private ConwaysByShadi game;
    private Canvas canvas;
    private AnimationTimer timer;
    private boolean isRunning = true;  // To pause/resume simulation

    @Override
    public void start(Stage primaryStage) {
        game = new ConwaysByShadi(GRID_WIDTH, GRID_HEIGHT);
        canvas = new Canvas(GRID_WIDTH * CELL_SIZE, GRID_HEIGHT * CELL_SIZE);

        // Random initial state
        initializeRandomBoard();

        Pane root = new Pane();
        root.getChildren().add(canvas);

        Button pauseButton = new Button("Pause");
        pauseButton.setLayoutX(GRID_WIDTH * CELL_SIZE + 10);
        pauseButton.setLayoutY(10);
        pauseButton.setOnAction(e -> togglePause(pauseButton));

        // Button to reset and randomize the game
        Button resetButton = new Button("Reset");
        resetButton.setLayoutX(GRID_WIDTH * CELL_SIZE + 10);
        resetButton.setLayoutY(50);
        resetButton.setOnAction(e -> initializeRandomBoard());

        root.getChildren().addAll(pauseButton, resetButton);

        Scene scene = new Scene(root, GRID_WIDTH * CELL_SIZE + 100, GRID_HEIGHT * CELL_SIZE);


        // Add mouse click event to let the user toggle cells
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            int x = (int) (e.getX() / CELL_SIZE);
            int y = (int) (e.getY() / CELL_SIZE);
            game.toggleCellState(x, y);
            draw();
        });

        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Animation Timer
        timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 200_000_000) { // 200ms per frame
                    if (isRunning) {
                        game.nextGeneration();
                        draw();
                    }
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    private void initializeRandomBoard() {
        Random random = new Random();
        boolean[][] initialState = new boolean[GRID_WIDTH][GRID_HEIGHT];

        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                initialState[i][j] = random.nextBoolean();  // Randomly set each cell to alive or dead
            }
        }
        game.setInitialState(initialState);
        draw();
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        boolean[][] board = game.getBoard();
        gc.setFill(Color.BLACK);

        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                if (board[i][j]) {
                    gc.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    private void togglePause(Button pauseButton) {
        isRunning = !isRunning;
        pauseButton.setText(isRunning ? "Pause" : "Resume");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
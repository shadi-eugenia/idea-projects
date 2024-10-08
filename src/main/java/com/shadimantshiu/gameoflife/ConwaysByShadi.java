package com.shadimantshiu.gameoflife;

import com.shadimantshiu.gameoflife.interfaces.ConwaysGameOfLife;

import java.awt.*;

public class ConwaysByShadi implements ConwaysGameOfLife {
    private int rows;
    private int cols;
    private boolean[][] board;

    public ConwaysByShadi(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new boolean[rows][cols];
    }

    public void setInitialState(boolean[][] initialState) {
        this.board = initialState;
    }

    public void toggleCellState(int x, int y) {
        if (x >= 0 && x < rows && y >= 0 && y < cols) {
            board[x][y] = !board[x][y];  // Toggle the cell state
        }
    }

    // Get number of live neighbours for a given point
    private int countLiveNeighbours(Point point) {
        int liveNeighbours = 0;
        int[][] directions = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};

        for (int[] dir : directions) {
            int newRow = point.x + dir[0];
            int newCol = point.y + dir[1];

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && board[newRow][newCol]) {
                liveNeighbours++;
            }
        }
        return liveNeighbours;
    }

    @Override
    public boolean liveCellWithFewerThanTwoLiveNeighboursDies(Point point) {
        int liveNeighbours = countLiveNeighbours(point);
        return liveNeighbours < 2;
    }

    @Override
    public boolean liveCellWithTwoOrThreeLiveNeighboursLives(Point point) {
        int liveNeighbours = countLiveNeighbours(point);
        return liveNeighbours == 2 || liveNeighbours == 3;
    }

    @Override
    public boolean liveCellWithMoreThanThreeLiveNeighboursDies(Point point) {
        int liveNeighbours = countLiveNeighbours(point);
        return liveNeighbours > 3;
    }

    @Override
    public boolean deadCellWithExactlyThreeLiveNeighboursBecomesAlive(Point point) {
        int liveNeighbours = countLiveNeighbours(point);
        return liveNeighbours == 3;
    }

    // Advance to the next state of the game
    public void nextGeneration() {
        boolean[][] newBoard = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Point point = new Point(i, j);
                if (board[i][j]) {
                    // Cell is alive
                    if (liveCellWithFewerThanTwoLiveNeighboursDies(point) ||
                            liveCellWithMoreThanThreeLiveNeighboursDies(point)) {
                        newBoard[i][j] = false; // Cell dies
                    } else {
                        newBoard[i][j] = true;  // Cell lives
                    }
                } else {
                    // Cell is dead
                    if (deadCellWithExactlyThreeLiveNeighboursBecomesAlive(point)) {
                        newBoard[i][j] = true; // Cell becomes alive
                    }
                }
            }
        }
        this.board = newBoard;
    }

    public boolean[][] getBoard() {
        return this.board;
    }
}

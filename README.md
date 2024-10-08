# Conway's Game of Life in Java & JavaFX

This is an implementation of **Conway's Game of Life** using Java and JavaFX for visualization. The project includes both the core logic of the game and a graphical user interface (GUI) that lets you observe the evolution of the cells over time. You can interact with the grid by toggling cell states with mouse clicks, pausing/resuming the simulation, or resetting the grid to a random state.

## Table of Contents

- [Overview](#overview)
- [Rules](#rules)
- [Features](#features)
- [Getting Started](#getting-started)
- [How to Play](#how-to-play)
- [Requirements](#requirements)

## Overview

**Conway's Game of Life** is a cellular automaton devised by the mathematician John Conway. It's a zero-player game, meaning its evolution is determined by its initial state, requiring no further input. The cells on the grid evolve based on a few simple rules.

This project includes:
- A Java implementation of the game logic based on an interface.
- JavaFX-based visualization of the game grid.
- User interaction capabilities, including toggling cells, pausing the simulation, and randomizing the grid.

## Rules

The grid consists of cells, each of which can be **alive** or **dead**. Cells evolve based on the number of live neighbors they have:

1. A live cell with **fewer than two live neighbors** dies (underpopulation).
2. A live cell with **two or three live neighbors** lives on to the next generation.
3. A live cell with **more than three live neighbors** dies (overpopulation).
4. A dead cell with **exactly three live neighbors** becomes alive (reproduction).

## Features

- **Visualization**: The game grid is drawn using JavaFX's `Canvas`. Alive cells are displayed in black, while dead cells are white.
- **Interaction**: You can toggle cells between alive and dead by clicking on the grid.
- **Pause/Resume**: Use a button to pause and resume the simulation.
- **Reset**: Randomize the grid with a button click to generate new initial states.
- **Smooth Animation**: The simulation evolves every 200ms, with adjustable speed by modifying the frame rate.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/conways-game-of-life.git
cd conways-game-of-life

# To compile
javac -cp /path/to/javafx-sdk/lib/* GameOfLifeFX.java

# To run
java -cp /path/to/javafx-sdk/lib/*:. GameOfLifeFX

```

3. Set Up JavaFX in Your IDE
If you are using an IDE like IntelliJ or Eclipse:

Download and include the JavaFX SDK in your project.
Set up the JavaFX libraries in your IDE's project structure.

### How to Play
Start the Game: Upon launching, the grid will start with a random configuration of alive and dead cells.
Pause/Resume: Use the "Pause" button to stop or resume the evolution.
Toggle Cells: Click on any cell in the grid to change its state (alive or dead).
Reset: Click the "Reset" button to randomize the grid and start a new simulation.
### Controls:
Mouse Click: Toggle the state of a cell between alive and dead.
Pause/Resume Button: Pause or resume the simulation.
Reset Button: Reset the game board to a new random state.

### Requirements
Java 8 or later
JavaFX SDK (for GUI support)




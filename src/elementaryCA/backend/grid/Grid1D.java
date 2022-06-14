package elementaryCA.backend.grid;
/**
 * Created by Joseph Haugh
 * CS 251, Fall 2020
 * Modified by Devendra Sawod
 */

import elementaryCA.backend.cell.Cell;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import langtonsLoop.Runner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Grid1D implements Runner {
    // Used by JavaFX to display the visualization
    private final GridPane GRID_PANE;
    // Width/height of cell
    private final double CELL_SIZE;

    private Map<String,Character> map;

    // Current generation of cells
    private List<Cell> currentGen;
    // Current generation (row) being shown to the screen
    private int currentGenIndex;
    // Class Constructor
    public Grid1D(GridPane gridPane,
                  String behavior,
                  List<Cell> currentGen,
                  double cellSize) {
        this.GRID_PANE = gridPane;
        // 8 bit string representing the behavior of the CA
        this.currentGen = currentGen;
        this.CELL_SIZE = cellSize;
        this.currentGenIndex = 0;
        // create a hash map of behavior
        initiateMap(behavior);
        // Show the initial generation to the screen
        show();
    }

    /**
     *
     * @param behavior
     * behaviour is string representation of number entered by user. If the behavior length is less than 8,
     *     zeros are appended in front of the behavior.
     *     characters from behavior are mapped to string pattern. Ex "111" is mapped to character at behavior(0).
     */
    private void initiateMap(String behavior) {
        map = new HashMap<>();
        int strLength = behavior.length();
        while ( strLength<8){
            behavior = "0"+ behavior;
            strLength++;
        }
        map.put("111",behavior.charAt(0));
        map.put("110",behavior.charAt(1));
        map.put("101",behavior.charAt(2));
        map.put("100",behavior.charAt(3));
        map.put("011",behavior.charAt(4));
        map.put("010",behavior.charAt(5));
        map.put("001",behavior.charAt(6));
        map.put("000",behavior.charAt(7));
    }

    /**
     * TODO: Fill in the logic below
     * I would suggest starting off by hard coding one of the rules,
     * such as rule 30, then generalize from there.
     * This function evolves the current generation to the next generation
     * using the current rule set given by the behavior string.
     * String "key" is created by appending Left neighbor, cell being looked and right neighbor.
     * Left neighbor and right neighbor are implemented with wrap around function such that
     *     leftmost cell has left neighbor which is rightmost cell and
     *     rightmost cell has left neighbor which is leftmost cell
     * The key generated is used to determine next state of the cell.
     */
    private void evolve() {
        List<Cell> temp = new ArrayList<>();
        int size = currentGen.size();
        String key;
        for ( int index = 0; index < size; index++ ){
            Cell leftNeighbor = currentGen.get((index+size-1)%size);
            Cell me = currentGen.get(index);
            Cell rightNeighbor = currentGen.get((index+1)%size);
            key = leftNeighbor.toString()+me.toString()+rightNeighbor.toString();
            Character nextCellChar = map.get(key);
            temp.add(index,Cell.fromChar(nextCellChar));
        }
        currentGen = temp;
    }

    /**
     * This function shows the current generation to the JavaFX window
     */
    private void show() {
        int colIndex = 0;
        // Create new rectangles to show for the current generation
        for (Cell cell : currentGen) {
            // Create a rectangle to represent the cell
            Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE, cell.getColor());
            // Add it to the JavaFX graph
            GRID_PANE.getChildren().add(rect);
            // Tell it where to show it on the screen
            GridPane.setConstraints(rect, colIndex, currentGenIndex);
            // Go to the next cell
            colIndex++;
        }
        currentGenIndex++;
    }

    /**
     * This function advances the state of the class to the next generation.
     * It then shows this new generation to the Java FX window.
     */
    public void nextGeneration() {
        evolve();
        show();
    }

    @Override
    public void run() {
        AnimationTimer timer = new AnimationTimer() {
            private long prevUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - prevUpdate >= TimeUnit.SECONDS.toNanos(1)) {
                    nextGeneration();
                    prevUpdate = now;
                }
            }
        };
        timer.start();
    }

}

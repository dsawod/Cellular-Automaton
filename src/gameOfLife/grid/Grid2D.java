package gameOfLife.grid;
import gameOfLife.cell.Cell;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import langtonsLoop.Runner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class is 2D Cellular Automata which implements Interface Runner with only run function.
 * It has GridPane, 2D Character Array, List of List of Cell and cellSize as private field.
 */

public class Grid2D implements Runner {
    private final GridPane GRID;
    private  Character [][] array;
    private  double cellSize;
    private  List<List<Cell>> CELLS = new ArrayList<>();

    /**
     * This is a Class Constructor
     * A method listToArray is called which created 2D Array pof Characters
     * Initial grid is set up using array created.
     */

    public Grid2D(GridPane grid, List<String> list, double cellSize) {
        this.GRID = grid;
        this.cellSize = cellSize;
        array = listToArray(list);
        setInitialGrid(array);
    }

    /**
     *
     * @param array
     * The Array is character type
     * Since the array is 2D, we need two nested for loops to get each elements
     * Each element is changed to Cell with method fromChar in Cell class
     * The Cell above is added to the list of list of cells
     */
    private void setInitialGrid(Character[][] array) {
        int size = array.length;
        for (Character[] characters : array) {
            List<Cell> rowOfCells = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Cell newCell = Cell.fromChar(characters[j]);
                rowOfCells.add(newCell);
            }
            CELLS.add(rowOfCells);
        }
    }

    /**
     *
     * @param list
     * @return 2D Character Array
     * Outer for loop gets string from the list. The inner for loop gets characters of the
     *     string and add character into an array.
     */
    private Character[][] listToArray(List<String> list) {
        int size = list.size();
        Character [][] charArray = new Character [size][size];
        for (int index = 0; index < size; index++){
            String rowString = list.get(index);
            for (int i= 0; i < size; i++){
                charArray[index][i] = rowString.charAt(i);
            }
        }
        return charArray;
    }

    public void nextGeneration() {
        evolve();
        show();
    }

    /**
     * In this method, each element from the 2D array are accessed.
     * Two nested for loop help access the neighbors of the element being looked in manner
     *     that allow for wrap around property.
     * It keeps track of alive neighbors. If it is 1, it is alive.
     * The number of alive neighbors determine the next state of the cell being looked.
     * The method is called to determine the next state.
     */
    private void evolve() {
        int rowSize = array.length;
        int columnSize = array[0].length;
        Character[][] newArray =new Character[rowSize][columnSize];
        for (int row =0; row<rowSize; row++){
            for ( int column=0; column<columnSize; column++){
                int numOfLiveNeighbor = 0;
                if ( array[row][(column+columnSize-1)%columnSize] == '1'){    //West Neighbor
                    numOfLiveNeighbor++;
                }
                if ( array[row][(column+1)%columnSize] == '1'){    //East Neighbor
                    numOfLiveNeighbor++;
                }
                if ( (array[(row+rowSize-1)%rowSize][column]) == '1'){    //North neighbor
                    numOfLiveNeighbor++;
                }
                if ((array[(row+1)%rowSize][column]) == '1'){    //South Neighbor
                    numOfLiveNeighbor++;
                }
                if ((array[(row+rowSize-1)%rowSize][(column+columnSize-1)%columnSize]) == '1'){    //NorthWest Neighbor
                    numOfLiveNeighbor++;
                }
                if ((array[(row+rowSize-1)%rowSize][(column+1)%columnSize]) == '1'){    //NorthEast Neighbor
                    numOfLiveNeighbor++;
                }
                if ((array[(row+1)%rowSize][(column+columnSize-1)%columnSize]) == '1'){    //SouthWest Neighbor
                    numOfLiveNeighbor++;
                }
                if ((array[(row+1)%rowSize][(column+1)%columnSize]) == '1'){    //SouthEast Neighbor
                    numOfLiveNeighbor++;
                }
                char newState = determineNextState(numOfLiveNeighbor,array[row][column]);
                newArray[row][column] = newState;
            }
        }
        array = newArray;
    }

    /**
     *
     * @param numOfLiveNeighbor
     * @param me
     * @return new state for me
     * If current state is dead
     *     If three of the eight neighbors alive and current state is dead, cell becomes alive.
     * if current state is alive
     *     if alive neighbors  are less than two, cell dies.
     *     else if alive neighbors are greater than 3, cell dies.
     *     else cell stays alive.
     */
    private char determineNextState(int numOfLiveNeighbor, Character me) {
        char newMe = me;
        if (me == '0'){
            if ( numOfLiveNeighbor == 3){
                newMe = '1';
            }
        }
        if(me=='1') {
            if (numOfLiveNeighbor < 2){
                newMe = '0';
            }
            if ( numOfLiveNeighbor > 3){
                newMe = '0';
            }
        }
        return newMe;
    }

    /**
     * The show method implementation in Blinker Class was used as a reference here.
     * The implementation is very similar.
     * Two nested for loops are used to access every elements in the 2D array of character type.
     * Each character gets converted to Cell type and is the color of that Cell is added to the GRID.
     */
    private void show(){
        GRID.getChildren().clear();
        int rowIndex = 0;
        for (Character[] characters : array) {
            for (int j = 0; j < characters.length; j++) {
                Cell cell = Cell.fromChar(characters[j]);
                Rectangle rect = new Rectangle(cellSize, cellSize, cell.getColor());
                GRID.add(rect,j,rowIndex);
            }
            rowIndex++;
        }
    }

    /**
     * This was provided with project skeleton
     */

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

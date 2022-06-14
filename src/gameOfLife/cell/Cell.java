package gameOfLife.cell;
import javafx.scene.paint.Color;

public enum Cell {
    RED ("dead"),
    GREEN("alive");

    private String state;
    // Constructor
    Cell(String state) {
        this.state = state;
    }

    /**
     *
     * @return Color
     *
     */
    public Color getColor() {
        if (this == RED) {
            return Color.RED;
        } else {
            return Color.GREEN;
        }
    }

    /**
     *
     * @param c
     * @return
     * @throws IllegalArgumentException
     * This method returns Cell from characters 0 and 1.
     * If character is 0, Cell is RED
     * else Cell is Green
     */
    public static Cell fromChar(char c) throws IllegalArgumentException {
        if (c == '0') {
            return RED;
        } else if (c == '1') {
            return GREEN;
        } else {
            throw new IllegalArgumentException("Input char must be either 0 or 1.");
        }
    }

    /**
     *
     * @return String corresponding to Cell
     * If Cell is RED, string is zero
     * else string is 1.
     */
    @Override
    public String toString() {
        if ( this == RED){
            return "0";
        } else{
            return "1";
        }
    }
}

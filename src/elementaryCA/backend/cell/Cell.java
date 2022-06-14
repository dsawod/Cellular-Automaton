package elementaryCA.backend.cell;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum Cell {
    WHITE(false),
    BLACK(true);

    private boolean state;

    Cell(boolean state) {
        this.state = state;
    }

    public boolean isOn() {
        return state;
    }

    public static Cell fromChar(char c) throws IllegalArgumentException {
        if (c == '0') {
            return WHITE;
        } else if (c == '1') {
            return BLACK;
        } else {
            throw new IllegalArgumentException("Input char must be either 0 or 1.");
        }
    }

    @Override
    public String toString() {
        if ( this == WHITE){
            return "0";
        } else{
            return "1";
        }
    }

    public Paint getColor() {
        if (this == WHITE){
            return Color.WHITE;
        }
        else return Color.BLACK;
    }
}

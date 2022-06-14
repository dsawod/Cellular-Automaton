package langtonsLoop.cell;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum Cell {
    WHITE("0",Color.WHITE),
    BLACK("1",Color.BLACK),
    DARK_GRAY("2",Color.DARKGRAY),
    CYAN("3",Color.CYAN),
    BLUE("4",Color.BLUE),
    GREEN("5",Color.GREEN),
    MAGENTA("6",Color.MAGENTA),
    ORANGE("7",Color.ORANGE);

    private String num;
    private Color color;

    Cell(String s, Color color) {

        this.num = s;
        this.color = color;
    }

    public static Cell fromChar(char c) throws IllegalArgumentException {
        if (c == '0') {
            return WHITE;
        }
        else if (c == '1') {
            return BLACK;
        }
        else if (c == '2') {
            return DARK_GRAY;
        }
        else if (c == '3') {
            return CYAN;
        }
        else if (c == '4') {
            return BLUE;
        }
        else if (c == '5') {
            return GREEN;
        }
        else if (c == '6') {
            return MAGENTA;
        }
        else if (c == '7') {
            return ORANGE;
        }
        else {
            throw new IllegalArgumentException("Input char must be [0-7].");
        }
    }
    public Paint getColor(){
        return this.color;
    }

    @Override
    public String toString() {

        if (this == WHITE) {
            return "0";
        } else if (this == BLACK) {
            return "1";
        } else if (this == DARK_GRAY) {
            return "2";
        } else if (this == CYAN) {
            return "3";
        } else if (this == BLUE) {
            return "4";
        } else if (this == GREEN) {
            return "5";
        } else if (this == MAGENTA) {
            return "6";
        } else {
            return "7";
        }
    }

    /*
    public Paint getColor() {
        if (this == WHITE){
            return Color.WHITE;
        }else if (this == BLACK) {
            return Color.BLACK;
        } else if (this == DARK_GRAY) {
            return Color.DARKGRAY;
        } else if (this == CYAN) {
            return Color.CYAN;
        } else if (this == BLUE) {
            return Color.BLUE;
        } else if (this == GREEN) {
            return Color.GREEN;
        } else if (this == MAGENTA) {
            return Color.MAGENTA;
        } else {
            return Color.ORANGE;
        }
    }

     */

}

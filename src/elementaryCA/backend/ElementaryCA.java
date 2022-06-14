package elementaryCA.backend;
/**
 * Author: Joseph Haugh
 * CS 251, Fall 2020
 * Modified by Devendra Sawod
 * This class is main of 1D cellular automata.
 */

import elementaryCA.backend.cell.Cell;
import elementaryCA.backend.grid.Grid1D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ElementaryCA extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * @param primaryStage
     * @throws Exception
     * This method with its JavaFX part implemented was provided with skeleton.
     * The codes for parsing were added later.
     * User is asked if he/she wish to generator or read from a txt files.
     * If read file is chosen then, file path is already hardcoded. User only need to
     *     choose which rule file to read from. The text files contain rule behavior
     *     and first generation.
     * If choice is generate then
     *     user is prompted to provide number between 1-255 which is converted to binary string of behavior.
     *     User also need to enter first generation string combination of 1s and 0s.
     * String entered is then converted into first generation of Cells.
     *     If 0 Cell is White, else Cell is Black.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("1D Cellular Automata");
        GridPane root = new GridPane();
        double width = 1380;
        double height = 700;
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();

        Scanner scan = new Scanner(System.in);
        System.out.println("This shows one dimensional cellular automata");
        System.out.println("[g] to generate");
        System.out.println("[r] to read from text file");
        String choice = scan.next();
        String firstGenStr=null;
        String behavior = null;

        switch(choice){
            case "r":
                System.out.println("[30] to read rule30.txt");
                System.out.println("[126] to read rule126.txt");
                int fileNum = scan.nextInt();
                if ( fileNum == 30){
                    File file = new File("resources/elementaryCA/rule30.txt");
                    Scanner sc = new Scanner(file);
                    while (sc.hasNext()){
                        behavior = sc.nextLine();
                        firstGenStr = sc.nextLine();
                    }
                } else if (fileNum == 126){
                    File file = new File("resources/elementaryCA/rule126.txt");
                    Scanner sc = new Scanner(file);
                    while (sc.hasNext()){
                        behavior = sc.nextLine();
                        firstGenStr = sc.nextLine();
                    }
                } else{
                    System.out.println("File does not exists!");
                }
                break;
            case "g":
                int ruleNum;
                do{
                    System.out.println("[1-255] Choose a number to generate");
                    ruleNum = scan.nextInt();
                }while(ruleNum>255);

                do{
                    System.out.println("Enter string of 1s and 0s for first generation");
                    firstGenStr = scan.nextLine();
                }while(!firstGenStr.matches("[01]+"));
                behavior = Integer.toBinaryString(ruleNum);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }

        List<Cell> firstGen = new ArrayList<>();
        // TODO: Turn the firstGen string into a list of cells
        int firstGenSize = firstGenStr.length();
        for (int index = 0; index < firstGenSize; index++){
            char c = firstGenStr.charAt(index);
            firstGen.add(Cell.fromChar(c));
        }
        double cellSize = width/firstGenSize;
        Grid1D grid = new Grid1D(root, behavior, firstGen, cellSize);
        grid.run();
    }
}

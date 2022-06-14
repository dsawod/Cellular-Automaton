package gameOfLife;
import gameOfLife.grid.Grid2D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Devendra Sawod
 * Reference used Elementary CA by Joseph Haugh
 * The JavaFX are almost same as Elementary CA
 * The user is prompted to read from a file
 *     or enter row and column size and provide initial row strings of 0s and 1s
 * User only need to select which file to read. Filepath is hardcoded.
 * The file is read and list of strings is created with help of listFromFile method.
 */
public class GameOfLife extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        List strList = new ArrayList<>();
        primaryStage.setTitle("Game of Life");
        GridPane root = new GridPane();
        double width = 1380;
        double height = 700;
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();

        Scanner scan = new Scanner(System.in);
        System.out.println("[g] to generate");
        System.out.println("[r] to read from text file");
        String choice = scan.next();
        switch (choice) {
            case "r":
                File file;
                System.out.println("[b] to read from blinker.txt");
                System.out.println("[d] to read dies1.txt");
                System.out.println("[dd] to read from dies2.txt");
                System.out.println("[gl] to read from glider1.txt");
                System.out.println("[r] to read repeats1.txt");
                System.out.println("[s] to read stable1.txt");
                System.out.println("[ss] to read stable2.txt");
                String fileToRead = scan.next();
                if (fileToRead.equalsIgnoreCase("b")) {
                    file = new File("resources/gameOfLife/blinker.txt");
                    strList = listFromFile(file);
                }
                else if (fileToRead.equalsIgnoreCase("d")) {
                    file = new File("resources/gameOfLife/dies1.txt");
                    strList = listFromFile(file);
                }
                else if (fileToRead.equalsIgnoreCase("dd")) {
                    file = new File("resources/gameOfLife/dies2.txt");
                    strList = listFromFile(file);
                }
                else if (fileToRead.equalsIgnoreCase("gl")) {
                    file = new File("resources/gameOfLife/glider1.txt");
                    strList = listFromFile(file);
                }
                else if (fileToRead.equalsIgnoreCase("r")) {
                    file = new File("resources/gameOfLife/repeats1.txt");
                    strList = listFromFile(file);
                }
                else if (fileToRead.equalsIgnoreCase("s")) {
                    file = new File("resources/gameOfLife/stable1.txt");
                    strList = listFromFile(file);
                }
                else if (fileToRead.equalsIgnoreCase("ss")) {
                    file = new File("resources/gameOfLife/stable2.txt");
                    strList = listFromFile(file);
                }
                else {
                    System.out.println("File does not exists");
                }
                break;

            case "g":
                System.out.println("Enter number of row:");
                int rowSize = scan.nextInt();
                System.out.println("Enter number of column:");
                int columnSize = scan.nextInt();
                for ( int index = 0; index < rowSize; index++){
                    System.out.println("Enter combination of 0s and 1s for initial grid");
                    String strGen = scan.next();
                    while(!strGen.matches("[01]+")){
                        System.out.println("Enter combination of 0s and 1s for initial grid:");
                    }
                    while (strGen.length() != columnSize){
                        System.out.println("Input must be equal to column size you entered:");
                        strGen = scan.next();
                    }
                    strList.add(strGen);
                }
                break;

            default:
                System.out.println("Invalid choice:" + choice);

        }

        String str = (String)strList.get(0);
        int columnSize = str.length();
        int rowSize = strList.size();
        double cellSize;
        if ( columnSize>= rowSize){
            cellSize = height/rowSize;
        } else{
            cellSize = height/columnSize;
        }

        Grid2D grid = new Grid2D(root, strList, cellSize);
        grid.run();
    }

    /**
     *
     * @param file
     * @return list of strings
     * The scanner is used to get the string from the file.
     * The strings read are stored in a list.
     */
    private List listFromFile(File file) {
        List<String> localList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            int rowSize = Integer.parseInt(sc.next());
            int columnSize = Integer.parseInt(sc.next());
            while(sc.hasNext()) {
                String strGen = sc.next();
                localList.add(strGen);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return localList;
    }
}


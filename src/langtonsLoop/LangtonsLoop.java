package langtonsLoop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import langtonsLoop.grid.LangtonsGrid;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class sets up GridPane for Langton`s Loop.
 * It also reads initial configuration file and rule file and create lists of strings.
 *
 */

public class LangtonsLoop extends Application {

    public static void main(String[] args) {
         launch(args);
    }

    /**
     * Created by Devendra Sawod
     * Reference used is Elementary CA by Joseph Haugh
     *
     * @param primaryStage
     * The JavaFX part is almost same as Elementary CA, however
     *     user don`t need to input anything.
     * The initial configuration of grid and rules are read from files in resources.
     * The file paths are hardcoded in the method.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Langton`s Loop");
        GridPane root = new GridPane();
        double width = 1380;
        double height = 700;
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();

        File fileInitialConfig = new File("resources/langtonsLoop/init_config.txt");
        List<String> configList = createInitialConfigList(fileInitialConfig) ;
        File ruleFile = new File("resources/langtonsLoop/rule_table.txt");
        List<String> ruleList = createRuleList(ruleFile);

        int listSize = configList.size();
        double cellSize = height/listSize;
        LangtonsGrid grid = new LangtonsGrid(root,configList,ruleList, cellSize);
        grid.run();
    }

    /**
     *
     * @param fileInitialConfig
     * @return list
     * The file has size of row and column for initial grid configuration
     *     and rest of it is initial gird values.
     * Scanner is used for parsing
     * The file is read row by row. Each row is temporarily stored in String variable strGen,
     *     then strGen is stored in a list that is returned to method call.
     * At termination, we have list of row of strings of initial configuration
     */
    private List<String> createInitialConfigList(File fileInitialConfig) {
        List<String> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(fileInitialConfig);
            String rowSize = sc.next();
            String columnSize = sc.next();
            while(sc.hasNext()) {
                String strGen = sc.next();
                list.add(strGen);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param ruleFile
     * @return list
     * The file is read row by row. Each row is temporarily stored in String variable strRule,
     *     then strRule is stored in a list that is returned to method call.
     */
    private List<String> createRuleList(File ruleFile) {
        List<String> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(ruleFile);
            while(sc.hasNext()) {
                String strRule = sc.next();
                list.add(strRule);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}

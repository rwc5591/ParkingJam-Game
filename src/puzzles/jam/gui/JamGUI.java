package puzzles.jam.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import puzzles.common.Observer;
import puzzles.jam.model.JamConfig;
import puzzles.jam.model.JamModel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;
/**
 *
 *
 * Reg Chuhi
 * **/

public class JamGUI extends Application  implements Observer<JamModel, String>  {
    /** The resources directory is located directly underneath the gui package */
    private final static String RESOURCES_DIR = "resources/";

    private final static String X_CAR_COLOR = "#DF0101";
    private final static String A_CAR_COLOR = "#01DF6C";
    private final static String B_CAR_COLOR = "#C65A27";
    private final static String C_CAR_COLOR = "#0A0BEC";
    private final static String D_CAR_COLOR = "#AF29BD";
    private final static String E_CAR_COLOR = "#5B4FE0";
    private final static String F_CAR_COLOR = "#163910";
    private final static String G_CAR_COLOR = "#555755";
    private final static String H_CAR_COLOR = "#827C7C";
    private final static String I_CAR_COLOR = "#DFD374";
    private final static String J_CAR_COLOR = "#43391E";
    private final static String K_CAR_COLOR = "#58714B";
    private final static String L_CAR_COLOR = "#FFFFFF";
    private final static String O_CAR_COLOR = "#FFFF54";
    private final static String P_CAR_COLOR = "#8117F5";
    private final static String Q_CAR_COLOR = "#306FF6";
    private final static String R_CAR_COLOR = "#499D38";
    private final static String S_CAR_COLOR = "#499D38";


    private final static int BUTTON_FONT_SIZE = 20;
    private final static int ICON_SIZE = 75;
    private FlowPane fp;
    private Stage stage;
    private BorderPane bp;
    private Label lb;
    private GridPane pane;
    private JamModel model;



    public JamGUI(){
        this.fp=new FlowPane();
        this.stage= new Stage();
        this.bp=new BorderPane();
        this.lb= new Label();
        this.pane= new GridPane();
        this.model=new JamModel();

    }


    public void init() {
        String filename = getParameters().getRaw().get(0);
        model=new JamModel();
        model.addObserver(this);
        model.loadGame(filename);
    }

    /**
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Button button1 = new Button();
        button1.setStyle(
                "-fx-font-size: " + BUTTON_FONT_SIZE + ";" +
                "-fx-background-color: " + X_CAR_COLOR + ";" +
                "-fx-font-weight: bold;");
        button1.setText("X");
        button1.setMinSize(ICON_SIZE, ICON_SIZE);
        button1.setMaxSize(ICON_SIZE, ICON_SIZE);

        bp = new BorderPane(pane);
        lb = new Label();
        fp = new FlowPane(lb);
        bp.setTop(fp);
        //creates the buttons at the bottom of the grid and sets the on Action events
        Button btn1 = new Button("Load");
        btn1.setOnAction(event -> fileChooser());
        Button btn2 = new Button("Reset");
        btn2.setOnAction(event -> model.reset());
        Button btn3 = new Button("Hint");
        btn3.setOnAction(event -> model.getHint() );
        FlowPane fpBtm= new FlowPane(btn1, btn2, btn3);
        bp.setBottom(fpBtm);
        Scene scene = new Scene(bp);
        stage.setTitle("Jam");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the path to load the game
     */
    public void fileChooser(){
        //create a new FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Loaded ");
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        currentPath += File.separator + "data" + File.separator + "jam";
        fileChooser.setInitialDirectory(new File(currentPath));
       // fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+"/boards"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        model.loadGame("data/jam/"+ selectedFile.getName());

    }


    /**
     * @param jamModel the object that wishes to inform this object
     *                 about something that has happened.
     * @param message  optional data the server.model can send to the observer
     */
    @Override
    public void update(JamModel jamModel, String message) {
        //set message
        lb.setText(message);
        int row=6;
        int col=6;
        pane.getChildren().clear();
        //loop through the rows and column and create new buttons depending on the loaded game specifications
        for(int i=0; i< JamConfig.row; i++){
            for(int j=0; j<JamConfig.col; j++){
                Button btn = new Button();
                btn.setStyle(
                        "-fx-font-size: " + BUTTON_FONT_SIZE + ";" +
                               // "-fx-background-color: " + X_CAR_COLOR + ";" +
                                "-fx-font-weight: bold;");
                btn.setText(String.valueOf(model.getCell(i,j)));
                btn.setMinSize(ICON_SIZE, ICON_SIZE);
                btn.setMaxSize(ICON_SIZE, ICON_SIZE);
                /**
                 * sets the car colors
                 * */

                switch(model.getCell(i,j)){
                    case 'A'-> btn.setStyle("-fx-background-color: " + A_CAR_COLOR + ";");
                    case 'B'-> btn.setStyle("-fx-background-color: " + B_CAR_COLOR + ";");
                    case 'C'-> btn.setStyle("-fx-background-color: " + C_CAR_COLOR + ";");
                    case 'D'-> btn.setStyle("-fx-background-color: " + D_CAR_COLOR + ";");
                    case 'E'-> btn.setStyle("-fx-background-color: " + E_CAR_COLOR + ";");
                    case 'F'-> btn.setStyle("-fx-background-color: " + F_CAR_COLOR + ";");
                    case 'G'-> btn.setStyle("-fx-background-color: " + G_CAR_COLOR + ";");
                    case 'H'-> btn.setStyle("-fx-background-color: " + H_CAR_COLOR + ";");
                    case 'I'-> btn.setStyle("-fx-background-color: " + I_CAR_COLOR + ";");
                    case 'J'-> btn.setStyle("-fx-background-color: " + J_CAR_COLOR + ";");
                    case 'K'-> btn.setStyle("-fx-background-color: " + K_CAR_COLOR + ";");
                    case 'L'-> btn.setStyle("-fx-background-color: " + L_CAR_COLOR + ";");
                    case 'O'-> btn.setStyle("-fx-background-color: " + O_CAR_COLOR + ";");
                    case 'P'-> btn.setStyle("-fx-background-color: " + P_CAR_COLOR + ";");
                    case 'Q'-> btn.setStyle("-fx-background-color: " + Q_CAR_COLOR + ";");
                    case 'R'-> btn.setStyle("-fx-background-color: " + R_CAR_COLOR + ";");
                    case 'S'-> btn.setStyle("-fx-background-color: " + S_CAR_COLOR + ";");
                    case 'X'-> btn.setStyle("-fx-background-color: " + X_CAR_COLOR + ";");



                    default -> btn.setText("");

                }

                int finalJ = j;
                int finalI = i;
                btn.setOnAction(event -> model.select(finalI,finalJ));

                pane.add(btn, j,i);


            }
        }
        bp.setCenter(pane);

    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java JamGUI filename");
        } else {
            Application.launch(args);
        }
    }
}

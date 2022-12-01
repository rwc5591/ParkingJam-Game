package puzzles.jam.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import puzzles.common.Observer;
import puzzles.jam.model.JamModel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JamGUI extends Application  implements Observer<JamModel, String>  {
    /** The resources directory is located directly underneath the gui package */
    private final static String RESOURCES_DIR = "resources/";

    // for demonstration purposes
    private final static String X_CAR_COLOR = "#DF0101";
    private final static int BUTTON_FONT_SIZE = 20;
    private final static int ICON_SIZE = 75;
    private FlowPane fp;
    private BorderPane bp;
    private Label lb;
    private GridPane pane;
    private JamModel model;


    public void init() {
        String filename = getParameters().getRaw().get(0);
    }

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
        pane = new GridPane();
        //Sets the horizontal space between tiles
        pane.setHgap(3);
        //Sets the vertical space between tiles
        pane.setVgap(3);
        int row=6;
        int col=6;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                Button btn = new Button();
                int finalJ = j;
                int finalI = i;

                btn.setPrefSize(70,70);
                //adds button to the grid
                pane.add(btn, i,j);
            }
        }
        bp = new BorderPane(pane);
        lb = new Label(" ");
        fp = new FlowPane(lb);
        bp.setTop(fp);
        //creates the buttons at the bottom of the grid and sets the on Action events
        Button btn1 = new Button("Load");

        Button btn2 = new Button("Reset");

        Button btn3 = new Button("Hint");


        FlowPane fpBtm= new FlowPane(btn1, btn2, btn3);
        bp.setBottom(fpBtm);

        Scene scene = new Scene(bp);
        stage.setTitle("Jam");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void update(JamModel jamModel, String message) {
//        this.message.setText("Message: " + message);
//        //update board
//        StackPane[][] board = generateBoard();
//        this.boardGrid = new GridPane();
//        for(int i = 0; i < board.length; i++){
//            for(int j = 0; j < board[i].length; j++){
//                boardGrid.add(board[i][j], i, j);
//            }
//
//       Things that you need from start() should be declared as private data members so that you can use them in update().
//
//In your case you are building a new GridPane in update().  That should be put in place of the old GridPane in what I assume is a BorderPane from start().  Make the BorderPane in start() a private data member and then you can set the GridPane in it in update().
//
//p.s. The Stage that gets passed into start() should also be stored as a private data member so you can do stage.sizeToScene() in update() to resize the window correctly when adding a smaller GridPane (e.g. load).
//
//        }


    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java JamGUI filename");
        } else {
            Application.launch(args);
        }
    }
}

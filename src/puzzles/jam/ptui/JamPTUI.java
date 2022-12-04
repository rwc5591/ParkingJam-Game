
package puzzles.jam.ptui;

import javafx.application.Application;
import javafx.stage.FileChooser;
import puzzles.common.Observer;
import puzzles.jam.model.JamConfig;
import puzzles.jam.model.JamModel;

import java.io.File;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static puzzles.jam.model.JamConfig.col;
import static puzzles.jam.model.JamConfig.row;

public class JamPTUI implements Observer<JamModel, String> {
    private JamModel model;
    private Scanner in;
    private boolean gameOn;
    private String msg;

    public JamPTUI(){
        model=new JamModel();
        model.addObserver(this);
        gameOn = false;
        in = new Scanner( System.in );
    }


    public boolean loadFromFile(){
        boolean ready = false;

        while(!ready){
            //create a new FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Loaded ");
            String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            currentPath += File.separator + "data" + File.separator + "jam";
            fileChooser.setInitialDirectory(new File(currentPath));
            // fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+"/boards"));
           // File selectedFile = fileChooser.showOpenDialog();
           // model.loadGame("data/jam/"+ selectedFile.getName());


            System.out.println("Enter a valid file name or type Q to go back.");
            String command =  in.next();
            if (command.equals("quit") || command.equals("QUIT")) {
                System.out.println("going back...");
                return false;
            }
            model.loadGame(command);


        }
        return true;
    }
    public boolean gameStart(){

        boolean ready = false;
        while(!ready){
            System.out.println("h(int)              -- hint next move\n" +
                    "l(oad) filename     -- load new puzzle file\n" +
                    "s(elect) r c        -- select cell at r, c\n" +
                    "q(uit)              -- quit the game\n" +
                    "r(eset)             -- reset the current game");
            String command =  in.next();
            switch (command) {
                case "RESET", "reset" -> {
                    model.reset();
                    ready = true;
                }
                case "LOAD", "load" -> ready = loadFromFile();
                case "SELECT", "select" -> {
                    model.select(row,col);
                    ready = true;
                }
                case "Hint", "hint" -> {
                    model.getHint();
                    ready = true;
                }
                case "QUIT", "quit" -> {
                    System.out.println("Exiting");
                    ready = true;
                    in = new Scanner(System.in);//get rid of any remaining commands from the start menu
                    return false;
                }

                default -> System.out.println("Enter RESET, LOAD, SELECT,HINT or QUIT.");
            }
            gameOn = true;
        }
        in = new Scanner(System.in);//get rid of any remaining commands from the start menu
        return true;
    }
    public void displayBoard(){
        System.out.print("  ");
        for(int i=0; i< JamConfig.row; i++){
            for(int j=0; j<JamConfig.col; j++){
            System.out.print(i+" ");
        }
            int currentRow = -1;
            for (int t = 0; t < model.getCell(row,col); t++) {
                    if(currentRow!=t){
                        currentRow=t;
                        System.out.printf("%n%d ",currentRow);
                    }

                    System.out.print(" ");

                }

                }

            }
    private void gameLoop(){
        String msg;

        while(gameOn) {
            msg = "";
            System.out.println("");
            String command = in.nextLine().strip();
            if (command.equals("quit") || command.equals("QUIT")) {
                System.out.println("Quitting to main menu.");
                gameOn = false;

                return;

            } else if(command.equals("hint")||command.equals("HINT")){
                model.getHint();


            } else {
                try {
                    Scanner s = new Scanner(command);
                    int x = s.nextInt();
                    int y = s.nextInt();
                    model.select(x, y);

                } catch (InputMismatchException e) {

                    msg = "X and Y must be integers";
                } catch (NoSuchElementException e) {

                    msg = "Must enter X and Y on one line.";
                } catch (IndexOutOfBoundsException e) {
                    throw new RuntimeException(e);
                }
            }


            if (!msg.isEmpty())

                System.out.println("Command: "+command+"\n\033[0;1m***"+msg+"***\033[0;0m");

        }
    }
    public void run() {
        while (true) {
            if (!gameStart()) //loads new games or quits
                break;
            gameLoop(); // gameplay
        }

    }


    /**
     * The observed subject calls this method on each observer that has
     * previously registered with it. This version of the design pattern
     * follows the "push model" in that the subject can provide
     * ClientData to inform the observer about what exactly has happened.
     * But this convention is not required. It may still be necessary for
     * the observer to also query the subject to find out more about what has
     * happened. If this is a simple signal with no data attached,
     * or if it can safely be assumed that the observer already has a
     * reference to the subject, even the subject parameter may be null.
     * But as always this would have to be agreed to by designers of both sides.
     *
     * @param jamModel the object that wishes to inform this object
     *                 about something that has happened.
     * @param s        optional data the server.model can send to the observer
     * @see <a href="https://sourcemaking.com/design_patterns/observer">the
     * Observer design pattern</a>
     */
    @Override
    public void update(JamModel jamModel, String s) {
        if (msg.equals(JamModel.LOADED)){ // game is loaded successfully
            System.out.println("Loaded ");
            displayBoard();
            return;
        }else if (msg.equals(JamModel.LOAD_FAILED)){ //Game failed to load
            System.out.println("Error Loading Game");
            return;
        } else if (msg.startsWith(JamModel.HINT_PREFIX)) { //Model is reporting a  hint
            System.out.println(msg);
            return;
        }

        if (model.gameOver()) { //checks if game is over.
            displayBoard();

            System.out.println("You win. Good for you.");
            gameOn = false;
            return;
        }
        displayBoard(); // renders the board
        System.out.println(msg);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java JamPTUI filename");
//        }
            JamPTUI ui = new JamPTUI();
            ui.run();

        }
    }}
//package puzzles.jam.ptui;
//
//import puzzles.common.Observer;
//import puzzles.jam.model.JamModel;
//
//import java.util.InputMismatchException;
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//
//import static puzzles.jam.model.JamConfig.col;
//import static puzzles.jam.model.JamConfig.row;
//
//public class JamPTUI implements Observer<JamModel, String> {
//    private JamModel model;
//    private boolean gameOn;
//    private Scanner in;
//    private static char ONSYMBOL = 'o';
//    private static char OFFSYMBOL = '.';
//
//
//
//
//    public JamPTUI(){
//        model = new JamModel();
//        model.addObserver(this);
//        gameOn = false;
//        in = new Scanner( System.in );
//
//    }
//    public boolean loadFromFile(){
//        boolean ready = false;
//
//        while(!ready){
//            System.out.println("Enter a valid file name or type Q to go back.");
//            String command =  in.next();
//            if (command.equals("q") || command.equals("Q")) {
//                System.out.println("going back...");
//                return false;
//            }
//             model.loadGame(command);
//
//
//        }
//        return true;
//    }
//    public boolean gameStart(){
//
//        boolean ready = false;
//        while(!ready){
//            System.out.println("(R)andom Board. (L)oad a board. (Q)uit");
//            String command =  in.next(); // Using next allows you to string together load commands like l boards/1.lob.
//            switch (command){
//                case "R":
//                case "r":
//                      model.reset();
//                    ready=true;
//                    break;
//                case "L":
//                case "l":
//                    ready = loadFromFile();
//                    break;
//                case "Q":
//                case "q":
//                    System.out.println("Exiting");
//                    ready = true;
//                    in = new Scanner(System.in);//get rid of any remaining commands from the start menu
//                    return false;
//
//                default:
//                    System.out.println("Enter R, L, or Q.");
//            }
//            gameOn = true;
//        }
//        in = new Scanner(System.in);//get rid of any remaining commands from the start menu
//        return true;
//    }
//    public void displayBoard() {
//        //formatting for text ui is soooo elegant
//        //System.out.print("\033[0;4m"); //turn on underline
//
//        //prints the column number
//        System.out.print("  ");
//        for (int c = 0; c < model.getCell(row, col); c++) {
//            System.out.print(c + " ");
//        }
//        //System.out.print("\033[0;0m"); //turn off underline
//        int currentRow = -1;
//
//        //prints the tiles
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                if (currentRow != model.getCell(currentRow, col)) {
//                    currentRow = model.getCell(row, col);
//                    System.out.printf("%n%d ", currentRow);
//
//                }
//
//
//            }
//
//            System.out.printf("\nTotal Moves: %d\n", JamModel.getMoves());
//            //  System.out.println();
//        }
////        public void run () {
////            //loads new games or quits
////            while (gameStart()) {
////                gameLoop(); // gameplay
////            }
////
////        }
//
//        /**
//         * Handles the actual game play. Gets user input, toggles tiles, and asks for hints. Checks if the game is over and drops back to the main menu if it is.
//         */
//     //   private void gameLoop () String msg;
////        {
////
////            while (gameOn) {
////                msg = "";
////                System.out.println("Enter X Y to toggle a tile, (H)int, or (Q)uit to main menu");
////                String command = in.nextLine().strip();
////                if (command.equals("q") || command.equals("Q")) {
////                    System.out.println("Quitting to main menu.");
////                    gameOn = false;
////
////                    return;
////
////                } else if (command.equals("h") || command.equals("H")) {
////                    model.getHint();
////
////                    // model.toggleTile(hint.getX(),hint.getY());
////
////                } else {
////                    try {
////                        Scanner s = new Scanner(command);
////                        int x = s.nextInt();
////                        int y = s.nextInt();
////                        model.select(x, y);
////
////                    } catch (InputMismatchException e) {
////
////                        msg = "X and Y must be integers";
////                    } catch (NoSuchElementException e) {
////
////                        msg = "Must enter X and Y on one line.";
////                    } catch (IndexOutOfBoundsException e) {
////                        //   msg = String.format("X and Y should be between 0 and %d", model.getDimension());
////                    }
////                }
////
////
////                if (!msg.isEmpty())
////
////                    System.out.println("Command: " + command + "\n\033[0;1m***" + msg + "***\033[0;0m");
////
////            }
////        }
//
//
////        @Override
////        public void update (JamModel model, String msg){
////
////            if (msg.equals(JamModel.LOADED)) { // game is loaded successfully
////                System.out.println("Game Loaded");
////                displayBoard();
////                return;
////            } else if (msg.equals(JamModel.l)) { //Game failed to load
////                System.out.println("Error Loading Game");
////                return;
////                //  } else if (msg.startsWith(JamModel.getHint())) { //Model is reporting a  hint
////                //    System.out.println(msg);
////                //don't display board
////                //    return;
////            }
//
//            // if (model.gameOver()) { //checks if game is over.
////            displayBoard();
//      /* Cool encodings renderable only on cool systems.
//            System.out.print("\033[0;4m"); //turn on underline
//            System.out.print("\033[5m");
//            System.out.println("You win. Good for you.");
//            System.out.print("\033[0;0m");
//         //   System.out.print("\033[0;4m"); //turn on underline
//
//       */
////
////            System.out.println("You win. Good for you.");
////            gameOn = false;
////            return;
////        }
////        displayBoard(); // renders the board
////        System.out.println(msg);
////    }
//
//
////
////    public static void main(String[] args) {
////        if (args.length != 1) {
////            System.out.println("Usage: java JamPTUI filename");
////        }
////        JamPTUI ui = new JamPTUI();
////        ui.run();
////
////
////
////}
//
////    private void run() {
////    }
////
////    private void gameLoop() {
////
////    }
////    }

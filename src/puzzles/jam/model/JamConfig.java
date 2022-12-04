package puzzles.jam.model;



import puzzles.common.solver.Configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class JamConfig implements Configuration {
    private final static char EMPTY = '.';
    public static int row;
    public static int col;
    private static int numCars;
    private char [][] board;
    public static int totalConfigs=1;

    private static HashMap<Character, int[]> carMap;

    public JamConfig(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            System.out.println("Loaded: " + filename);
            String[] fields = reader.readLine().split("\\s+");
            row= Integer.parseInt(fields[0]);
            col= Integer.parseInt(fields[1]);
            numCars = Integer.parseInt(reader.readLine());
            carMap=new HashMap<>();
            for (int i = 0; i < numCars; i++) {
                fields= reader.readLine().split(" ");
                char carName = fields[0].charAt(0);
                int [] carPos = new int[4];
                for (int j = 0; j <carPos.length ; j++) {
                    carPos[j]=Integer.parseInt(fields[j+1]);

                }
                carMap.put(carName,carPos);
            }
            board = new char[row][col];
            for (char c : carMap.keySet()) {
                int [] pos = carMap.get(c);
                if(pos[0]==pos[2]){
                    for (int i = pos[1]; i <= pos[3]; i++) {
                        board[pos[0]][i]= c;
                    }
                }
                else {
                    for (int i = pos[0]; i <= pos[2]; i++) {
                        board[i][pos[1]] = c;
                    }
                }

            }
            //loop the entire board if there's no car set to empty
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j]== 0){
                        board[i][j]= EMPTY;
                    }

                }

            }
            reader.close();
            System.out.println(this);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    private JamConfig(JamConfig other, char car, int row, int col){
        board=new char[JamConfig.row][JamConfig.col];
        for (int i = 0; i <JamConfig.row; i++) {
            for (int j = 0; j < JamConfig.col; j++) {
                if (other.board[i][j]==car){
                    this.board[i][j]=EMPTY;
                }
                else {
                    this.board[i][j]=other.board[i][j];

                }
               // System.out.println(other.board[i][j]);
            }
        }
        int [] pos = carMap.get(car);
       // System.out.println(board.length);
        if(pos[0]==pos[2]){
            for (int i = col; i <= col+(pos[3]-pos[1]); i++) {
                board[pos[0]][i]= car;
            }

        }
        else {
            for (int i = row; i <= row+(pos[2]-pos[0]); i++){
                board[i][pos[1]]= car;
            }
        }

    }

    public char cell(int row, int col){
        return board[row][col];

    }
    public void moveCar(int row, int col, char car) {
        //check car if horizontal move left or right, if vertical move up or down
        board[row][col]=car;
        int[] pos=carMap.get(car);
        if (pos[0] == pos[2]) {
            if(col< JamConfig.col -1){
                if(board[row][col+1]==car){
                   board[row][col+pos[3]-pos[1]+1]=EMPTY;
                }

            }
            if (col>0){
                if(board[row][col-1]==car){
                    board[row][col-(pos[3]-pos[1]+1)]=EMPTY;

            }

            }

        }
        else {
            if(row< JamConfig.row -1){
                if(board[row+1][col]==car){
                    board[row+pos[2]-pos[0]+1][col]=EMPTY;

                }

            }
            if (row>0){
                if(board[row-1][col]==car){
                    board[row-(pos[2]-pos[0]+1)][col]=EMPTY;

                }

                }

            }
        

        }
    public boolean canMove(int row, int col, char car){
        int[] pos=carMap.get(car);
        if (pos[0] == pos[2]){
            if(row!=pos[0]){
                return false;
            }
            if(col< JamConfig.col -1){
                if(board[row][col+1]==car){
                   return true;
                }

            }
            if (col>0){
                if(board[row][col-1]==car){
                    return true;

                }

            }

        }
        else {
            if(col!=pos[1]){
                return false;
            }
            if(row< JamConfig.row -1){
                if(board[row+1][col]==car){
                    return true;
                }

            }
            if (row>0){
                if(board[row-1][col]==car){
                    return true;

                }

            }

        }
        return false;



    }



        @Override
        public boolean isSolution () {

            return board[carMap.get('X')[0]][col-1] == 'X';

        }


        @Override
        public Collection<Configuration> getNeighbors () {
            ArrayList<Configuration> neighbors = new ArrayList<>();
            for (char c : carMap.keySet()) {
                int[] pos = carMap.get(c);
                int currRow = -1;
                int currCol = -1;
                if (pos[0] == pos[2]) {
                    currRow = pos[0];
                    for (int i = 0; i < col; i++) {
                        if (board[currRow][i] == c) {
                            currCol = i;
                            break;
                        }
                    }
                    if (currCol > 0) {
                        if (board[currRow][currCol - 1] == EMPTY) {
                            neighbors.add(new JamConfig(this, c, currRow, currCol - 1));
                            totalConfigs++;
                        }

                    }
                    if (currCol < col - (pos[3] - pos[1] + 1)) {
                        if (board[currRow][currCol + (pos[3] - pos[1] + 1)] == EMPTY) {
                            neighbors.add(new JamConfig(this, c, currRow, currCol + 1));
                            totalConfigs++;
                        }
                    }
                } else {
                    currCol = pos[1];
                    for (int i = 0; i < row; i++) {
                        if (board[i][currCol] == c) {
                            currRow = i;
                            break;
                        }
                    }
                    if (currRow > 0) {
                        if (board[currRow - 1][currCol] == EMPTY) {
                            neighbors.add(new JamConfig(this, c, currRow - 1, currCol));
                            totalConfigs++;
                        }

                    }
                    if (currRow < row - (pos[2] - pos[0] + 1)) {
                        if (board[currRow + (pos[2] - pos[0] + 1)][currCol] == EMPTY) {
                            neighbors.add(new JamConfig(this, c, currRow + 1, currCol));
                            totalConfigs++;
                        }
                    }

                }

            }
            //System.out.println(neighbors);
            return neighbors;

        }


        @Override
        public boolean equals (Object o){
            //  if (this == o) return true;
            if (o instanceof JamConfig that) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (this.board[i][j] != that.board[i][j]) {
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        }

        @Override
        public int hashCode () {
            return Arrays.deepHashCode(board);
        }

        @Override
        public String toString () {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {

                    result.append(board[i][j]);
                    result.append(" ");

                }
                result.append("\n");
                // System.out.println();

            }
            return result.toString();
        }
    }

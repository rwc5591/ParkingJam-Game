package puzzles.tilt.model;

import puzzles.common.solver.Configuration;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class TiltConfig implements Configuration {
    private final static char GREEN = 'G';
    private final static char BLUE = 'B';
    private final static char EMPTY = '.';
    private final static char BLOCKER = '*';
    private final static char HOLE = 'O';
    private int DIM;
    private char [][] BOARD;
    private int numSliders = 0;

    public TiltConfig(String filename) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            System.out.println("File: " + filename);
            DIM = Integer.parseInt(reader.readLine());
            BOARD = new char[DIM][DIM];

            for(int r = 0; r < DIM; r++) {
                String[] fill = reader.readLine().split(" ");

                for(int c = 0; c < DIM; c++) {
                    if(fill[c].charAt(0) == GREEN || fill[c].charAt(0) == BLUE) {
                        numSliders++;
                    }
                    BOARD[r][c] = fill[c].charAt(0);
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public TiltConfig(char [][] BOARD, int sliders, TiltConfig other){
        this.BOARD = BOARD;
        this.DIM = other.DIM;
        this.numSliders = sliders;
    }

    @Override
    public boolean isSolution() {
        return numSliders == 0;
    }

    @Override
    public Collection<Configuration> getNeighbors() {
        Collection<Configuration> configs = new ArrayList<Configuration>();

        // NORTH
        boolean northValid = true;
        char [][] northBoard = new char[DIM][DIM];
        int northSliders = numSliders;

        while (northValid) {
            for(int r = 0; r < DIM; r++) {
                while (northValid) {
                    Queue<Integer> empties = new LinkedList<>();
                    Queue<Character> sliders = new LinkedList<>();
                    boolean hole = false;

                    for (int c = 0; c < DIM; c++) {
                        northBoard[r][c] = BOARD[r][c];

                        if (BOARD[r][c] == EMPTY) {
                            empties.add(c);

                        } else if (BOARD[r][c] == BLOCKER) {
                            empties = new LinkedList<>();
                            sliders = new LinkedList<>();
                            hole = false;

                        } else if (BOARD[r][c] == BLUE) {
                            sliders.add(BLUE);

                            if (hole) {
                                System.out.println("invalid configuration :(");
                                northValid = false;
                            } else {
                                northBoard[r][empties.remove()] = BLUE;
                                empties.add(c);
                                northBoard[r][c] = EMPTY;
                            }

                        } else if (BOARD[r][c] == GREEN) {
                            sliders.add(GREEN);

                            if (hole) {
                                //EAT GREEN
                                empties.add(c);
                                northBoard[r][c] = EMPTY;
                                northSliders--;
                            } else {
                                northBoard[r][empties.remove()] = GREEN;
                                empties.add(c);
                                northBoard[r][c] = EMPTY;
                            }

                        } else if (BOARD[r][c] == HOLE) {
                            hole = true;
                        }
                    }
                }
            }
        }
        if (northValid) {
            Configuration northConfig = new TiltConfig(northBoard, northSliders, this);
            configs.add(northConfig);
        }

        // EAST
        boolean eastValid = true;
        char [][] eastBoard = new char[DIM][DIM];
        int eastSliders = numSliders;

        while (eastValid) {
            for(int r = 0; r < DIM; r++) {
                while (eastValid) {
                    Queue<Integer> empties = new LinkedList<>();
                    Queue<Character> sliders = new LinkedList<>();
                    boolean hole = false;

                    for (int c = 0; c < DIM; c++) {
                        eastBoard[r][c] = BOARD[r][c];

                        if (BOARD[r][c] == EMPTY) {
                            empties.add(c);

                        } else if (BOARD[r][c] == BLOCKER) {
                            empties = new LinkedList<>();
                            sliders = new LinkedList<>();
                            hole = false;

                        } else if (BOARD[r][c] == BLUE) {
                            sliders.add(BLUE);

                            if (hole) {
                                System.out.println("invalid configuration :(");
                                eastValid = false;
                            } else {
                                eastBoard[r][empties.remove()] = BLUE;
                                empties.add(c);
                                eastBoard[r][c] = EMPTY;
                            }

                        } else if (BOARD[r][c] == GREEN) {
                            sliders.add(GREEN);

                            if (hole) {
                                //EAT GREEN
                                empties.add(c);
                                eastBoard[r][c] = EMPTY;
                                eastSliders--;
                            } else {
                                eastBoard[r][empties.remove()] = GREEN;
                                empties.add(c);
                                eastBoard[r][c] = EMPTY;
                            }

                        } else if (BOARD[r][c] == HOLE) {
                            hole = true;
                        }
                    }
                }
            }
        }
        if (eastValid) {
            Configuration eastConfig = new TiltConfig(eastBoard, eastSliders, this);
            configs.add(eastConfig);
        }

        // SOUTH
        boolean southValid = true;
        char [][] southBoard = new char[DIM][DIM];
        int southSliders = numSliders;

        while (southValid) {
            for(int r = 0; r < DIM; r++) {
                while (southValid) {
                    Queue<Integer> empties = new LinkedList<>();
                    Queue<Character> sliders = new LinkedList<>();
                    boolean hole = false;

                    for (int c = 0; c < DIM; c++) {
                        southBoard[r][c] = BOARD[r][c];

                        if (BOARD[r][c] == EMPTY) {
                            empties.add(c);

                        } else if (BOARD[r][c] == BLOCKER) {
                            empties = new LinkedList<>();
                            sliders = new LinkedList<>();
                            hole = false;

                        } else if (BOARD[r][c] == BLUE) {
                            sliders.add(BLUE);

                            if (hole) {
                                System.out.println("invalid configuration :(");
                                southValid = false;
                            } else {
                                southBoard[r][empties.remove()] = BLUE;
                                empties.add(c);
                                southBoard[r][c] = EMPTY;
                            }

                        } else if (BOARD[r][c] == GREEN) {
                            sliders.add(GREEN);

                            if (hole) {
                                //EAT GREEN
                                empties.add(c);
                                southBoard[r][c] = EMPTY;
                                southSliders--;
                            } else {
                                southBoard[r][empties.remove()] = GREEN;
                                empties.add(c);
                                southBoard[r][c] = EMPTY;
                            }

                        } else if (BOARD[r][c] == HOLE) {
                            hole = true;
                        }
                    }
                }
            }
        }
        if (southValid) {
            Configuration southConfig = new TiltConfig(southBoard, southSliders, this);
            configs.add(southConfig);
        }

        // WEST
        boolean westValid = true;
        char [][] westBoard = new char[DIM][DIM];
        int westSliders = numSliders;

        while (westValid) {
            for(int r = 0; r < DIM; r++) {
                while (westValid) {
                    Queue<Integer> empties = new LinkedList<>();
                    Queue<Character> sliders = new LinkedList<>();
                    boolean hole = false;

                    for (int c = 0; c < DIM; c++) {
                        westBoard[r][c] = BOARD[r][c];

                        if (BOARD[r][c] == EMPTY) {
                            empties.add(c);

                        } else if (BOARD[r][c] == BLOCKER) {
                            empties = new LinkedList<>();
                            sliders = new LinkedList<>();
                            hole = false;

                        } else if (BOARD[r][c] == BLUE) {
                            sliders.add(BLUE);

                            if (hole) {
                                System.out.println("invalid configuration :(");
                                westValid = false;
                            } else {
                                westBoard[r][empties.remove()] = BLUE;
                                empties.add(c);
                                westBoard[r][c] = EMPTY;
                            }

                        } else if (BOARD[r][c] == GREEN) {
                            sliders.add(GREEN);

                            if (hole) {
                                //EAT GREEN
                                empties.add(c);
                                westBoard[r][c] = EMPTY;
                                westSliders--;
                            } else {
                                westBoard[r][empties.remove()] = GREEN;
                                empties.add(c);
                                westBoard[r][c] = EMPTY;
                            }

                        } else if (BOARD[r][c] == HOLE) {
                            hole = true;
                        }
                    }
                }
            }
        }
        if (westValid) {
            Configuration westConfig = new TiltConfig(westBoard, westSliders, this);
            configs.add(westConfig);
        }

        return configs;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof TiltConfig) {
            if(((TiltConfig) other).DIM != this.DIM) {
                return false;
            }
            else {
                for(int r = 0; r < DIM; r++) {
                    for(int c = 0; c < DIM; c++) {
                        if(BOARD[r][c] != ((TiltConfig) other).BOARD[r][c]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        String to = "";
        for(int r = 0; r < DIM; r++) {
            for(int c = 0; c < DIM; c++) {
                if(c == DIM - 1) {
                    to += BOARD[r][c];
                }
                else {
                    to += BOARD[r][c] + " ";
                }
            }
            to += "\n";
        }

        return to;
    }
}

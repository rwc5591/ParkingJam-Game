package puzzles.strings;

import puzzles.common.solver.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class StringsConfig implements Configuration {
    private static String start;
    private static String finish;
    private String current;
    public static int configCount=1;


    public StringsConfig(String start, String current, String finish) {
        StringsConfig.start = start;
        StringsConfig.finish = finish;
        this.current = current;
    }

    @Override
    public boolean isSolution() {
        return current.equals(finish);
    }

    @Override
    public Collection<Configuration> getNeighbors() {

        //arraylist of neighbors check the neigbor of the config and add list..
        ArrayList< Configuration> neighbors= new ArrayList<>();
       ArrayList<String> alpabet=new ArrayList<>(Arrays.asList("A","B","C", "D", "E", "F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));

        for (int i = 0; i < current.length(); i++) {
            String currLetter =current.substring(i, i+1);
            int index = alpabet.indexOf(currLetter);
            String a;
            String z;
            if(index==0){
                 a = current.substring(0, i) + alpabet.get(25) + current.substring(i+1);
            }
            else {
                a = current.substring(0, i) + alpabet.get(index-1) + current.substring(i+1);
            }

            neighbors.add(new StringsConfig(start, a, finish));
            configCount++;
            if(index==25){
                z= current.substring(0,i) + alpabet.get(0) + current.substring(i+1);
            }
            else {
                z = current.substring(0, i) + alpabet.get(index+1) + current.substring(i+1);
            }
            neighbors.add(new StringsConfig(start, z, finish));

            configCount++;

        }
//        System.out.println(neighbors);

        return neighbors;
    }

    @Override
    public String toString() {
        return current;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringsConfig that)) return false;
        return Objects.equals(current, that.current);
    }

    @Override public int hashCode(){
        return current.hashCode();

    }

}

package puzzles.common.solver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    // TODO
    private LinkedList<Configuration> queue;
    private HashMap<Configuration, Configuration> predecessor;//link neighbors to each other
    public static int uniqConfigCount=0;

    //queue list
    // predecessor map
    public Solver() {
        this.queue = new LinkedList<>();
        this.predecessor = new HashMap<>();
    }

    public LinkedList<Configuration> solve(Configuration config) {
        queue.add(config);
        uniqConfigCount++;
        predecessor.put(config, null);
        while (!queue.isEmpty()) {
            // the next node to process is at the front of the queue
            Configuration current = queue.remove(0);
            if (current.isSolution()) {
                LinkedList<Configuration> path = new LinkedList<>();
                Configuration currConfig = current;
                while (currConfig != null) {
                    path.add(0, currConfig);
                    currConfig = predecessor.get(currConfig);
                }
                return path;

            }

            for (Configuration nbr : current.getNeighbors()) {
                // process unvisited neighbors
                if (!predecessor.containsKey(nbr)) {
                    predecessor.put(nbr, current);
                    queue.add(nbr);
                    uniqConfigCount++;
                }

            }

        }
        return null;
    }

}

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P5C {

    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        Scanner s = new Scanner(new FileReader(filename));
        ArrayList<String> aPorts = new ArrayList<String>();
        GraphN g = new GraphN();
        // dat code, splits line and adds all the elements to a list
        aPorts.addAll(Arrays.asList(s.nextLine().split(" ")));
        for (String p : aPorts) {
            g.addNode(new Node(p));
        }
        int j = 0;
        while (s.hasNextLine()) {

            String currPort = s.next();
            String line = s.nextLine();
            String[] ports = line.split(" ");
            int k = 0;
            for (String p : ports) {

                Integer i = new Integer(p);
                if (i != 0) {
                    g.addEdge(g.getNodes().get(j), g.getNodes().get(k), i);
                }
            }
            j++;
        }

    }

}

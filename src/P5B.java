import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Main class for p5b.
 * @author r42xe_000
 *
 */
public final class P5B {
    /**
     * Default constructor to do nothing.
     */
    private P5B() {
        
    }
    /**
     * Main.
     * @param args
     * Args
     * @throws FileNotFoundException
     * If file isn't found
     */
    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        Scanner s = new Scanner(new FileReader(filename));
        int[][] freqTable;
        ArrayList<String> aPorts = new ArrayList<String>();
        //Add everything to the list of airports
        //(in the order they occur as beginning ports)
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] ports = line.split("->");
            if (!aPorts.contains(ports[0]) && !ports[0].isEmpty()) {
                aPorts.add(ports[0]);
            }
        }
        freqTable = new int[aPorts.size()][aPorts.size()];
        s = new Scanner(new FileReader(filename));
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] ports = line.split("->");
            for (int i = 0; i < ports.length - 1; i++) {
                //Add to the frequency
                freqTable[aPorts.indexOf(ports[i])]
                        [aPorts.indexOf(ports[i + 1])]++;
            }
        }
        s.close();
        PrintWriter w = new PrintWriter("P5Bout.txt");
        w.print("    ");
        for (int i = 0; i < aPorts.size(); i++) {
            w.print(aPorts.get(i) + " ");
        }
        w.println();
        for (int i = 0; i < aPorts.size(); i++) {
            w.print(aPorts.get(i) + " ");
            for (int j = 0; j < aPorts.size(); j++) {
                w.printf("%-4s", freqTable[i][j] + " ");
            }
            w.println();
        }
        w.close();
    }
}

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * Main class for p5a.
 * @author r42xe_000
 *
 */
public final class P5A {
    /**
     * Empty constructor because checkstyle is dumb.
     */
    private P5A() {
        
    }
    /**
     * Main.
     * @param args
     * Args
     * @throws FileNotFoundException
     * If input isnt found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        GraphN graph = new GraphN();
        String filename = args[0];
        int threshold = new Integer(args[1]);
        Scanner s = new Scanner(new FileReader(filename));
        while (s.hasNextLine()) {
            Node fromPort = new Node(s.next());
            Node toPort = new Node(s.next());
            int dist = s.nextInt();
            graph.addNode(fromPort);
            graph.addNode(toPort);
            graph.addEdge(fromPort, toPort, dist);
            graph.addEdge(toPort, fromPort, dist);
        }
        if (threshold == 0) {
            threshold = graph.getNodes().size();
        }
        s.close();
        PrintWriter w = new PrintWriter("P5Aout.txt");
        for (Node n : graph.getNodes()) {

            HashMap<Node, Node> previousMap = 
                    (HashMap<Node, Node>) graph.djikstra(n);
            HashMap<Node, Node> upreviousMap =
                    (HashMap<Node, Node>) graph.breadthFirst(n);
            for (Node k : graph.getNodes()) {
                Stack<Node> trans = trans(previousMap, n, k);
                if (trans.size() > threshold) {
                    trans = trans(upreviousMap, n, k);
                }
                w.print(n.toString());
                while (!trans.isEmpty()) {
                    w.print("->" + trans.pop());
                }
                w.println();
            }
            w.println();

        }

        w.close();
    }
    /**
     * Gives a path.
     * @param map
     * The map of previous nodes
     * @param begin
     * Beginning node
     * @param end
     * End node
     * @return
     * The path
     */
    public static Stack<Node> trans(
            HashMap<Node, Node> map, Node begin, Node end) {
        Node curr = end;
        Stack<Node> ret = new Stack<Node>();

        while (curr != begin) {
            ret.push(curr);
            curr = map.get(curr);
            if (curr == null) {
                return null;
            }
        }
        return ret;
    }

}

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;
/**
 * Main class for P5C.
 * @author Raphael Norman-Tenazas
 *
 */
public final class P5C {
    /**
     * Because checkstyle is dumb.
     */
    private P5C() {
        
    }
    /**
     * Main class.
     * @param args
     * Args
     * @throws FileNotFoundException
     * If file not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        Scanner s = new Scanner(new FileReader(filename));
        ArrayList<String> aPorts = new ArrayList<String>();
        GraphN g = new GraphN();
        // dat code, splits line with at least one space 
        // inbetween and adds all the elements to a list
        aPorts.addAll(Arrays.asList(s.nextLine().split(" {1,}")));
        aPorts.remove(0);
        for (String p : aPorts) {
            g.addNode(new Node(p));
        }
        int j = 0;
        int[] totalFreq = new int[aPorts.size()];
        while (s.hasNextLine()) {
            String line = s.nextLine();
            ArrayList<String> ports = 
                    new ArrayList<String>(Arrays.asList(line.split(" {1,}")));
            
            ports.remove(0);
            int k = 0;
            for (String p : ports) {
                Integer i = new Integer(p);
                if (i != 0) {
                    Edge e = new Edge(g.getNodes().get(j), 
                            g.getNodes().get(k), -i);
                    Edge f = new Edge(g.getNodes().get(k), 
                            g.getNodes().get(j), -i);
                    g.addEdge(e);
                    g.addEdge(f);
                    
                    
                }
                k++;
            }
            j++;
        }
        s.close();
        TreeSet<Edge> maxTree = kruskal(g);
        PrintWriter w = new PrintWriter("P5Ctable.txt");
        w.print("    ");
        for (String port : aPorts) {
            w.print(port + " ");
        }
        w.println();
        int[][] table = new int[aPorts.size()][aPorts.size()];
        for (Edge e : maxTree) {
            System.out.println(e);
            int endInd = aPorts.indexOf(e.getEnd().toString());
            int begInd = aPorts.indexOf(e.getBegin().toString());
            table[begInd][endInd] = 1;
            table[endInd][begInd] = 1;
            totalFreq[begInd] += -e.getWeight();
            totalFreq[endInd] += -e.getWeight();
        }
        for (int i = 0; i < aPorts.size(); i++) {
            w.print(aPorts.get(i) + " ");
            for (int k = 0; k < aPorts.size(); k++) {
                w.printf("%-4s", table[i][k] + " ");
            }
            w.println();
        }   
        w.close();
        for (int i: totalFreq) {
            System.out.println(i);
        }
        pTf(totalFreq, aPorts);
        
    }
    /**
     * To prevent cylcomamtic complexity.
     * @param g
     * graph
     * @return
     * max spanning tree
     */
    private static TreeSet<Edge> kruskal(GraphN g) {
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>(g.getEdges());
        TreeSet<Edge> maxTree = new TreeSet<Edge>();
        HashSet<HashSet<Node>> nodes = new HashSet<HashSet<Node>>();
        for (Node n : g.getNodes()) {
            HashSet<Node> temp = new HashSet<Node>();
            temp.add(n);
            nodes.add(temp);
        }
        //Basically Kruskals
        while (!edges.isEmpty()) {
            Edge e = edges.poll();

            HashSet<Node> endSet = null;
            HashSet<Node> beginSet = null;

            for (HashSet<Node> tsn : nodes) {

                if (tsn.contains(e.getBegin())) {
                    beginSet = tsn;
                }
                if (tsn.contains(e.getEnd())) {
                    endSet = tsn;
                }
            }

            if (beginSet != null && endSet != null 
                    && !beginSet.equals(endSet)) {

                nodes.remove(endSet);

                nodes.remove(beginSet);
                beginSet.addAll(endSet);
                nodes.add(beginSet);
                Edge f = new Edge(e.getEnd(), e.getBegin(), e.getWeight());
                System.out.println(maxTree.contains(e) 
                        + " " + maxTree.contains(f));
                maxTree.add(f);
                System.out.println(maxTree);
                maxTree.add(e);
                System.out.println(maxTree);
                
                
            }
        
        }
        return maxTree;
    }
    /**
     * To get rid of cyclomatic complexity.
     * @param totalFreq
     * total Frequency
     * @param aPorts
     * aports
     * @throws FileNotFoundException 
     * idk
     */
    private static void pTf(int[] totalFreq, ArrayList<String> aPorts) 
            throws FileNotFoundException {
        PrintWriter w = new PrintWriter("P5Cairports.txt");
        int max = 1; 
        int ind = -1;
        while (max != 0) {
            max = 0;
            for (int i = 0; i < totalFreq.length; i++) {
                if (totalFreq[i] > max) {
                    max = totalFreq[i];
                    ind = i;
                } 
            }
            if (max != 0) {
                w.println(aPorts.get(ind) + " - " + totalFreq[ind]);
            }
            totalFreq[ind] = 0;
        }
        w.close();
    }

}

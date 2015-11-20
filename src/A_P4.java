
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class A_P4 {

	public static void main(String[] args) throws IOException{
		TreeMap<String, Integer> codes = new TreeMap<String, Integer>();
	 	GraphM graph;
		ArrayList<String> aPorts = new ArrayList<String>();
		ArrayList<String> dPorts = new ArrayList<String>();
		ArrayList<Integer> miles = new ArrayList<Integer>();
		String filename = args[1];
		Integer threshold = new Integer(args[2]);
		Scanner s = new Scanner( new FileReader(filename));
		while(s.hasNextLine()){
			aPorts.add(s.next());
			dPorts.add(s.next());
			miles.add(s.nextInt());
		}
		int i = 0;
		for(String aPort : aPorts){
			if(!codes.containsKey(aPort)){
				codes.put(aPort, i++);
			}
		}
		int j = 0;
		for(String dPort : dPorts){
			if(!codes.containsKey(dPort)){
				codes.put(dPort, j++);
			}
		}
		graph = new GraphM(codes.size());
		for(int k = 0; k < aPorts.size(); k++){
			graph.addEdge(codes.get(aPorts.get(k)), codes.get(dPorts.get(k)), miles.get(k));
			graph.addEdge(codes.get(dPorts.get(k)), codes.get(aPorts.get(k)), miles.get(k));
		}
	}
}

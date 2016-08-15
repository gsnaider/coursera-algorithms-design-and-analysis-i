package week3.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import week3.graph.Graph;

public class KargerFileReader {

	private static final String FILE_NAME = "kargerMinCut.txt";
	
	
	
	public void readFile(Graph graph){
		
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		String filePath = classLoader.getResource(FILE_NAME).getFile();

		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			
			boolean lineRead = true;
			while (lineRead){
				String line = br.readLine();
				lineRead = (line != null);
				
				if (lineRead){
					String[] values = line.split("\\t");
					int node = Integer.parseInt(values[0]);
					List<Integer> adjacentNodes = new LinkedList<Integer>();
					for(int i = 1; i < values.length; i++){
						adjacentNodes.add(Integer.parseInt(values[i]));
					}
					
					GraphBuilder builder = new GraphBuilder(graph);
					builder.addNode(node, adjacentNodes);
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
}

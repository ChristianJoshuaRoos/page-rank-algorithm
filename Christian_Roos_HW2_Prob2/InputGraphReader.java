import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class InputGraphReader{

	public static Map<String, List<String>> getFile(String fileName) throws FileNotFoundException{
		
		Scanner input = new Scanner(new File(fileName));
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		while (input.hasNext()){
			String line = input.nextLine();
			String[] edge = line.split("\t");
			
			if (map.containsKey(edge[0])){
				map.get(edge[0]).add(edge[1]);
			}else{
				map.put(edge[0], new ArrayList());
				map.get(edge[0]).add(edge[1]);
			}
		}
		
		Scanner input2 = new Scanner(new File(fileName));
		
		while (input2.hasNext()){
			String line = input2.nextLine();
			String[] edge = line.split("\t");
			
			if (!map.containsKey(edge[1])){
				map.put(edge[1], new ArrayList());
			}
		}
		
		int numberOfPages = map.size();
		System.out.println("Number of pages: " + numberOfPages + "\n");
		
		for (int i = 0; i < numberOfPages; i++){
			if (map.get(String.valueOf(i)) == null){
				for (int j = 0; j < numberOfPages; j++){
					map.get(String.valueOf(i)).add("0");
				}
			}
		}
		
		for (String key : map.keySet() ){
			System.out.println(key + ": " + map.get(key).toString());
		}
		System.out.println();
		
		return map;
	}	
}
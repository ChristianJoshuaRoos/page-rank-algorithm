import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class MatrixVectorMultiplier{
	
	public static double[][] buildMatrix(Map<String, List<String>> map){
		
		double[][] matrix = new double[map.size()][map.size()];
		
		for (String key : map.keySet()){
			int numberOfOutlinks = map.get(key).size();
			
			if (numberOfOutlinks == 0){
				for (int j = 0; j < matrix[Integer.valueOf(key)].length; j++){
					matrix[j][Integer.valueOf(key)] = 0.0;
				}
			}else{
				for (int j = 0; j < matrix[Integer.valueOf(key)].length; j++){
					matrix[j][Integer.valueOf(key)] = (map.get(key).contains(String.valueOf(j)) ? 1.0 : 0.0)/numberOfOutlinks;
				}
			}
		}
		return matrix;
	}
}
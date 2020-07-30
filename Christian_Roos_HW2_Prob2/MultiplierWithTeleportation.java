import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class MultiplierWithTeleportation{
	
	//Teleportation parameter.
	private static double beta = 0.85;
	
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
		
		double[][] newMatrix = new double[map.size()][map.size()];
		
		//Muliplies beta by matrix giving new M matrix.
		for (int i = 0; i < matrix.length; i++){
			for (int j = 0; j < matrix[i].length; j++){
				newMatrix[i][j] = matrix[i][j] * beta;
			}
		}
		System.out.println("New Matrix M: \n");
		for (int i = 0; i < newMatrix.length; i++){ 
			for (int j = 0; j < newMatrix[i].length; j++){ 
				System.out.printf("%.2f  ", newMatrix[i][j]); 
			} 
			System.out.println(); 
		}
		
		double[][] teleportMatrix = new double[map.size()][map.size()];
		
		//Creates teleportMatrix where each element is 1/N.
		for (int i = 0; i < teleportMatrix.length; i++){
			for (int j = 0; j < teleportMatrix[i].length; j++){
				teleportMatrix[i][j] = 1.0 / map.size();
			}
		}
		System.out.println("\nTeleport Matrix: \n");
		for (int i = 0; i < teleportMatrix.length; i++){ 
			for (int j = 0; j < teleportMatrix[i].length; j++){ 
				System.out.printf("%.2f  ", teleportMatrix[i][j]); 
			} 
			System.out.println(); 
		}
		
		//Multiplies each element in teleportMatrix by (1 - beta).
		for (int i = 0; i < teleportMatrix.length; i++){
			for (int j = 0; j < teleportMatrix[i].length; j++){
				teleportMatrix[i][j] = teleportMatrix[i][j] * (1 - beta);
			}
		}
		System.out.println("\nTeleport Matrix * (1 - beta): \n");
		for (int i = 0; i < teleportMatrix.length; i++){ 
			for (int j = 0; j < teleportMatrix[i].length; j++){ 
				System.out.printf("%.2f  ", teleportMatrix[i][j]); 
			} 
			System.out.println(); 
		}
		
		double[][] matrixA = new double[map.size()][map.size()];
		
		for (int i = 0; i < matrixA.length; i++){
			for (int j = 0; j < matrixA[i].length; j++){
				matrixA[i][j] = newMatrix[i][j] + teleportMatrix[i][j];
			}
		}
		
		System.out.println("\nMatrixA: \n");
		for (int i = 0; i < matrixA.length; i++){ 
			for (int j = 0; j < matrixA[i].length; j++){ 
				System.out.printf("%.2f  ", matrixA[i][j]); 
			} 
			System.out.println(); 
		}
		
		return matrixA;
	}
}
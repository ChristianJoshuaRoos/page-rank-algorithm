import java.util.Map;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class PowerIterator{
	
	private static String stringRank = null;
	private static ArrayList<String> listOfRanks = new ArrayList<String>();
	private static String nameOfFile = null;
	
	public static void main(String[] args) throws FileNotFoundException{
		
		if (args.length != 1){
			System.out.println("\nError: Enter name of file as command-line argument--");
			System.out.println("e.g., java PowerIteratorWithTeleportation threeVertexGraph.txt");
		}else{
			Map<String, List<String>> map = InputGraphReader.getFile(args[0]);
			nameOfFile = args[0];
			
			double[][] matrix = MatrixVectorMultiplier.buildMatrix(map);
		
			for (int i = 0; i < matrix.length; i++){ 
				for (int j = 0; j < matrix[i].length; j++){ 
					System.out.printf("%.2f  ", matrix[i][j]); 
				} 
				System.out.println(); 
			} 
			
			//Create a vector/array where each element is initalized with 1/(# of pages).
			int numberOfOutlinks = map.size();

			double[] rankVector = new double[numberOfOutlinks];
			System.out.println();
			
			for (int i = 0; i < rankVector.length; i++){
				rankVector[i] = 1.0 / numberOfOutlinks;
				System.out.printf("%.3f%n", rankVector[i]);
			}
			System.out.println();
			
			//Multiply vector by matrix until change in value is less than 5% OR until 100 iterations.
			double[] changeInVectorValues = new double[rankVector.length];
			double[] tempRankVector = new double[rankVector.length];
			boolean thresholdMet = false;
			int counter = 0;
			
			while (thresholdMet == false && counter < 100){
				for (int i = 0; i < tempRankVector.length; i++){
					tempRankVector[i] = 0;
				}
				
				for (int i = 0; i < matrix.length; i++){
					for (int j = 0; j < matrix[i].length; j++){
						tempRankVector[i] += matrix[i][j] * rankVector[j];					
					}
				}
				
				System.out.println("Old Rank Vector     New Rank Vector");
				for (int i = 0; i < rankVector.length; i++){
					System.out.println(rankVector[i] + " " + tempRankVector[i]);
				}
				System.out.println();
				
				for (int i = 0; i < rankVector.length; i++){
					changeInVectorValues[i] = (tempRankVector[i] - rankVector[i])/tempRankVector[i];
				}
				
				counter++;
				System.out.printf("%d%n", counter);
				
				thresholdMet = true;
				
				for (int i = 0; i < changeInVectorValues.length; i++){
					if (Math.abs(changeInVectorValues[i]) > 0.05){
						thresholdMet = false;
						System.out.println("\nThreshold not met!\n");
						break;
					}	
				}
			
				if (thresholdMet == false){
					for (int j = 0; j < rankVector.length; j++){
						rankVector[j] = tempRankVector[j];
					}
				}
			}//End while-loop.
			
			System.out.println();
			for (int i = 0; i < rankVector.length; i++){
				System.out.println("Page rank of " + i + ": " + rankVector[i]);
			}
			
			double sum = 0;
			System.out.println();
			for (int i = 0; i < rankVector.length; i++){
				sum = sum + rankVector[i];
			}
			System.out.println("Sum of page ranks: " + sum);
			String stringSum = "Sum of page ranks: " + sum;
			
			for (int i = 0; i < rankVector.length; i++){
				stringRank = "Page rank of " + i + ": " + rankVector[i] + "\n";
				listOfRanks.add(stringRank);
			}
			listOfRanks.add(stringSum);
		}//End of else block.
		
		try{
			writePageRanksToFile(listOfRanks);
		}catch(IOException e){
			System.out.println("An IOException was caught: " + e.getMessage());
		}
		
	}//End of main.
	
	public static void writePageRanksToFile(ArrayList<String> ranks)throws IOException{
		
		try{
			FileWriter writer = new FileWriter("output.txt");
			writer.write(nameOfFile + System.lineSeparator());
			writer.write("\n");
			for(String rank : ranks) {
				writer.write(rank + System.lineSeparator());
			}
			writer.close();
		}catch(IOException e){
			System.out.println("An IOException was caught: " + e.getMessage());
		}
	}
}//End class.
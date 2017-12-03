import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GA {
	
	public static List<Double> runResults;
	
	public static void main(String[] args) throws IOException {
		
		
		int epoch = 0;
		double localBest = 0;
		double globalMean = 0;
		String iterationResult = "Iteration,Best_Fitness\n";
		runResults = new ArrayList<>();
		int popSize = 10;
		
		Page fittest = null;
		
        while(epoch < 100) {
        	
        	Population myPop = new Population(popSize, true);
        	int generationCount = 0;
        	
        	while(generationCount < 1000) {
            	generationCount++;
            	fittest = myPop.getFittest();
            	localBest = fittest.getFitness();
            	
            	if(epoch == 0)
            		iterationResult += generationCount + "," + localBest + "\n";
            	
            	myPop = Algorithm.evolvePopulation(myPop);
            }
        	     
        	runResults.add(localBest);
        	globalMean += localBest;
        	System.out.println((epoch + 1) + ".Run --> " + " Best Fitness:" + localBest + " - Advert Size:" + fittest.toString() + " - Remain Space:" + fittest.getCapacity() + " - Total Area:" + fittest.sumAdvertCapacity());
        	epoch++;
        }
        
        FitnessCalc.writeResult(iterationResult,"Pc_" + Algorithm.crossoverRate + "_Pm_" + Algorithm.mutationRate + "_size_" + popSize + ".txt");
        Collections.sort(runResults);
        System.out.println("-------------------");
        System.out.println("100 Run Best:" + runResults.get(runResults.size() - 1) + "- 100 Run Mean:" + (globalMean / 100) + " - 100 Run St. Dev:" + calculateStandartDev(globalMean / 100));
        
	}
	
	public static double calculateStandartDev(double mean) {
		
		double result = 0.0;
		for (Double value : runResults) {
			result += Math.pow((value - mean), 2); 
		}
		return Math.sqrt(result / (runResults.size() - 1)); 
	}
}

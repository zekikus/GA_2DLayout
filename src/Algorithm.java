
import java.util.ArrayList;

public class Algorithm {

	/* GA Parameters */
	static final double crossoverRate = 0.8;
	static final double mutationRate = 0.1;
	private static final int tournamentSize = 5;
	private static final boolean elitism = true;
	static final double upperBoundary = 50;
	static final double lowerBoundary = 10;

	// Evolve a population
	public static Population evolvePopulation(Population popu) {
		Population newPopulation = new Population(popu.size(), false);

		// Keep our best individual
		if (elitism) {
			newPopulation.savePage(0, popu.getFittest());
		}

		// Crossover population
		int elitismOffset = 0;
		if (elitism) {
			elitismOffset = 1;
		}

		for (int i = elitismOffset; i < popu.size(); i++) {
			Page parent1 = tournamentSelection(popu);
			Page parent2 = tournamentSelection(popu);
			Page newChild = crossover(parent1.getAdverts(), parent2.getAdverts());
			newChild.setFitness(0);
			newPopulation.savePage(i, newChild);
		}
		
		// Mutate population
		for (int i = elitismOffset; i < newPopulation.size(); i++) {
			mutate(newPopulation.getPage(i));
			newPopulation.getPage(i).updateCapacity();
		}
		
		return newPopulation;
	}

	// Crossover individuals
	private static Page crossover(ArrayList<Advert> list1, ArrayList<Advert> list2) {
		Page newSolution = new Page();
		int minSize = list1.size();
		
		if(list2.size() < minSize)
			minSize = list2.size();
		
		for (int i = 0; i < minSize; i++) {
			if (Math.random() >= crossoverRate) {
				newSolution.addAdvert(list1.get(i));
			} else {
				newSolution.addAdvert(list2.get(i));
			}
		}
		return newSolution;
	}

	// Mutate an individual
	private static void mutate(Page page) {
		// Loop through genes
		for (int i = 0; i < page.advertSize(); i++) {
			if (Math.random() <= mutationRate) {
				Advert advert = page.getAdverts().get(i);
				double width = advert.getGene(0);
				double height = advert.getGene(1);
				// Create random gene
				double newWidth = FitnessCalc.diffMirror(width + Advert.getRandomGene());
				double newHeight = FitnessCalc.diffMirror(height + Advert.getRandomGene());
				advert.setGene(0, newWidth);
				advert.setGene(1, newHeight);
				page.setFitness(0);
				page.setAdvert(i, advert);
			}
		}
	}

	// Select individuals for crossover
	private static Page tournamentSelection(Population popu) {
		// Create a tournament population
		Population tournament = new Population(tournamentSize, false);

		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * popu.size());
			tournament.savePage(i, popu.getPage(randomId));
		}

		// Get the fittest
		Page fittest = tournament.getFittest();
		return fittest;
	}
}

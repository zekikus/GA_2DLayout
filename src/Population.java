
public class Population {
	
	Page[] pages;
	
	// Create a population
	public Population (int populationSize, boolean initialise) {
		pages = new Page[populationSize];
		
		// Initialise population
		if (initialise) {
			for (int i = 0; i < pages.length; i++) {
				Page newPage = new Page();
				newPage.fillPage();
				savePage(i, newPage);
			}
		}
		
	}
	
	/* Getters and Setters */
	
	public Page getPage(int index) {
		return pages[index];
	}
	
	/* Public Functions */
	public void savePage(int index,Page advert) {
		pages[index] = advert;
	}
	
	public int size() {
		return pages.length;
	}
	
	public Page getFittest() {
		Page fittest = pages[0];
		
		for (int i = 0; i < pages.length; i++) {
			if(getPage(i).getFitness() > fittest.getFitness()) {
				fittest = getPage(i);
			}
		}
		return fittest;
	}
}

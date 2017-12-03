
public class Advert {
	
	private double area = 1;
	private double cost;
	private int dimension;
	private double[] genes = new double[2];

	public Advert() {
		generateAdvert();
	}
	
	// Create a random advert
	public void generateAdvert() {
		for (int i = 0; i < genes.length; i++) {
			double gene = getRandomGene();
			area = area * gene;
			genes[i] = gene;
		}
		cost = area * 0.5;
	}
	
	public int size() {
		return genes.length;
	}
	
	public static double getRandomGene() {
		return 10 + (Math.random() * 50);
	}
		
	/* Getters and Setters */
	
	public double getGene(int index) {
		return genes[index];
	}
	
	public void setGene(int index, double value) {
		genes[index] = value;
	}
	
	public void setGenes(double[] genes) {
		this.genes = genes;
	}
	
	public double[] getGenes() {
		return genes;
	}
	
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return genes[0] + " - " + genes[1];
	}
	
}

import java.util.ArrayList;

public class Page {
	
	private double width;
	private double height;
	private double area;
	private double capacity;
	private double fitness;
	private ArrayList<Advert> adverts;
	
	public Page() {
		fitness = 0;
		this.width = 100;
		this.height = 80;
		area = this.width * this.height;
		capacity = area;
		adverts = new ArrayList<>();
	}
	
	public void fillPage() {
		Advert newAdvert = new Advert();
		while (capacity - newAdvert.getArea() >= 0) {
			addAdvert(newAdvert);
			capacity -= newAdvert.getArea();
			newAdvert = new Advert();
		}
		fitness = 0;
	}
	
	public void setAdvert(int index, Advert advert) {
		adverts.set(index, advert);
	}
	
	/* Getter and Setters */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	public int advertSize() {
		return adverts.size();
	}
	public ArrayList<Advert> getAdverts() {
		return adverts;
	}
	public double getFitness() {
		if(fitness == 0) {
			fitness = FitnessCalc.getFitness(this);
		}
		return fitness;
	}
	public void addAdvert(Advert advert) {
		adverts.add(advert);
	}
	public void updateCapacity() {
		for (int i = 0; i < advertSize(); i++) {
			capacity -= adverts.get(i).getArea();
		}
	}
	public double sumAdvertCapacity() {
		double sum = 0;
		for (int i = 0; i < advertSize(); i++) {
			sum += adverts.get(i).getArea();
		}
		return sum;
	}
	
	@Override
	public String toString() {
		return "" + advertSize();
	}
	
	
}

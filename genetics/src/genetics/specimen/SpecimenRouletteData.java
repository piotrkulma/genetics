package genetics.specimen;

public class SpecimenRouletteData {
	private int specimenIndex;
	private double probability;
	private double distribution;
	
	public SpecimenRouletteData(int index) {
		this.specimenIndex = index;
		this.probability = 0;
		this.distribution = 0;
	}
	
	public int getSpecimenIndex() {
		return specimenIndex;
	}
	public void setSpecimenIndex(int specimenIndex) {
		this.specimenIndex = specimenIndex;
	}
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	public double getDistribution() {
		return distribution;
	}
	public void setDistribution(double distribution) {
		this.distribution = distribution;
	}
}

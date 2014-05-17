package genetics.specimen;

public class Specimen {
	private Double evaluation;
	private Double realRepresentation;
	private String binaryRepresentation;

	public Specimen(String chromosome) {
		this.binaryRepresentation = chromosome;
	}

	public Double getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Double evaluation) {
		this.evaluation = evaluation;
	}

	public Double getRealRepresentation() {
		return realRepresentation;
	}

	public void setRealRepresentation(Double realRepresentation) {
		this.realRepresentation = realRepresentation;
	}

	public String getBinaryRepresentation() {
		return binaryRepresentation;
	}

	public void setBinaryRepresentation(String binaryRepresentation) {
		this.binaryRepresentation = binaryRepresentation;
	}
	
	public Specimen clone() {
		Specimen spec = new Specimen(this.binaryRepresentation);
		
		return spec;
	}
}

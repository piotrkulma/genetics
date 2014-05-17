package genetics.utils;

import genetics.specimen.Specimen;
import genetics.specimen.SpecimenRouletteData;
import genetics.specimen.evaluator.Evaluator;

import java.util.List;

public class GeneticUtils {
	public static void initData(int size, List<SpecimenRouletteData> data) {
		for(int i=0; i<size; i++) {
			data.add(new SpecimenRouletteData(i));
		}
	}
	
	public static void drawPopulation(int bitLen, int size, List<Specimen> population) {
		for(int i=0; i<size; i++) {
			population.add(drawSpecimen(bitLen));
		}
	}
	
	public static Specimen drawSpecimen(int bitLen) {
		StringBuilder builder = new StringBuilder("");
		
		for(int i=0; i<bitLen; i++) {
			builder.append((int)(Math.random() * 10) % 2);
		}
		
		
		return new Specimen(builder.toString());
	}
	
	public static void setRealRepresentation(List<Specimen> population, long min, long interval) {
		for(Specimen specimen : population) {			
			specimen.setRealRepresentation(MathUtils.getRealNumber(min, interval, specimen.getBinaryRepresentation()));
		}
	}
	
	public static <T extends Evaluator> void evaluation(List<Specimen> population, T evaluator) {
		for(Specimen specimen : population) {
			specimen.setEvaluation(evaluator.eval(specimen.getRealRepresentation()));
		}
	}
	
	public static void hybridization(Specimen a, Specimen b, int point) {
		String newBinA = "";
		String newBinB = "";
		String binA = a.getBinaryRepresentation();
		String binB = b.getBinaryRepresentation();
		
		newBinA = binA.substring(0, point) + binB.substring(point, binB.length());
		newBinB = binB.substring(0, point) + binA.substring(point, binA.length());
		
		a.setBinaryRepresentation(newBinA);
		b.setBinaryRepresentation(newBinB);
	}
	
	public static void mutation(Specimen specimen, int point) {
		char c = specimen.getBinaryRepresentation().charAt(point);
		
		StringBuilder builder = new StringBuilder("");
		
		String bin = specimen.getBinaryRepresentation();
		builder.append(bin.substring(0, point-1));
		
		if(c == '0') {
			builder.append(1);
		} else {
			builder.append(0);
		}
		
		builder.append(bin.substring(point, bin.length()));
		specimen.setBinaryRepresentation(builder.toString());
	}
}

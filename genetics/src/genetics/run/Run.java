package genetics.run;

import genetics.specimen.Specimen;
import genetics.specimen.SpecimenRouletteData;
import genetics.specimen.evaluator.TestEvaluator;
import genetics.utils.GeneticUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Run {
	static Logger log = Logger.getLogger(Run.class.getName());
	
	public static void main(String[] args) {		
		int min = -1;
		int interval = 3;
		int bitLen = 22;
		int populationSize = 50;
		int generation = 0;
		
		ClassicGeneticAlgorithm cga = new ClassicGeneticAlgorithm(0.25, 0.01);
		
		List<Specimen> population = new ArrayList<Specimen>();
		List<SpecimenRouletteData> data = new ArrayList<SpecimenRouletteData>();
				
		GeneticUtils.initData(populationSize, data);
		GeneticUtils.drawPopulation(bitLen, populationSize, population);
		
		GeneticUtils.setRealRepresentation(population, min, interval);
		GeneticUtils.evaluation(population, new TestEvaluator());
		
		while(generation < 150) {
			population = cga.selection(population, data);
			GeneticUtils.setRealRepresentation(population, min, interval);
			GeneticUtils.evaluation(population, new TestEvaluator());
		
			System.out.println("G:" + generation + " MAX:" +evalMax(population));
			generation++;
		}
	}
	
	public static double evalMax(List<Specimen> population) {
		Double max = null;
		for(Specimen spec : population) {
			if(max == null || spec.getEvaluation() > max) {
				max = spec.getEvaluation();
			}
		}
		
		return max;
	}
}

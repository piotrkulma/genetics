package genetics.run;

import genetics.specimen.Specimen;
import genetics.specimen.SpecimenRouletteData;
import genetics.utils.GeneticUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClassicGeneticAlgorithm {
	private double pc;
	private double pm;
	
	public ClassicGeneticAlgorithm(double pc, double pm) {
		this.pc = pc;
		this.pm = pm;
	}
	
	public List<Specimen> selection(List<Specimen> population, List<SpecimenRouletteData> data) {
		List<Specimen> newPopulation = new ArrayList<Specimen>();
		selectNewPopulation(population, newPopulation, data);
		
		//hybridization
		doHybridization(newPopulation);
		
		//mutation
		doMutation(newPopulation);
		
		return newPopulation;
	}
	
	private void selectNewPopulation(List<Specimen> population, List<Specimen> newPopulation, List<SpecimenRouletteData> data) {
		//F
		double F = 0;
		for(Specimen spec : population) {
			F += spec.getEvaluation();
		}
		
		//probability
		for(int i=0; i<data.size(); i++) {
			data.get(i).setProbability(population.get(i).getEvaluation() / F);
		}
		
		//distribution
		for(int i=0; i<data.size(); i++) {
			double distr = 0;
			for(int j=0; j<=i; j++) {
				distr += data.get(j).getProbability();
			}
			data.get(i).setDistribution(distr);
		}

		//new population
		for (int i = 0; i < population.size(); i++) {
			double r = Math.random();

			if (r < data.get(0).getDistribution()) {
				newPopulation.add(population.get(0).clone());
			} else {
				for (int j = 1; j < population.size(); j++) {
					
					if (data.get(j-1).getDistribution() < r && r <= data.get(j).getDistribution()) {
						newPopulation.add(population.get(j).clone());
						break;
					}
				}
			}
		}
	}
	
	private void doHybridization(List<Specimen> newPopulation) {
		List<Integer> hybridIndxs = new ArrayList<Integer>();
		for(int i=0; i< newPopulation.size(); i++) {	
			int r = (int)(Math.random() * newPopulation.size());
		
			if(r < pc) {
				hybridIndxs.add(i);
			}				
		}
		
		if(hybridIndxs.size() >= 2) {
			if(hybridIndxs.size() % 2 != 0) {
				int temp = (int)(Math.random() * hybridIndxs.size());
				
				hybridIndxs.remove(temp);
			}
			
			Iterator<Integer> iterator = hybridIndxs.iterator();
			while(iterator.hasNext()) {
				int point = (int)(Math.random() * (newPopulation.get(0).getBinaryRepresentation().length() - 2));
				
				Specimen a = newPopulation.get(iterator.next());
				Specimen b = newPopulation.get(iterator.next());
				
				try {
					GeneticUtils.hybridization(a, b, point);
				}catch(Exception e) {
					
					e.printStackTrace();
				}
			}
		}
	}
	
	private void doMutation(List<Specimen> newPopulation) {
		for(int i=0; i< newPopulation.size(); i++) {	
			Specimen spec = newPopulation.get(i);
			
			for(int c=0; c<spec.getBinaryRepresentation().length(); c++) {
				int r = (int)(Math.random() * newPopulation.size()) + 1;
				
				if(r < pm) {
					GeneticUtils.mutation(spec, c);
				}
			}
		}
	}
}

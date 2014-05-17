package genetics.specimen.evaluator;


public class TestEvaluator implements Evaluator{
	@Override
	public double eval(double spec) {
		return spec * Math.sin(10.0 * Math.PI * spec) + 1.0d;
	}
}

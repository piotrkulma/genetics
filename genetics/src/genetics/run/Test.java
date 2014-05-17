package genetics.run;

import genetics.specimen.Specimen;
import genetics.utils.GeneticUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Te {
	public String a = "";
	
	public Te(String a) {
		this.a = a;
	}
}

public class Test {
	public static void main(String[] args) {
		
		Specimen a = new Specimen("111111111");
		Specimen b = new Specimen("1234567891");
		GeneticUtils.mutation(a, 9);
		System.out.print(a.getBinaryRepresentation() + " " + a.getBinaryRepresentation().length());
	}

}

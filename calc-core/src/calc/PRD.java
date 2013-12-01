package calc;

import java.util.ArrayList;

public class PRD extends RangedFunction {

	public PRD(ArrayList<Reference> interval) {
		super(interval);
	}

	public int calculate() {
		int prod = 1;
		for (Reference reference : getInterval()) {
			prod *= reference.calculate();
		}
		return prod;
	}

	
	public String print() {
		return null;
	}
	
}

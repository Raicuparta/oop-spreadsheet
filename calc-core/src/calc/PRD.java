package calc;

public class PRD extends RangedFunction {

	public PRD(Reference[] interval) {
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

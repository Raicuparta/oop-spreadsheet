package calc;

public class PRD extends RangedFunction {

	public PRD(Range interval) {
		super(interval);
	}

	public int calculate() {
		int prod = 1;
		for (Reference reference : getRange().getInterval()) {
			prod *= reference.calculate();
		}
		return prod;
	}
	
	public String getName() {
		return "PRD";
	}
	
}

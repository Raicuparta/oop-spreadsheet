package calc;

public class AVG extends RangedFunction {

	public AVG(Range interval) {
		super(interval);
	}

	public int calculate() {
		int total = 0;
		for (Reference reference : getRange().getInterval()) {
			total += reference.calculate();
		}
		return total/getRange().getInterval().size();
	}
	
	
	public String getName() {
		return "AVG";
	}
	
}

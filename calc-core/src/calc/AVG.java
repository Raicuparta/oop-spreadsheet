package calc;

public class AVG extends RangedFunction {

	public AVG(Reference[] interval) {
		super(interval);
	}

	public int calculate() {
		int total = 0;
		for (Reference reference : getInterval()) {
			total += reference.calculate();
		}
		return total/getInterval().length;
	}
	
	public String print() {
		return null;
	}
	
	public String getName() {
		return "AVG";
	}
	
}

package calc;
import java.util.ArrayList;

public class AVG extends RangedFunction {

	public AVG(ArrayList<Reference> interval) {
		super(interval);
	}

	public int calculate() {
		int total = 0;
		for (Reference reference : getInterval()) {
			total += reference.calculate();
		}
		return total/getInterval().size();
	}
	
	public String print() {
		return null;
	}
	
	public String getName() {
		return "AVG";
	}
	
}

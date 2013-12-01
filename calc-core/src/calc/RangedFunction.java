package calc;
import java.util.ArrayList;

public abstract class RangedFunction extends Function {

	//private ArrayList<Integer> operators;
	
	private Reference[] _interval;
	
/*	public ArrayList<Integer> getOperators() {
		return operators;
	}*/
	
	public Reference[] getInterval() {
		return _interval;
	}

	public RangedFunction(Reference[] interval) {
		
		_interval = interval;
		
	}
	
	public abstract int calculate();
	
	public abstract String print();
	
	public boolean hasValue() {
		return true; //ARRANJAR ISTO
	}
	
}

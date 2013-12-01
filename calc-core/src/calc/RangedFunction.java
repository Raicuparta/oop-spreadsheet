package calc;

import java.io.Serializable;

import java.util.ArrayList;

public abstract class RangedFunction extends Function implements Serializable {

	//private ArrayList<Integer> operators;
	
	private ArrayList<Reference> _interval;
	
/*	public ArrayList<Integer> getOperators() {
		return operators;
	}*/
	
	public ArrayList<Reference> getInterval() {
		return _interval;
	}

	public RangedFunction(ArrayList<Reference> interval) {
		
		_interval = interval;
		
	}
	
	public abstract int calculate();
	
	public abstract String print();
	
	public boolean hasValue() {
		return true; //ARRANJAR ISTO
	}
	
}

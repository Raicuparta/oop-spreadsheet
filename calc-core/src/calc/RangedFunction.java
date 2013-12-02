package calc;

import java.io.Serializable;

public abstract class RangedFunction extends Function implements Serializable {

	//private ArrayList<Integer> operators;
	
	private Range _range;
	
/*	public ArrayList<Integer> getOperators() {
		return operators;
	}*/
	
	public Range getRange() {
		return _range;
	}

	public RangedFunction(Range range) {
		
		_range = range;
		
	}
	
	public abstract String getName();
	
	public abstract int calculate();
	
	public String print() {
		
		String valueString = "#VALUE";
				
		String op1 = super.parseOperators(_range.getFirst());
		String op2 = super.parseOperators(_range.getLast());
		
		
		if (hasValue()){
			valueString = "" + calculate();
		}
				
		return valueString + "=" + getName() + "(" + op1 + ":" + op2 + ")";
	}
	
	public boolean hasValue() {
		return _range.hasValue();
	}
	
}

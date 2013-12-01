package calc;

import java.io.Serializable;
import java.io.Serializable;

public class Literal extends Content implements Serializable {

	private int _value;
	
	public Literal(int value) {
		_value = value;
	}
	
	public int calculate() {
		return _value;
	}
	
	public String print() {
		return "" + _value;
	}
	
	public boolean hasValue() {
		return true;
	}
}

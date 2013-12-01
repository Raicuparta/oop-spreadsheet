package calc;

import java.io.Serializable;

public abstract class BinaryFunction extends Function implements Serializable {

	private Content _operator1;
	private Content _operator2;
		
	public BinaryFunction(Content content1, Content content2) {
		_operator1 = content1;
		_operator2 = content2;
	}
		
	public Content getOperator1() {
		return _operator1;
	}

	public Content getOperator2() {
		return _operator2;
	}
	
	public abstract int calculate();
	
	public abstract String getName();
	
	public String print() {
		
		String valueString = "#VALUE";
				
		String op1 = super.parseOperators(_operator1);
		String op2 = super.parseOperators(_operator2);
		
		if (hasValue()){
			valueString = "" + calculate();
		}
				
		return valueString + "=" + getName() + "(" + op1 + "," + op2 + ")";
	}
	
	
	public boolean hasValue() {
		return (_operator1.hasValue()) && (_operator2.hasValue());
	}

	
}

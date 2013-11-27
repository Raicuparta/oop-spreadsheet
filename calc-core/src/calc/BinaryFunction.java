package calc;

public abstract class BinaryFunction extends Function {

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
	
	public String toString() {
		String[] splitArray1 = _operator1.toString().split("=");
		String[] splitArray2 = _operator2.toString().split("=");
		
		String op1, op2;
		
		if (splitArray1[1] != null) {
			op1 = splitArray1[1];
		} else {
			op1 = splitArray1[0];
		}
		
		if (splitArray2[1] != null) {
			op2 = splitArray2[1];
		} else {
			op2 = splitArray2[0];
		}
		
		
		return calculate() + "=" + getName() + "(" + op1 + "," + op2 + ")";
	}
	
}

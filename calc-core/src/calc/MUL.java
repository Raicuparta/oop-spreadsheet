package calc;

public class MUL extends BinaryFunction {

	public MUL(Content content1, Content content2) {
		super(content1, content2);
	}

	public int calculate() {
		return super.getOperator1().calculate() * super.getOperator2().calculate();
		
	}
	public String getName() {
		return "MUL";
	}	
}

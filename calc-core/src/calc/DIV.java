package calc;

public class DIV extends BinaryFunction {

	public DIV(Content content1, Content content2) {
		super(content1, content2);
	}

	public int calculate() {
		return super.getOperator1().calculate() / super.getOperator2().calculate();
	}
	
	public String getName() {
		return "DIV";
	}	
}

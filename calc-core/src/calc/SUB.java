package calc;

public class SUB extends BinaryFunction {

	public SUB(Content content1, Content content2) {
		super(content1, content2);
	}

	public int calculate() {
		return super.getOperator1().calculate() - super.getOperator2().calculate();
	}
	public String getName() {
		return "SUB";
	}	
}

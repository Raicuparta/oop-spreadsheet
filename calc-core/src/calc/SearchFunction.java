package calc;

public class SearchFunction implements Search {
	
	private String _functionName;
	
	public SearchFunction(String functionName) {
		_functionName = functionName;
	}
		
	public boolean visitLiteral(Literal l) {
		return false;
	}

	public boolean visitReference(Reference r) {
		return false;
	}

	public boolean visitFunction(Function f) {
		return f.getName().equals(_functionName);
	}

}

package calc;

public class SearchValue implements Search {

	private int _value;
	
	public SearchValue(int value) {
		_value = value;
	}
	
	public boolean visitLiteral(Literal l) {
		return l.calculate() == _value;
	}

	public boolean visitReference(Reference r) {
		if(!r.hasValue()) {
			return false;
		}
		return r.calculate() == _value;
	}

	public boolean visitFunction(Function f) {
		if(!f.hasValue()) {
			return false;
		}
		return f.calculate() == _value;
	}

}

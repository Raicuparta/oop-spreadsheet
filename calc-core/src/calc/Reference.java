package calc;

import java.io.Serializable;

public class Reference extends Content implements Serializable, Observer {

	private Cell _cell;
	private boolean _update = true;
	private int _value;
	
	public Reference (Cell cell) {
		_cell = cell;
		cell.registerObserver(this);
	}
	
	public void update() {
		_update = true;
	}
	

	
	public int getValue() {
		return _value;
	}
	
	public int calculate() {
		if (_update) {
			_update = false;
			if (hasValue()) {
				_value = _cell.getContent().calculate();
				return _value;
			} else {
				return 0; //Este valor nunca e usado
			}
		} else {
			return getValue();
		}
		
	}
	
	public void insert(Content content) {
		_cell.setContent(content);
	}
	
	public Cell getCell() {
		return _cell;
	}
	
	public boolean hasValue() {
		if(_cell.getContent() != null) {
			return _cell.getContent().hasValue();
		}
		return false;
	}
	
	public String print() {
		String valueString = "#VALUE";
		

		if (hasValue()) {
			valueString = "" + calculate();
		}

		
		return valueString +  "=" + _cell.getLine() + ";" + _cell.getColumn();
	}

	public boolean accept(Search s) {
		return s.visitReference(this);
	}

}

package calc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Range implements Serializable, Iterable<Reference>{

	//IMPLEMENTAR ITERABLE

	private Sheet _sheet;
	private ArrayList<Reference> _interval;
	private Reference _first;
	private Reference _last;

	public Range(Reference ref1, Reference ref2, Sheet sheet) {
		_sheet = sheet;
		_interval = new ArrayList<Reference>();
		_first = ref1;
		_last = ref2;
		
		int firstLine = ref1.getCell().getLine();
		int firstColumn = ref1.getCell().getColumn();
		int lastLine = ref2.getCell().getLine();
		int lastColumn = ref2.getCell().getColumn();
		
		if((firstLine != lastLine) && (firstColumn == lastColumn)) {
			
			for(int i = 0; (i+firstLine)<= lastLine; i++) {
				_interval.add(new Reference(_sheet.getMatrix().getCell(i+firstLine,firstColumn)));				
			}
		}
		
		else if((firstLine == lastLine) && (firstColumn != lastColumn)) {
			
			for(int i = 0; (i+firstColumn)<= lastColumn; i++) {
				_interval.add(new Reference(_sheet.getMatrix().getCell(firstLine,i+firstColumn)));				
			}
		}
		
		else if((firstLine == lastLine) && (firstColumn == lastColumn)) {
			_interval.add(new Reference(_sheet.getMatrix().getCell(firstLine,firstColumn)));
		}		
	}
	
	public ArrayList<Reference> getInterval() {
		return _interval;
	}
	
	public Reference getFirst() {
		return _first;
	}
		
	public Reference getLast() {
		return _last;
	}
	
	public int getIntervalSize() {
		return _interval.size();
	}
	
	public boolean hasValue() {
		for (Reference reference : _interval) {
			if (!reference.hasValue()) {
				return false;
			}
		}
		return true;
		
	}

	private class RangeIterator implements Iterator<Reference> {
		
	    int _index = 0;
	    
	    public boolean hasNext() {
	    	return _index < _interval.size();
	    }
	    
	    public Reference next() {
	    	return _interval.get(_index++);
	    }
	    
	    public void remove() {
	    	_interval.get(_index).getCell().removeContent();
	    }
	 }
	
	@Override
	public Iterator<Reference> iterator() {
		return new RangeIterator();
	}
	
	
}

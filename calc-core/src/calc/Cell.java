package calc;

import java.io.Serializable;
import java.util.ArrayList;

public class Cell implements Serializable, Subject {

	private int _line;
	private int _column;
	private Content _content;
    private ArrayList<Observer> _observers = new ArrayList<Observer>();


	public Cell(int line, int column) {
		_line = line;
		_column = column;
	}
	
	public int getLine() {
		return _line;
	}

	public void setLine(int line) {
		_line = line;
	}

	public int getColumn() {
		return _column;
	}

	public void setColumn(int column) {
		_column = column;
	}

	public Content getContent() {
		return _content;
	}
	
	public void setContent(Content content) {
		_content = content;
		notifyObservers();
		
	}
	
	public void removeContent() {
		_content = null;
		notifyObservers();
	}
	
	public String print() {
		String contentString = "";
		
		if(_content != null) {
			contentString = _content.print();
		}
		
		return _line + ";" + _column + "|" + contentString;
	}
	
	public boolean sameLine(Cell other) {
		return (getLine() == other.getLine());
	}
	
	public boolean sameColumn(Cell other) {
		return (getLine() != other.getLine());
	}

	public void registerObserver(Observer o) {
		_observers.add(o);
	}
 
    public void notifyObservers() {
      for (int i = 0; i < _observers.size(); i++) {
        Observer observer = _observers.get(i);
        observer.update();
      }
    }
	
}

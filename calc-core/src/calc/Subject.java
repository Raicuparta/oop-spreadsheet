package calc;

public interface Subject {
	
	public void registerObserver(Observer o);
    public void notifyObservers();

}

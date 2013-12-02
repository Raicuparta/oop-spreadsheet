package calc;

public interface Search {

	public abstract boolean visitLiteral(Literal l);
	public abstract boolean visitReference(Reference r);
	public abstract boolean visitFunction(Function f);
	
}

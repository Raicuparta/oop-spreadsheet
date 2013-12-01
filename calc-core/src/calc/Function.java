package calc;

public abstract class Function extends Content {

	public abstract int calculate();
	
	public abstract boolean hasValue();
	
	public abstract String print();
	
	public abstract String getName();
	
	public String parseOperators(Content operator) {
		
		/*
		 * Caso um dos argumentos seja uma referencia, o metodo toString inclui o caracter '=',
		 * que nao faz parte do output esperado para as funcoes, logo tem que ser removido.
		 */		
		String[] splitArray = operator.print().split("=");
		
		String op;
		
		if (splitArray.length == 2) {
			op = splitArray[1];
			
		} else {
			op = splitArray[0];
		}
		
		return op;
	}

}

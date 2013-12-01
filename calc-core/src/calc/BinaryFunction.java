package calc;

import java.io.Serializable;

public abstract class BinaryFunction extends Function implements Serializable {

	private Content _operator1;
	private Content _operator2;
		
	public BinaryFunction(Content content1, Content content2) {
		_operator1 = content1;
		_operator2 = content2;
	}
		
	public Content getOperator1() {
		return _operator1;
	}

	public Content getOperator2() {
		return _operator2;
	}
	
	public abstract int calculate();
	
	public abstract String getName();
	
	public String print() {
		
		String valueString = "#VALUE";
				
		String op1 = parseOperators(_operator1);
		String op2 = parseOperators(_operator2);
		
		if (hasValue()){
			valueString = "" + calculate();
		}
				
		return valueString + "=" + getName() + "(" + op1 + "," + op2 + ")";
	}
	
	
	public boolean hasValue() {
		return (_operator1.hasValue()) && (_operator2.hasValue());
	}
	
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

package calc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


import calc.Content;


public class Factory {

	private Sheet _sheet;

	public Factory() {}

	public Factory(Sheet sheet) {
		_sheet = sheet;
	}

	public void setSheet(Sheet sheet) {
		_sheet = sheet;
	}

	/*
	 * Decifra uma linha lida e decide que tipo de conteudo e'
	 */
	public void readLine(String inputString) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		String[] arraySplit;
		String splitContent;
		String referenceString;
		Content content = null;
		

		arraySplit = inputString.split("[|]", 2);
		referenceString = arraySplit[0];
		Reference reference = readReference(referenceString);
		
		splitContent = arraySplit[1];

		arraySplit = splitContent.split("=", 2);

		if (!arraySplit[0].isEmpty()) { //temos literal

			content = this.readLiteral(arraySplit[0]);
		}

		else if ((arraySplit[0].isEmpty()) && (!arraySplit[1].isEmpty())) { //temos funcao ou referencia

			if (arraySplit[1].endsWith(")")) { //temos funcao

				content = readFunction(arraySplit[1]);
			} else { //temos referencia

				content = readReference(arraySplit[1]);
			}
		}

		reference.insert(content);
	}

	/*
	 * Recebe o numero de linhas ou de colunas (em forma de string) e devolve em forma de inteiro
	 */
	public int readDimension(String inputString) {
		String splitArray[] = inputString.split("=");
		int dimension = Integer.parseInt(splitArray[1]);
		return dimension;
	}

	/*
	 * Recebe um conteudo em forma de string e, conforme o seu formato, decifra se e' um literal, referencia ou conteudo
	 */
	public Content readContent(String inputString) {
		
		Content content = null;
		String[] splitArray = inputString.split("=", 2);

		if (splitArray[0].length() != 0) { //temos literal
			content = this.readLiteral(splitArray[0]);
		}
		
		else if ((splitArray[0].length() == 0) && (splitArray[1].length() != 0)) { //temos funcao ou referencia

			if (splitArray[1].endsWith(")")) { //temos funcao
				
				try {
					content = this.readFunction(splitArray[1]);
				}
				 
				catch (ClassNotFoundException e) {e.printStackTrace();}
				catch (NoSuchMethodException e) {e.printStackTrace();}
				catch (SecurityException e) {e.printStackTrace();}
				catch (InstantiationException e) {e.printStackTrace();}
				catch (IllegalAccessException e) {e.printStackTrace();}
				catch (IllegalArgumentException e) {e.printStackTrace();}
				catch (InvocationTargetException e) {e.printStackTrace();}
				
			} else { //temos referencia
				content = this.readReference(splitArray[1]);
			}
		}
		return content;
	}

	/*
	 * Recebe uma string no formato de "m;n" e cria uma celula nessa referencia e retorna a referencia
	 */
	public Reference readReference(String inputString) {

		int line, column;
		Cell cell;

		Reference reference;

		String[] splitArray = inputString.split(";");

		line = Integer.parseInt(splitArray[0]);
		column = Integer.parseInt(splitArray[1]);

		cell = _sheet.getMatrix().getCell(line, column);

		reference = new Reference(cell);

		return reference;
	}
	
	/*
	 * Recebe uma string no formato de "m" e cria um literal com esse valor m e retorna-o
	 */
	public Literal readLiteral(String inputString) {

		int value = Integer.parseInt(inputString);
		Literal literal = new Literal(value);
		return literal;

	}

	/*
	 * Recebe uma string no formato de "FUNCION(content,content)", decifra qual e' a funcao e o tipo de conteudo
	 * dos seus argumentos, cria a funcao e retorna-a
	 */
	public Function readFunction(String inputString) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Function function;

		String[] splitArray = inputString.split("[(,)]", 4);
	
		Class<?> c = Class.forName("calc."+splitArray[0]);
		Object o;
		
		// Se for uma funcao aplicada a intervalos
		if (inputString.contains(":")) {
			
			Range range = readInterval(splitArray[1]);
			Constructor<?> funcConstructor = c.getConstructor(Range.class);
			o = funcConstructor.newInstance(range);
		
		}
		
		// Se for uma funcao binaria
		else {
			
			Content arg1;
			Content arg2;
			
			if (splitArray[1].contains(";")) { //temos ref no 1o argumento
				arg1 = readReference(splitArray[1]);

			} else {//temos literal no 1o argumento
				arg1 = readLiteral(splitArray[1]);
			}

			if (splitArray[2].contains(";")) { //temos ref no 2o argumento
				arg2 = readReference(splitArray[2]);

			} else {//temos literal no 2o argumento
				arg2 = readLiteral(splitArray[2]);
			}

			Constructor<?> funcConstructor = c.getConstructor(Content.class, Content.class);
			o = funcConstructor.newInstance(arg1, arg2);
		}

		
		
		function = (Function)o;

		return function;

	}
	
	/*
	 * Recebe uma string no formato de "m;n:o;p", decifra e cria uma gama, e retorna-a
	 */
	public Range readInterval(String inputString) {
			
		String[] splitString = inputString.split(":");
		
		Reference firstReference = this.readReference(splitString[0]);
		Reference lastReference = this.readReference(splitString[1]);
		
		Range interval = new Range(firstReference, lastReference, _sheet);
		
		
		return interval;
			
	}
	
}

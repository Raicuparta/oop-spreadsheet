package calc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import calc.Content;


public class Factory  {

	private Sheet _sheet;

	public Factory() {}

	public Factory(Sheet sheet) {
		_sheet = sheet;
	}

	public void setSheet(Sheet sheet) {
		_sheet = sheet;
	}

	public void readLine(String inputString) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		int line, column;
		String[] arraySplit;
		String splitContent;
		Content content = null;

		arraySplit = inputString.split("[;|]", 3);
		line = Integer.parseInt(arraySplit[0]);
		column = Integer.parseInt(arraySplit[1]);
		splitContent = arraySplit[2];

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

		_sheet.insert(content, line, column);
	}

	public int readDimension(String inputString) {
		String splitArray[] = inputString.split("=");
		int dimension = Integer.parseInt(splitArray[1]);
		return dimension;
	}

	public Content readContent(String inputString) {
		
		Content content = null;
		String[] splitArray = inputString.split("=", 2);

		if (splitArray[0] != null) { //temos literal
			content = this.readLiteral(splitArray[0]);
		}
		
		else if ((splitArray[0] == null) && (splitArray[1] != null)) { //temos funcao ou referencia

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

	public Reference readReference(String inputString) {

		int line, column;
		Cell cell;

		Reference reference;

		String[] splitArray = inputString.split(";");

		line = Integer.parseInt(splitArray[0]);
		column = Integer.parseInt(splitArray[1]);

		cell = _sheet.getMatrix().getCell(line, column);
		
		if (cell == null) {
			cell = new Cell(line, column);
			_sheet.getMatrix().setCell(line, column, cell);
		}

		reference = new Reference(cell);


		return reference;
		
		
	}

	public Literal readLiteral(String inputString) {

		int value = Integer.parseInt(inputString);
		Literal literal = new Literal(value);
		return literal;

	}

	public Function readFunction(String inputString) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Function function;

		Content arg1;
		Content arg2;

		String[] splitArray = inputString.split("[(,)]", 4);



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


		Class<?> c = Class.forName("calc."+splitArray[0]);
		Constructor<?> funcConstructor = c.getConstructor(Content.class, Content.class);
		Object o = funcConstructor.newInstance(arg1, arg2);
		function = (Function)o;

		return function;

	}
	
	public Reference[] readInterval(String inputString) {
			
		Reference[] interval = null;
		String[] splitString = inputString.split(":");
		
		Reference firstReference = this.readReference(splitString[0]);
		Reference lastReference = this.readReference(splitString[1]);
		
		int firstLine = firstReference.getCell().getLine();
		int firstColumn = firstReference.getCell().getColumn();
		int lastLine = lastReference.getCell().getLine();
		int lastColumn = lastReference.getCell().getColumn();
		
		if((firstLine != lastLine) && (firstColumn == lastColumn)) {
			
			interval = new Reference[lastLine - firstLine + 1];
			
			for(int i = 0; (i+firstLine)<= lastLine; i++) {
				interval[i] = new Reference(_sheet.getMatrix().getCell(i+firstLine,firstColumn));				
			}
			
		}
		
		else if((firstLine == lastLine) && (firstColumn != lastColumn)) {
			interval = new Reference[lastColumn - firstColumn + 1];
			
			for(int i = 0; (i+firstColumn)<= lastColumn; i++) {
				interval[i] = new Reference(_sheet.getMatrix().getCell(firstLine,i+firstColumn));				
			}
		}
		
		return interval;
			
	}
	
}

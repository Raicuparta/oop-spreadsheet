/**
 * @version $Id: Dialog.java,v 1.2 2011/09/25 14:53:05 david Exp $
 */
package ist.po.ui;

import ist.po.io.CompositePrintStream;
import ist.po.io.RuntimeEOFException;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * User interaction (either through the keyboard or files).
 */
public class Dialog {

	/** Single instance of this class. */
	public static/* final */Dialog IO = new Dialog();

	/**
	 * Messages used during interaction with the user.
	 */
	@SuppressWarnings("nls")
	private static final class Messages {

		/**
		 * Error message for file-not-found errors (input).
		 * 
		 * @param fnfe
		 *            an exception corresponding to a file-not-found problem.
		 * @return error message.
		 */
		static final String inputError(FileNotFoundException fnfe) {
			return "Erro a colocar a entrada de dados: " + fnfe;
		}

		/**
		 * Error message for file-not-found errors (output).
		 * 
		 * @param fnfe
		 *            an exception corresponding to a file-not-found problem.
		 * @return error message.
		 */
		static final String outputError(FileNotFoundException fnfe) {
			return "Erro a colocar a saída de dados: " + fnfe;
		}

		/**
		 * Error message for file-not-found errors (log).
		 * 
		 * @param fnfe
		 *            an exception corresponding to a file-not-found problem.
		 * @return error message.
		 */
		static final String logError(FileNotFoundException fnfe) {
			return "Erro a especificar o ficheiro de log: " + fnfe;
		}

		/**
		 * Error message for IO errors (closing input).
		 * 
		 * @param ioe
		 *            an IO exception.
		 * @return error message.
		 */
		static final String errorClosingInput(IOException ioe) {
			return "Erro a fechar entrada de dados: " + ioe;
		}

		/**
		 * Error message for number format errors.
		 * 
		 * @param nfe
		 *            a <code>NumberFormatException</code>.
		 * @return error message.
		 */
		static final String invalidNumber(NumberFormatException nfe) {
			return "Número inválido: " + nfe;
		}

		/**
		 * Error message for EOF errors.
		 * 
		 * @return error message.
		 */
		static final String endOfInput() {
			return "Fim do fluxo de dados de entrada";
		}

	}

	/** Property name: use multiple channels? */
	private static final String USE_BOTH_CHANNELS = "both"; //$NON-NLS-1$

	/** Property name: input channel. */
	private static final String INPUT_CHANNEL = "in"; //$NON-NLS-1$

	/** Property name: log channel. */
	private static final String LOG_CHANNEL = "log"; //$NON-NLS-1$

	/** Property name: output channel. */
	private static final String OUTPUT_CHANNEL = "out"; //$NON-NLS-1$

	/** Property name: Should input be copied to the output? */
	private static final String WRITE_INPUT = "writeInput"; //$NON-NLS-1$

	/** Positive value for yes-no question. */
	private static final char YESNO_YES = 's';

	/** Negative value for yes-no question. */
	private static final char YESNO_NO = 'n';

	/** Input channel. */
	private BufferedReader _in;

	/** Output channel. */
	private PrintStream _out;

	/** Log channel. */
	private PrintStream _log;

	/** Copy input to output? */
	private boolean _writeInput;

	/**
	 * The IO object will be initialized in accordance with the properties
	 * specified in the command line.
	 */
	private Dialog() {
		String name = System.getProperty(INPUT_CHANNEL);

		if (name != null) {
			try {
				_in = new BufferedReader(new FileReader(name));
			} catch (FileNotFoundException fn) {
				println(Messages.inputError(fn));
				name = null; // uses the default value
			}
		}

		if (name == null)
			_in = new BufferedReader(new InputStreamReader(System.in));

		name = System.getProperty(OUTPUT_CHANNEL);

		if (name != null) {
			try {
				PrintStream pr = new PrintStream(new FileOutputStream(name));
				if (Boolean.getBoolean(USE_BOTH_CHANNELS)) {
					CompositePrintStream out = new CompositePrintStream();
					out.add(pr);
					out.add(System.out);
					_out = out;
				} else
					_out = pr;
			} catch (FileNotFoundException fn) {
				println(Messages.outputError(fn));
				name = null; // uses the default value
			}
		}

		if (name == null)
			_out = System.out;

		name = System.getProperty(LOG_CHANNEL);
		if (name != null) {
			try {
				_log = new PrintStream(new FileOutputStream(name));
			} catch (FileNotFoundException fn) {
				println(Messages.logError(fn));
				name = null; // uses the default value
			}
		}

		if (name == null)
			_log = null;

		_writeInput = Boolean.getBoolean(WRITE_INPUT);
	}

	/**
	 * Close all I/O channels.
	 */
	public void closeDown() {
		if (System.out != _out)
			_out.close();
		try {
			String nome = System.getProperty(INPUT_CHANNEL);
			if (nome != null)
				_in.close();
		} catch (IOException ioe) {
			println(Messages.errorClosingInput(ioe));
		}

		if (_log != null)
			_log.close();
	}

	/**
	 * Read an integer number from the input.
	 * 
	 * @param prompt
	 *            a prompt (may be null)
	 * @return the number read from the input.
	 * @throws IOException
	 *             in case of read errors
	 */
	public final int readInteger(String prompt) throws IOException {
		while (true) {
			try {
				return Integer.parseInt(readString(prompt));
			} catch (NumberFormatException e) {
				println(Messages.invalidNumber(e));
			}
		}
	}

	/**
	 * Read a floating point number from the input.
	 * 
	 * @param prompt
	 *            a prompt (may be null)
	 * @return the number read from the input.
	 * @throws IOException
	 *             in case of read errors
	 */
	public final float readFloat(String prompt) throws IOException {
		while (true) {
			try {
				return Float.parseFloat(readString(prompt));
			} catch (NumberFormatException e) {
				println(Messages.invalidNumber(e));
			}
		}
	}

	/**
	 * Read a boolean from the input. 's' corresponds to true and 'n' to false.
	 * 
	 * @param prompt
	 *            a prompt (may be null)
	 * @return the boolean read from the input.
	 * @throws IOException
	 *             in case of read errors
	 * 
	 */
	public final boolean readBoolean(String prompt) throws IOException {
		while (true) {
			String res = readString(prompt);
			if (res.length() == 1 && (res.charAt(0) == YESNO_YES || res.charAt(0) == YESNO_NO))
				return res.charAt(0) == YESNO_YES;
		}
	}

	/**
	 * Read a string.
	 * 
	 * @param prompt
	 *            a prompt (may be null)
	 * @return the string read from the input.
	 * @throws IOException
	 *             in case of read errors
	 * @throws EOFException
	 *             in case of end of file errors
	 */
	public final String readString(String prompt) throws IOException {
		if (prompt != null)
			_out.print(prompt);
		String str = _in.readLine();
		if (str == null) {
			// infelizmente tem que ser esta excepcao pq ha
			// alunos que a apanham e nao fazem nada
			throw new RuntimeEOFException(Messages.endOfInput());
		}

		if (_log != null)
			_log.println(str);

		if (_writeInput)
			_out.println(str);

		return str;
	}

	/**
	 * Write a string.
	 * 
	 * @param text
	 *            string to write.
	 */
	public final void println(String text) {
		_out.println(text);
	}

}
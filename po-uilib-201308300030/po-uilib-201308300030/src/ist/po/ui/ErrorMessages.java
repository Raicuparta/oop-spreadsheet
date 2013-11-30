/**
 * @version $Id: ErrorMessages.java,v 1.2 2011/09/25 14:53:05 david Exp $
 */
package ist.po.ui;

import ist.po.io.RuntimeEOFException;

import java.io.EOFException;
import java.io.IOException;

/**
 * Messages for internal use. Thus none of the methods is public.
 */
@SuppressWarnings("nls")
final class ErrorMessages {

	/**
	 * Constructor.
	 */
	private ErrorMessages() {
		// intentionally left empty
	}

	/**
	 * Message for presenting errors in commands.
	 * 
	 * @param error
	 *            error message.
	 * @return message text.
	 */
	static final String invalidOperation(String error) {
		return "Operação inválida: " + error;
	}

	/**
	 * Message for presenting EOF errors in commands.
	 * 
	 * @param eof
	 *            an <code>EOFException</code>.
	 * @return message text.
	 */
	static final String errorEOF(EOFException eof) {
		return "Fim de entrada de dados (EOF): " + eof;
	}

	/**
	 * Message for presenting IO errors in commands.
	 * 
	 * @param ioe
	 *            an <code>IOException</code>.
	 * @return message text.
	 */
	static final String errorIO(IOException ioe) {
		return "Problema de I/O: " + ioe;
	}

	/**
	 * Message for presenting number reading errors in commands.
	 * 
	 * @param nfe
	 *            a <code>NumberFormatException</code>.
	 * @return message text.
	 */
	static final String errorInvalidNumber(NumberFormatException nfe) {
		return "Número inválido!";
	}

	/**
	 * Message for presenting EOF errors in commands.
	 * 
	 * @param eof
	 *            a <code>RuntimeEOFException</code>.
	 * @return message text.
	 */
	static final String errorREOF(RuntimeEOFException eof) {
		return "Fim de entrada de dados (R-EOF): " + eof;
	}

}

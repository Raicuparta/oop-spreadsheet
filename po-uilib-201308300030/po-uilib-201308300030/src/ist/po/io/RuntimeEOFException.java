/**
 * @version $Id: RuntimeEOFException.java,v 1.2 2011/09/25 14:53:05 david Exp $
 */
package ist.po.io;

/**
 * Class RuntimeEOFException.
 */
public class RuntimeEOFException extends RuntimeException {

	/** Serial number. */
	static final long serialVersionUID = 200610291655L;

	/**
	 * Constructor.
	 */
	public RuntimeEOFException() {
		// intentionally left empty
	}

	/**
	 * @param msg
	 *            message describing the exception.
	 */
	public RuntimeEOFException(String msg) {
		super(msg);
	}

}
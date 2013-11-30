/** @version $Id: $ */
package ist.po.ui;

/**
 * Predicate that tells whether the Command is valid for the given receiver.
 * 
 * @param <Receiver>
 *            the command receiver
 */
public abstract class ValidityPredicate<Receiver> {

	/** Receiver for the command. */
	protected Receiver _receiver;

	/**
	 * @param receiver
	 */
	public ValidityPredicate(Receiver receiver) {
		_receiver = receiver;
	}

	/**
	 * @return whether the command is valid for the predicate.
	 */
	public abstract boolean isValid();
}

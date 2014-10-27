package cscie97.asn2.squaredesk.provider;


/**
 * The Class AccessException.
 */
public class AccessException extends Exception
{	
	
	/** serialization. */
	private static final long serialVersionUID = -4710568791280793495L;

	/**
	 * Instantiates a new access exception.
	 */
	public AccessException()
	{ 
		super();
	}
	
	/**
	 * Instantiates a new access exception.
	 *
	 * @param message the message
	 */
	public AccessException( String message ) 
	{
		super( message );
	}
}

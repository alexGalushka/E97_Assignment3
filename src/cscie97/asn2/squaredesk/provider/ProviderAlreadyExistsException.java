package cscie97.asn2.squaredesk.provider;


/**
 * The Class ProviderAlreadyExistsException.
 */
public class ProviderAlreadyExistsException extends Exception
{	
	
	/** serialization. */
	private static final long serialVersionUID = 8222616516878201026L;

	/**
	 * Instantiates a new provider already exists exception.
	 */
	public ProviderAlreadyExistsException()
	{ 
		super();
	}
	
	/**
	 * Instantiates a new provider already exists exception.
	 *
	 * @param message the message
	 */
	public ProviderAlreadyExistsException( String message ) 
	{
		super( message );
	}
}

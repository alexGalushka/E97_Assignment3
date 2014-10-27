package cscie97.asn2.squaredesk.provider;


/**
 * The Class ProviderNotFoundException.
 */
public class ProviderNotFoundException extends Exception
{	
	
	/** serialization. */
	private static final long serialVersionUID = 8530344751450677081L;

	/**
	 * Instantiates a new provider not found exception.
	 */
	public ProviderNotFoundException()
	{ 
		super();
	}
	
	/**
	 * Instantiates a new provider not found exception.
	 *
	 * @param message the message
	 */
	public ProviderNotFoundException( String message ) 
	{
		super( message );
	}
}
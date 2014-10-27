package cscie97.asn1.knowledge.engine;

/**
 * ImportException extending Exception class, it is used for all import file associated exceptions
 * @author APGalush
 *
 */
public class ImportException extends Exception
{

	private static final long serialVersionUID = 6583817743178913990L;
	public ImportException()
	{ 
		super();
	}
	public ImportException(String message) 
	{
		super(message);
	}

}

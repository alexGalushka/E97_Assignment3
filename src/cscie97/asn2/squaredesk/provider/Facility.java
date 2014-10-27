package cscie97.asn2.squaredesk.provider;


/**
 * The Class Facility.
 */
public class Facility
{
	
	/** The category. */
	private String category;
	
	/** The type. */
	private String type;
	
	/**
	 * Instantiates a new facility.
	 *
	 * @param category the category
	 * @param type the type
	 */
	public Facility ( String category, String type )
	{
		this.category = category;
		this.category = type;
	}
	
	public Facility ()
	{
		category = "";
		category = "";
	}
	
	/**
	 * mutator method for category attribute.
	 *
	 * @param category the new category
	 */
	public void setCategory ( String category )
	{
		this.category = category;
	}
	
	/**
	 * accessor method for category attribute.
	 *
	 * @return String
	 */
	public String getCategory ()
	{
		return this.category;
	}
	
	/**
	 * mutator method for type attribute.
	 *
	 * @param type the new type
	 */
	public void setType ( String type )
	{
		this.type = type;
	}
	
	/**
	 * accessor method for type attribute.
	 *
	 * @return String
	 */
	public String getType ()
	{
		return this.type;
	}
	
}

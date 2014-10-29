package cscie97.asn2.squaredesk.provider;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The Class OfficeProvider.
 */
public class Provider implements Profile
{
	
	/** The office spaces map. */
	private Map<String, OfficeSpace> officeSpacesMap;
	
	/** The provider ratings map. */
	private Map<String, Rating> ratingsMap;
	
	
	/** The office provider guid. */
	private String guid;
	
	/**
	 * Instantiates a new office provider.
	 */
	public Provider ()
	{
		this.officeSpacesMap = new HashMap<String, OfficeSpace>();
		this.ratingsMap = new HashMap<String, Rating>();
		this.guid = "";
	}
	
	/**
	 * Instantiates a new office provider.
	 * @param officeSpaces the office spaces
	 * @param guid the office provider guid
	 */
	public Provider ( URI picture, ContactInfo contact,
			          Map<String, OfficeSpace> officeSpaces, Account account, String guid )
	{
		this.officeSpacesMap = officeSpaces;
		this.guid = guid;
		this.ratingsMap = new HashMap<String, Rating>();
	}
	
	
	/**
	 * mutator method for guid attribute.
	 *
	 * @param guid the new office provider guid
	 */
	public void setGuid ( String guid )
	{
		this.guid = guid;
	}
	
	/**
	 * accessor method for guid attribute.
	 *
	 * @return String
	 */
	public String getGuid ()
	{
		return this.guid;
	}
	
	
	/**
	 * mutator method for officeSpacesMap attribute.
	 *
	 * @param officeSpacesMap the office spaces map
	 */
	public void setOfficeSpaces ( Map<String, OfficeSpace> officeSpacesMap )
	{
		this.officeSpacesMap = officeSpacesMap;
	}
	
	/**
	 * accessor method for officeSpacesMap attribute.
	 *
	 * @return Map<String, OfficeSpace>
	 */
	public Map<String, OfficeSpace> getOfficeSpaces ()
	{
		return this.officeSpacesMap;
	}

	
	public List<OfficeSpace> getOfficeSpacesList()
	{
		return (List<OfficeSpace>) this.officeSpacesMap.values();
	}
	/**
	 * mutator method for ratingsMap attribute.
	 *
	 * @param ratingsMap the provider ratings map
	 */
	public void setRatingsMap ( Map<String, Rating> ratingsMap )
	{
		this.ratingsMap = ratingsMap;
	}
	
	/**
	 * accessor method for ratingsMap attribute.
	 *
	 * @return Map<String, Rating>
	 */
	public Map<String, Rating> getRatingsMap ()
	{
		return this.ratingsMap;
	}
	
	/**
	 * getter method for all ratings per office provider.
	 *
	 * @return List<Rating>
	 */
	public List<Rating> getAllRatings()
	{
		Collection<Rating> tempSet;
		tempSet = ratingsMap.values();
		List<Rating> officeProviderRatingsList = new ArrayList<Rating> ( tempSet );
		return officeProviderRatingsList;
	}
	

}

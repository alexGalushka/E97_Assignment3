package cscie97.asn3.squaredesk.renter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cscie97.asn2.squaredesk.provider.OfficeSpace;
import cscie97.asn2.squaredesk.provider.Profile;
import cscie97.asn2.squaredesk.provider.Rating;

public class Renter implements Profile
{
	public enum Gender {MALE, FEMALE};
	
	private Criteria criteria;
	private Gender gender;
	private String guid;
	
	/** The renter ratings map. */
	private Map<String, Rating> ratingsMap;
	
	public Renter()
	{
		criteria = new Criteria();
		gender = Gender.MALE;
		this.ratingsMap = new HashMap<String, Rating>();
		guid = "";
	}


	/**
	 * accessor method
	 * @return the guid
	 */
	public String getGuid()
	{
		return guid;
	}


	/**
	 * mutator methos
	 * @param guid the guid to set
	 */
	public void setGuid(String guid)
	{
		this.guid = guid;
	}


	/**
	 * @return the criteria
	 */
	public Criteria getCriteria()
	{
		return criteria;
	}


	/**
	 * @return the ratingsMap
	 */
	public Map<String, Rating> getRatingsMap()
	{
		return ratingsMap;
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
	
	
	
	/**
	 * @param ratingsMap the ratingsMap to set
	 */
	public void setRatingsMap(Map<String, Rating> ratingsMap)
	{
		this.ratingsMap = ratingsMap;
	}


	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(Criteria criteria)
	{
		this.criteria = criteria;
	}


	/**
	 * @return the gender
	 */
	public Gender getGender()
	{
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}


	public void setOfficeSpaces(Map<String, OfficeSpace> officeSpacesMap)
	{
		// deferred to next release
		
	}


	public Map<String, OfficeSpace> getOfficeSpaces() 
	{
		// deferred to next release
		return null;
	}


	public List<OfficeSpace> getOfficeSpacesList() 
	{
		// deferred to next release
		return null;
	}


	public List<String> getOfficeSpacesIds()
    {
		// deferred to next release
		return null;
	}


	public void setOfficeSpacesIds(List<String> officeSpacesIds)
	{
		// deferred to next release
		
	}


	public void addOfficeSpacesIdToList(String officeSpacesId)
	{
		// deferred to next release
		
	}
	
	
}

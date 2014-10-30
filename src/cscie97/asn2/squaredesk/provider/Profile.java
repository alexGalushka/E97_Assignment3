package cscie97.asn2.squaredesk.provider;

import java.util.List;
import java.util.Map;

public interface Profile
{
	
	/**
	 * mutator method for officeProviderGuid attribute.
	 *
	 * @param officeProviderGuid the new office provider guid
	 */
	public void setGuid ( String guid );
	
	/**
	 * accessor method for officeProviderGuid attribute.
	 *
	 * @return String
	 */
	public String getGuid ();
	
	
	public void setOfficeSpaces ( Map<String, OfficeSpace> officeSpacesMap );
	
	/**
	 * accessor method for officeSpacesMap attribute.
	 *
	 * @return Map<String, OfficeSpace>
	 */
	public Map<String, OfficeSpace> getOfficeSpaces ();
	
	public List<OfficeSpace> getOfficeSpacesList();

	/**
	 * mutator method for providerRatingsMap attribute.
	 *
	 * @param providerRatingsMap the provider ratings map
	 */
	public void setRatingsMap ( Map<String, Rating> ratingsMap );
	
	/**
	 * accessor method for providerRatingsMap attribute.
	 *
	 * @return Map<String, Rating>
	 */
	public Map<String, Rating> getRatingsMap ();
	
	/**
	 * getter method for all ratings per office provider.
	 *
	 * @return List<Rating>
	 */
	public List<Rating> getAllRatings();
	
	/**
	 * @return the officeSpacesIds
	 */
	public List<String> getOfficeSpacesIds();

	/**
	 * @param officeSpacesIds the officeSpacesIds to set
	 */
	public void setOfficeSpacesIds(List<String> officeSpacesIds);
	
	/**
	 * add officeSpace Id to officeSpacesIds
	 * @param String: officeSpacesId
	 */
	public void addOfficeSpacesIdToList( String officeSpacesId );
}

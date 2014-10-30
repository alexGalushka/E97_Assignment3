package cscie97.asn2.squaredesk.provider;

import java.util.List;




/**
 * The Class OfficeProviderService.
 */
public interface ProviderService
{
	

	/**
	 * Creates a new provider: add provider to the providerMap;
	 * check if it exists already and throws ProviderAlreadyExistException if it does.
	 *
	 * @param authToken the auth token
	 * @param provider the provider
	 * @throws ProviderAlreadyExistsException the provider already exists exception
	 * @throws ProviderNotFoundException 
	 */
	public void createProvider ( String authToken, User provider ) throws ProviderAlreadyExistsException, ProviderNotFoundException;
	
	/**
	 * Returns provider per passed in providerId,
	 * if there is no match – throws ProviderNotFoundException .
	 *
	 * @param authToken the auth token
	 * @param providerId the provider id
	 * @return the provider
	 * @throws ProviderNotFoundException the provider not found exception
	 */
	public User getProvider( String authToken, String providerId ) throws ProviderNotFoundException;
	
	/**
	 * Returns whole list of providers.
	 *
	 * @param authToken the auth token
	 * @return List<OfficeProvider>
	 */
	public List<User> getProviderList ( String authToken );
	
	/**
	 * Updates the provider, new Provider instance has to be passed in.
	 * If providerId not found, throws ProviderNotFoundException.
	 *
	 * @param authToken the auth token
	 * @param provider the provider
	 * @throws ProviderNotFoundException the provider not found exception
	 */
     public void updateProvider ( String authToken, User provider ) throws ProviderNotFoundException;
	
	/**
	 * Deleted the provider.
	 * If providerId not found, throws ProviderNotFoundException.
	 *
	 * @param authToken the auth token
	 * @param providerId the provider id
	 * @throws ProviderNotFoundException the provider not found exception
	 */
	public void deleteProvider ( String authToken, String providerId ) throws ProviderNotFoundException;
	
	/**
	 * Rate the provider. Rating is an integer from 0 to 5. The rating value is added to officeProviderRatingsMap.
	 * if it is found throw RatingAlreadyExistsException. ProviderId is checked as well if it's not found
	 *  - ProviderNotFoundException is thrown 
	 *
	 * @param authToken the auth token
	 * @param providerId the provider id
	 * @param renterId the renter id
	 * @param rating the rating
	 * @throws RatingAlreadyExistsException the rating already exists exception
	 * @throws ProviderNotFoundException the provider not found exception
	 */
	public void rateProvider ( String authToken, String providerId,
			                   String renterId , Rating rating ) throws RatingAlreadyExistsException, ProviderNotFoundException;
	
	/**
	 * The Rating correspondent to renterId is to be removed from officeProviderRatingMap within the officeSpaceMap,
	 * if office space id is not found - ProviderNotFoundException is thrown;
	 * if renterId is not found - RatingNotFoundExcepion is thrown.
	 *
	 * @param authToken the auth token
	 * @param providerId the provider id
	 * @param renterId the renter id
	 * @throws RatingNotFoundExcepion the rating not found excepion
	 * @throws ProviderNotFoundException the provider not found exception
	 */
	public void removeProviderRating ( String authToken, String providerId,
			                           String renterId) throws RatingNotFoundExcepion, ProviderNotFoundException;
	
	/**
	 * Gets the rating list.
	 *
	 * @param authToken the auth token
	 * @param providerId the provider id
	 * @return the rating list
	 * @throws OfficeSpaceNotFoundException the office space not found exception
	 * @throws ProviderNotFoundException 
	 */
	public List<Rating> getRatingList ( String authToken, String providerId ) throws OfficeSpaceNotFoundException, ProviderNotFoundException;
	
	/**
	 * Creates a new OfficeSpace: add office space to officeSpaceMap; check if it exists already
	 * and throws OfficeSpaceAlreadyExistException if it is. If authentication fails - throw AccessException.
	 * Rating Field is initialized here.
	 * Note: officeSpaceId has to be generated first! Check for officeSpaceIds and providerId in the id buckets,
	 * if this check fails throw the 
	 *
	 * @param authToken the auth token
	 * @param officeSpace the office space
	 * @param providerId the guid
	 * @throws OfficeSpaceAlreadyExistException the office space already exist exception
	 * @throws AccessException the access exception
	 */
	public void createOfficeSpace ( String authToken, OfficeSpace officeSpace,
			                        String providerId ) throws OfficeSpaceAlreadyExistException, AccessException;
	
	/**
	 * accessor method for officeSpaceMap attribute
	 * if the guid not found in the map, it throws OfficeSpaceNotFoundException exception.
	 *
	 * @param authToken the auth token
	 * @param guid the guid
	 * @return OfficeSpace
	 * @throws OfficeSpaceNotFoundException the office space not found exception
	 */
	public OfficeSpace getOfficeSpace ( String authToken, String guid ) throws OfficeSpaceNotFoundException;
	
	/**
	 * Gets the office space list.
	 *
	 * @return all values (office spaces) from officeSpaceMap
	 */
	public List<OfficeSpace> getOfficeSpaceList ();
	
	/**
	 * Gets the office space guid list.
	 *
	 * @return List<String>
	 */
	public List<String> getOfficeSpaceGuidList ();
	
	/**
	 * updates particular office space in the office space map with a new office space based on guid passed in
	 * if the guid not found in the map, it throws OfficeSpaceNotFoundException exception.
	 *
	 * @param authToken the auth token
	 * @param providerId the guid
	 * @param updatedOffice the updated office
	 * @throws OfficeSpaceNotFoundException the office space not found exception
	 */
	public void updateOfficeSpace ( String authToken, String providerId,
			                        OfficeSpace updatedOffice) throws OfficeSpaceNotFoundException;
	
	/**
	 * removed particular office space from the office space map based on guid passed in
	 * if the guid not found in the map, it throws OfficeSpaceNotFoundException exception.
	 *
	 * @param authToken the auth token
	 * @param guid the guid
	 * @param officeSpaceId the office space id
	 * @param updatedOffice the updated office
	 * @throws OfficeSpaceNotFoundException the office space not found exception
	 */
	public void removeOfficeSpace ( String authToken, String providerId,
			                        String officeSpaceId ) throws OfficeSpaceNotFoundException;	
	 
	/**
	 * The new Rating is added to officeSpaceRatingMap within the officeSpaceMap,
	 * if the office space id is not found - OfficeSpaceNotFoundException is thrown;
	 * if the rater already provided his/her rating - RatingAlreadyExistsException is thrown.
	 *
	 * @param authToken the auth token
	 * @param renterId the renter id
	 * @param officeSpaceId the office space id
	 * @param rating the rating
	 * @throws OfficeSpaceNotFoundException the office space not found exception
	 * @throws RatingAlreadyExistsException the rating already exists exception
	 */
	public void rateOfficeSpace ( String authToken, String renterId,
			                      String officeSpaceId, Rating rating ) throws OfficeSpaceNotFoundException, RatingAlreadyExistsException;
	
	/**
	 * The Rating correspondent to renterId is to be removed from officeSpaceRatingMap within the officeSpaceMap,
	 * if office space id is not found - OfficeSpaceNotFoundException is thrown;
	 * if renterId is not found - RatingNotFoundExcepion is thrown.
	 *
	 * @param authToken the auth token
	 * @param renterId the renter id
	 * @param officeSpaceId the office space id
	 * @throws OfficeSpaceNotFoundException the office space not found exception
	 * @throws RatingNotFoundExcepion the rating not found excepion
	 */
	public void removeOfficeSpaceRating ( String authToken, String renterId,
                                          String officeSpaceId ) throws OfficeSpaceNotFoundException, RatingNotFoundExcepion;
	
	
	/**
	 * Returns the list all Ratings correspondent to office space the officeSpaceMap,
	 * if the office space id is not found - OfficeSpaceNotFoundException is thrown.
	 *
	 * @param authToken the auth token
	 * @param officeSpaceId the office space id
	 * @return List<Rating>
	 * @throws OfficeSpaceNotFoundException the office space not found exception
	 */
	public List<Rating> getOfficeSpaceRatingList  ( String authToken, String officeSpaceId ) throws OfficeSpaceNotFoundException;
}

package cscie97.asn2.squaredesk.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ProviderServiceImpl implements ProviderService
{

	/** The office provider map. */
	private Map<String, User> providerMap;
	
	/** The office space map. */
	private Map<String, OfficeSpace> officeSpaceMap;
	
	private Map<String, List<OfficeSpace>> officeSpaceMapByProvider;
	private static ProviderServiceImpl _obj;
	
    private ProviderServiceImpl ()
    {
		providerMap = new HashMap<String, User>();
		officeSpaceMap = new HashMap<String, OfficeSpace>();
		officeSpaceMapByProvider = new HashMap<String, List<OfficeSpace>>();
    }
     
    
    /**
     * A special static method to access the single KnowledgeGraph instance
     * @return _obj - type: KnowledgeGraph
     */
    public static ProviderServiceImpl getInstance() 
    {
    	//Checking if the instance is null, then it will create new one and return it
        if (_obj == null)  
        //otherwise it will return previous one.
        {
            _obj = new ProviderServiceImpl();
        }
        return _obj;
    }
    
    
	
	/**
	 * Creates a new provider: add provider to the providerMap;
	 * check if it exists already and throws ProviderAlreadyExistException if it does.
	 *
	 * @param authToken the auth token
	 * @param provider the provider
	 * @throws ProviderAlreadyExistsException the provider already exists exception
	 * @throws ProviderNotFoundException 
	 */
	public void createProvider ( String authToken, User provider) throws ProviderAlreadyExistsException, ProviderNotFoundException
	{
		String providerId = provider.getProfile("provider").getGuid();
		if ( !this.providerMap.containsKey( providerId ) )
		{
			this.providerMap.put ( providerId, provider );
		}
		else
		{
			throw new ProviderAlreadyExistsException();
		}
	}
	
	/**
	 * Returns provider per passed in providerId,
	 * if there is no match – throws ProviderNotFoundException .
	 *
	 * @param authToken the auth token
	 * @param providerId the provider id
	 * @return the provider
	 * @throws ProviderNotFoundException the provider not found exception
	 */
	public User getProvider( String authToken, String providerId ) throws ProviderNotFoundException
	{
		if ( this.providerMap.containsKey( providerId ) )
		{
			return providerMap.get( providerId );
		}
		else
		{
			throw new ProviderNotFoundException();
		}
		
	}
	
	/**
	 * Returns whole list of providers.
	 *
	 * @param authToken the auth token
	 * @return List<OfficeProvider>
	 */
	public List<User> getProviderList ( String authToken )
	{
		Collection<User> tempSet;
		tempSet = providerMap.values();
		List<User> officeProviderGuidList = new ArrayList<User> ( tempSet );
		return officeProviderGuidList;
	}
	
	/**
	 * Updates the provider, new Provider instance has to be passed in.
	 * If providerId not found, throws ProviderNotFoundException.
	 *
	 * @param authToken the auth token
	 * @param providerId the provider id
	 * @param provider the provider
	 * @throws ProviderNotFoundException the provider not found exception
	 */
	public void updateProvider ( String authToken, User provider ) throws ProviderNotFoundException
	{
		String providerId = provider.getGuid();
		if ( this.providerMap.containsKey( providerId ) )
		{
			providerMap.put( providerId, provider );
		}
		else
		{
			throw new ProviderNotFoundException();
		}
	}
	
	/**
	 * Deleted the provider.
	 * If providerId not found, throws ProviderNotFoundException.
	 *
	 * @param authToken the auth token
	 * @param providerId the provider id
	 * @throws ProviderNotFoundException the provider not found exception
	 */
	public void deleteProvider ( String authToken, String providerId ) throws ProviderNotFoundException
	{
		if ( this.providerMap.containsKey( providerId ) )
		{
			providerMap.remove( providerId );
		}
		else
		{
			throw new ProviderNotFoundException();
		}
	}
	
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
			                   String renterId , Rating rating ) throws RatingAlreadyExistsException, ProviderNotFoundException 
	{
		if ( providerMap.containsKey( providerId ) )
		{
			
			User tempUser = providerMap.get( providerId );
			Profile tempProvider = tempUser.getProfile("provider");
			Map<String, Rating>  tempProviderRatingMap = tempProvider.getRatingsMap();
			if ( !tempProviderRatingMap.containsKey( renterId ) )
			{
				tempProviderRatingMap.put( renterId, rating );
				tempProvider.setRatingsMap ( tempProviderRatingMap );
				tempUser.updateProfile("provider", tempProvider);
				providerMap.put( providerId, tempUser );
			}
			else
			{
				throw new RatingAlreadyExistsException();
			}

		}
		else
		{
			throw new ProviderNotFoundException();
		}
	}
	
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
			                           String renterId) throws RatingNotFoundExcepion, ProviderNotFoundException
	{
		if ( providerMap.containsKey( providerId ) )
		{
            User tempUser = providerMap.get( providerId );
            Profile tempProvider = tempUser.getProfile("provider");
            Map<String, Rating> tempProviderRatingMap = tempProvider.getRatingsMap();
			if ( tempProviderRatingMap.containsKey( renterId ) )
			{
				tempProviderRatingMap.remove( renterId );
			}
			else
			{
				throw new RatingNotFoundExcepion();
			}
			tempProvider.setRatingsMap ( tempProviderRatingMap );
			tempUser.updateProfile("provider", tempProvider);
			providerMap.put( providerId, tempUser );
		}
		else
		{
			throw new ProviderNotFoundException();
		}
	}
	
	/**
	 * Gets the rating list.
	 *
	 * @param authToken the auth token
	 * @param providerId the provider id
	 * @return the rating list
	 * @throws OfficeSpaceNotFoundException the office space not found exception
	 * @throws ProviderNotFoundException 
	 */
	public List<Rating> getRatingList ( String authToken, String providerId ) throws OfficeSpaceNotFoundException, ProviderNotFoundException
	{
		if ( providerMap.containsKey( providerId ) )
		{
			User tempUser = providerMap.get( providerId );
			List<Rating> tempProviderRatingList;
			tempProviderRatingList = tempUser.getProfile("provider").getAllRatings();
			return tempProviderRatingList;
		}
		else
		{
			throw new OfficeSpaceNotFoundException();
		}
	}
	
	/**
	 * Creates a new OfficeSpace: add office space to officeSpaceMap; check if it exists already
	 * and throws OfficeSpaceAlreadyExistException if it is. If authentication fails - throw AccessException.
	 * Rating Field is initialized here.
	 * Note: officeSpaceId has to be generated first! Check for officeSpaceIds and providerId in the id buckets,
	 * if this check fails throw the 
	 *
	 * @param authToken the auth token
	 * @param officeSpace the office space
	 * @param guid the guid of the OfficeSpace
	 * @throws OfficeSpaceAlreadyExistException the office space already exist exception
	 * @throws AccessException the access exception
	 */
	public void createOfficeSpace ( String authToken, OfficeSpace officeSpace, String providerId )
			                        throws OfficeSpaceAlreadyExistException, AccessException
	{
		String officeSpaceId = officeSpace.getOfficeSpaceGuid();
		if ( !officeSpaceMap.containsKey( officeSpaceId ) )
		{
			officeSpaceMap.put( officeSpaceId, officeSpace );
		}
		else
		{
			throw new OfficeSpaceAlreadyExistException();
		}
		
		// put the OfficeSpace in the map with a key as a provider ID (officeSpaceMapByProvider)
		
		List<OfficeSpace> tempList;
		if ( !officeSpaceMapByProvider.containsKey( providerId ) )
		{
			tempList = new LinkedList<OfficeSpace>();
			tempList.add( officeSpace );
			officeSpaceMapByProvider.put( providerId, tempList );
		}
		else
		{
			tempList = officeSpaceMapByProvider.get( providerId );
			tempList.add( officeSpace );
			officeSpaceMapByProvider.put( providerId, tempList );
		}
		
	}
	
	/**
	 * accessor method for officeSpaceMap attribute
	 * if the guid not found in the map, it throws OfficeSpaceNotFoundException exception.
	 *
	 * @param authToken the auth token
	 * @param guid the guid
	 * @return OfficeSpace
	 * @throws OfficeSpaceNotFoundException the office space not found exception
	 */
	public OfficeSpace getOfficeSpace ( String authToken, String guid ) throws OfficeSpaceNotFoundException
	{
		if ( officeSpaceMap.containsKey( guid ) )
		{
			return this.officeSpaceMap.get( guid );
		}
		else
		{
			throw new OfficeSpaceNotFoundException();
		}
	}
	
	/**
	 * Gets the office space list.
	 *
	 * @return all values (office spaces) from officeSpaceMap
	 */
	public List<OfficeSpace> getOfficeSpaceList ()
	{
		List<OfficeSpace> officeSpaceList = null;
		officeSpaceList = (List<OfficeSpace>) officeSpaceMap.values();
		return officeSpaceList;
	}
	
	/**
	 * Gets the office space guid list.
	 *
	 * @return List<String>
	 */
	public List<String> getOfficeSpaceGuidList ()
	{
		Set<String> tempSet;
		tempSet = officeSpaceMap.keySet();
		List<String> officeSpaceGuidList = new ArrayList<String> ( tempSet );
		return officeSpaceGuidList;
	}
	
	/**
	 * updates particular office space in the office space map with a new office space based on guid passed in
	 * if the guid not found in the map, it throws OfficeSpaceNotFoundException exception.
	 *
	 * @param authToken the auth token
	 * @param guid the guid
	 * @param officeSpaceId the office space id
	 * @param updatedOffice the updated office
	 * @throws OfficeSpaceNotFoundException the office space not found exception
	 */
	public void updateOfficeSpace ( String authToken, String providerId,
			                        OfficeSpace updatedOffice) throws OfficeSpaceNotFoundException
	{
		String officeSpaceId = updatedOffice.getOfficeSpaceGuid();
		if ( officeSpaceMap.containsKey( officeSpaceId ) )
		{
			officeSpaceMap.put( officeSpaceId, updatedOffice );
		}
		else
		{
			throw new OfficeSpaceNotFoundException();
		}
		List<OfficeSpace> tempList;
		if ( officeSpaceMapByProvider.containsKey( providerId ) )
		{
			tempList = officeSpaceMapByProvider.get( providerId );
			tempList.add( updatedOffice );
			officeSpaceMapByProvider.put( providerId, tempList );
		}
		else
		{
			throw new OfficeSpaceNotFoundException();
		}
	}
	
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
                                    String officeSpaceId ) throws OfficeSpaceNotFoundException
	{
		List<OfficeSpace> tempList;
		OfficeSpace tempOfficeSpace = officeSpaceMap.get( officeSpaceId );
		if ( officeSpaceMapByProvider.containsKey( providerId ) )
		{
			tempList = officeSpaceMapByProvider.get( providerId );
			tempList.remove(tempOfficeSpace);
			officeSpaceMapByProvider.put( providerId, tempList );
		}
		else
		{
			throw new OfficeSpaceNotFoundException();
		}
		
		if ( officeSpaceMap.containsKey( officeSpaceId ) )
		{
			officeSpaceMap.remove( officeSpaceId );
		}
		else
		{
			throw new OfficeSpaceNotFoundException();
		}

	}	
	
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
			                      String officeSpaceId, Rating rating ) throws OfficeSpaceNotFoundException, RatingAlreadyExistsException 
	{
		if ( officeSpaceMap.containsKey( officeSpaceId ) )
		{
			OfficeSpace tempOfficeSpace;
            Map<String, Rating> tempOfficeSpaceRatingMap;
			tempOfficeSpace = officeSpaceMap.get( officeSpaceId );
			tempOfficeSpaceRatingMap = tempOfficeSpace.getRatings();
			if ( !tempOfficeSpaceRatingMap.containsKey( renterId ) )
			{
				tempOfficeSpaceRatingMap.put(renterId, rating );
			}
			else
			{
				throw new RatingAlreadyExistsException();
			}
			tempOfficeSpace.setRatings( tempOfficeSpaceRatingMap );
			officeSpaceMap.put( officeSpaceId, tempOfficeSpace );
		}
		else
		{
			throw new OfficeSpaceNotFoundException();
		}
	}
	
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
                                          String officeSpaceId ) throws OfficeSpaceNotFoundException, RatingNotFoundExcepion 
    {
		if ( officeSpaceMap.containsKey( officeSpaceId ) )
		{
			OfficeSpace tempOfficeSpace;
            Map<String, Rating> tempOfficeSpaceRatingMap;
			tempOfficeSpace = officeSpaceMap.get( officeSpaceId );
			tempOfficeSpaceRatingMap = tempOfficeSpace.getRatings();
			if ( tempOfficeSpaceRatingMap.containsKey( renterId ) )
			{
				tempOfficeSpaceRatingMap.remove( renterId );
			}
			else
			{
				throw new RatingNotFoundExcepion();
			}
			tempOfficeSpace.setRatings( tempOfficeSpaceRatingMap );
			officeSpaceMap.put( officeSpaceId, tempOfficeSpace );
		}
		else
		{
			throw new OfficeSpaceNotFoundException();
		}
    }
	
	
	/**
	 * Returns the list all Ratings correspondent to office space the officeSpaceMap,
	 * if the office space id is not found - OfficeSpaceNotFoundException is thrown.
	 *
	 * @param authToken the auth token
	 * @param officeSpaceId the office space id
	 * @return List<Rating>
	 * @throws OfficeSpaceNotFoundException the office space not found exception
	 */
	public List<Rating> getOfficeSpaceRatingList  ( String authToken, String officeSpaceId ) throws OfficeSpaceNotFoundException 
	{
		if ( officeSpaceMap.containsKey( officeSpaceId ) )
		{
			OfficeSpace tempOfficeSpace;
			tempOfficeSpace = officeSpaceMap.get( officeSpaceId );
			List<Rating> tempOfficeSpaceRatingList;
			tempOfficeSpaceRatingList = tempOfficeSpace.getAllRatings();
			return tempOfficeSpaceRatingList;
		}
		else
		{
			throw new OfficeSpaceNotFoundException();
		}
	}
    	 
}

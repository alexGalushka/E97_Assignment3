package cscie97.asn1.knowledge.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cscie97.asn2.squaredesk.provider.Features;
import cscie97.asn2.squaredesk.provider.Location;
import cscie97.asn2.squaredesk.provider.OfficeSpace;
import cscie97.asn2.squaredesk.provider.Profile;
import cscie97.asn2.squaredesk.provider.Provider;
import cscie97.asn2.squaredesk.provider.ProviderNotFoundException;
import cscie97.asn2.squaredesk.provider.ProviderServiceImpl;
import cscie97.asn2.squaredesk.provider.User;

public class ImporterSD
{

	private ProviderServiceImpl provService;
	private List<User> provUserList;
	

	//predicate - standard
	private Predicate predicate;
	// Preferred features (multiple features can be searched for at once)

	private Predicate locationPredicate;
	private Predicate featurePredicate;
	private Predicate facilityAndCategoryPredicate;
	private Predicate ratingPredicate; 
	
	// start and end date
	private Map<String, String[]> datesForRentMap;
	
	private Map<String, Set<Triple>> mapSdTriples; //might not need them
	
	public ImporterSD(ProviderServiceImpl provService)
	{
		this.provService = provService;
		provUserList = provService.getProviderList( "" );
		mapSdTriples = new HashMap<String,Set<Triple>>();
		locationPredicate = new Predicate ("has_lat_long");
		featurePredicate = new Predicate ("has_feature");
		facilityAndCategoryPredicate = new Predicate ("has_facility_type_category");
		ratingPredicate = new Predicate ("has_average_rating");
	}
	
	// subject is concatenated string "provId&officeId"
	// object is the search criteria
	// predicate is "has"
	
	//rename it Search prep?
	public List<Triple> createSquareDeskTriples ()
	{
		try
		{
	        Profile tempProvider;
	        String provId;
	        String officeId;
	        String provAndOfficeId;
	        List<OfficeSpace> officeList;
	        
	    	//subject
	    	Node subjId;
	    	
	        //feature
	    	Node objFeature;
	    	Node objTraslatedCommonAccessFeat;
	    	Triple resultingFeatTriple;
	    	// location
	    	Node objLocation;
	    	Triple resultingLocTriple;
	    	// facility type, and category
	    	Node objFacility;
	    	Triple resultingFacTriple;
	    	// minimum average rating
	    	Node objRating;
	    	Triple resultingRatTriple;
	        
	        List<Triple> resultTripleList = new ArrayList<Triple>();
	        Features tempFeatures;
	        
	        
			for ( User provUser: provUserList )
			{
				tempProvider = provUser.getProfile( "provider" );
				provId = tempProvider.getGuid();
				officeList = tempProvider.getOfficeSpacesList();
				
				
				for ( OfficeSpace office:officeList )
				{
					officeId = office.getOfficeSpaceGuid();
					provAndOfficeId = provId+"&"+officeId;	
					subjId = new Node(provAndOfficeId);
					
					objLocation = new Node(createSearchableLocation(office.getLocation()));
					resultingLocTriple = new Triple( subjId, locationPredicate, objLocation );
					resultTripleList.add(resultingLocTriple); //add *
					
					tempFeatures = office.getFeatures();
					
					for (String feature:tempFeatures.getAllFeatures())
					{
						objFeature  = new Node ( feature );
						resultingFeatTriple = new Triple( subjId, featurePredicate, objFeature );
						resultTripleList.add(resultingFeatTriple); //add *
					}
					
					//common access is considered as a Feature as well, translated to the type such "hasAccessTo_..."
					for ()
					{
						objTraslatedCommonAccessFeat = NodegetTranslatedCommonAccessList
					}
					objRating = new Node( office.getAverageRating().toString() );
					resultingRatTriple = new Triple( subjId, ratingPredicate, objRating );
					resultTripleList.add( resultingRatTriple ); //add *
					
					
					
					
					
					
					
				}
			}
		}
		catch ( ProviderNotFoundException e )
		{
			//do nothing
		}
		finally
		{
			
		}
		
		return null;
	}
	
	private String createSearchableLocation (Location location)
	{
		// as per "has_lat_long"
		Integer lat = location.getLatitude().intValue();
		Integer lon = location.getLongitude().intValue();
		String result = lat.toString()+"_"+lon.toString();
		return result;
	}
	// very important method
	public void syncUpdate()
	{}
	
}

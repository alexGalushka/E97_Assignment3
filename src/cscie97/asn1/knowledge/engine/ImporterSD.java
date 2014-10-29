package cscie97.asn1.knowledge.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cscie97.asn2.squaredesk.provider.Features;
import cscie97.asn2.squaredesk.provider.Location;
import cscie97.asn2.squaredesk.provider.OfficeSpace;
import cscie97.asn2.squaredesk.provider.Profile;
import cscie97.asn2.squaredesk.provider.ProviderNotFoundException;
import cscie97.asn2.squaredesk.provider.ProviderServiceImpl;
import cscie97.asn2.squaredesk.provider.User;

public class ImporterSD
{
	private List<User> provUserList;

	private Predicate locationPredicate;
	private Predicate featurePredicate;
	private Predicate facilityAndCategoryPredicate;
	private Predicate ratingPredicate; 
	List<Triple> resultTripleList;
	
	// start and end date specified by Provider - feature deferred.
	// private Map<String, String[]> datesForRentMap;
	
	public ImporterSD(ProviderServiceImpl provService)
	{
		provUserList = provService.getProviderList( "" );
		// datesForRentMap = new HashMap<String, String[]>();
		locationPredicate = new Predicate ("has_lat_long");
		featurePredicate = new Predicate ("has_feature");
		facilityAndCategoryPredicate = new Predicate ("has_facility_type_category");
		ratingPredicate = new Predicate ("has_average_rating");
		resultTripleList = new ArrayList<Triple>();
	}
	
	
	/**
	 * collect all required information from the OfficeSpaceImpl object matching the specified search criteria
	 * (location, facility type and category, feature, minimum average rating) and present to the Renter Service
	 *  Knowledge graph in the format of Triples
	 *  Note: subject is concatenated string "provId&officeId"
	 * @return List<Triple>
	 */
	public List<Triple> collectSquareDeskInfoForSearch ()
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
	        Features tempFeatures;
	        
	 
			for ( User provUser: provUserList )
			{
				tempProvider = provUser.getProfile( "provider" );
				provId = tempProvider.getGuid();
				officeList = tempProvider.getOfficeSpacesList();
                String[] tempFacTypeCat = {"",""};
                //String[] tempDates = {"",""};
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
					for (String com:office.getTranslatedCommonAccessList())
					{
						objTraslatedCommonAccessFeat = new Node ( com );
						resultingFeatTriple = new Triple( subjId, featurePredicate, objTraslatedCommonAccessFeat );
						resultTripleList.add(resultingFeatTriple); //add *
						
					}
					
					objRating = new Node( office.getAverageRating().toString() );
					resultingRatTriple = new Triple( subjId, ratingPredicate, objRating );
					resultTripleList.add( resultingRatTriple ); //add *
					
					tempFacTypeCat = office.getFacility().getTraslatedCategoryAndType();
					if ( !tempFacTypeCat[0].equals("") )
					{
						objFacility = new Node ( tempFacTypeCat[0] );
				    	resultingFacTriple = new Triple( subjId, facilityAndCategoryPredicate, objFacility );
				    	resultTripleList.add( resultingFacTriple ); //add *
					}
					if ( !tempFacTypeCat[1].equals("") )
					{
						objFacility = new Node ( tempFacTypeCat[1] );;
				    	resultingFacTriple = new Triple( subjId, facilityAndCategoryPredicate, objFacility );
				    	resultTripleList.add( resultingFacTriple ); //add *
					}
					
					//get the start and end dates
					//tempDates[0]
					//tempDates[1]
				}
			}
		}
		catch ( ProviderNotFoundException e )
		{
			//do nothing
		}
		finally
		{
			// nothing to put in final lol
		}
		return resultTripleList;
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
	{
		
	}
	
}

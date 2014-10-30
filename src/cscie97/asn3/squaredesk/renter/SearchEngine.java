package cscie97.asn3.squaredesk.renter;

import java.util.Set;

import cscie97.asn1.knowledge.engine.QueryEngine;
import cscie97.asn1.knowledge.engine.QueryEngineException;
import cscie97.asn1.knowledge.engine.Triple;

public class SearchEngine
{
	
	private QueryEngine queryEngine;
	
	public SearchEngine()
	{
		queryEngine = new QueryEngine();
	}
	public void SearchForOfficeSpace ( Criteria criteria )
	{
		Set<Triple> ultimateTripleSetFirst = null;
		Set<Triple> ultimateTripleSetLast = null;
		// search by facility
		// when creating criteria for search user specified type of facility, search
		// would be executed according to category_type (second element of tempFacArray)
		// if both elements of tempFacArray are empty strings search would ignore facility criteria
		String[] tempFacArray;
		String search = "";
		String tempLocation = "";
		int ratingParam = 0;
		try
		{
			// by location
			tempLocation = criteria.getLocation().getSearchableLocation();
			if ( !tempLocation.equals( "" ) )
			{
				search = "? has_lat_long "+tempLocation.trim().toLowerCase();
				ultimateTripleSetFirst = queryEngine.executeQuery( search );
			}
			
			// by facility
			tempFacArray = criteria.getFacility().getTraslatedCategoryAndType();
			if ( !tempFacArray[1].equals( "" ) )
			{
				search = "? has_facility_type_category " + tempFacArray[1].trim().toLowerCase();
			}
			else if( tempFacArray[1].equals( "" ) && !tempFacArray[0].equals( "" ) )
			{
				search = "? has_facility_type_category " + tempFacArray[0].trim().toLowerCase();
			}
			ultimateTripleSetLast = queryEngine.executeQuery( search );
			
			ultimateTripleSetLast.addAll( ultimateTripleSetFirst ); //the most current set
			
			// by minimum average rating 
			// has to loop through rounded up rating from passed in rating parameter up to "5".
			// if user don't pass the rating parameter to criteria, on search it would default to 0
			ratingParam = Math.round( criteria.getMinAverageRating() );
			for (Integer i = ratingParam; i<=5; i++)
			{
				search = "? has_average_rating "+i.toString();
				ultimateTripleSetFirst = queryEngine.executeQuery( search );
				ultimateTripleSetLast.addAll( ultimateTripleSetFirst );
			}
			
			// by feature
			for ( String feat:criteria.getPreferredFeatures() )
			{
				search = "? has_feature "+feat.trim().toLowerCase();
				ultimateTripleSetFirst = queryEngine.executeQuery( search );
				ultimateTripleSetLast.addAll( ultimateTripleSetFirst );
			}
			
			// now we have an ultimate set of Triples returned by search, which is going to be passed to 
			// availability validator
		}
		catch (QueryEngineException e)
		{
			// TODO Auto-generated catch block
		}
		finally
		{
				
			
		}
		
		
	}
	
	
}

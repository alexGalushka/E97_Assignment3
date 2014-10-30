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
		Set<Triple> ultimateTripleSetFirst;
		Set<Triple> ultimateTripleSetLast;
		// search by facility
		// when creating criteria for search user specified type of facility, search
		// would be executed according to category_type (second element of tempFacArray)
		// if both elements of tempFacArray are empty strings search would ignore facility criteria
		String[] tempFacArray;
		String search = "";
		String tempLocation = "";
		try
		{
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
			ultimateTripleSetFirst = queryEngine.executeQuery( search );
			
			//by location
			tempLocation = criteria.getLocation().getSearchableLocation();
			if ( !tempLocation.equals( "" ) )
			{
				search = "? has_lat_long "+tempLocation.trim().toLowerCase();
				ultimateTripleSetLast = queryEngine.executeQuery( search );
			}
			
			//
			
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

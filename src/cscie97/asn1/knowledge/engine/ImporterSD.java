package cscie97.asn1.knowledge.engine;

import java.util.List;
import java.util.Set;

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
	
	// Preferred features (multiple features can be searched for at once)
	private Node subjFeature;
	private Node objFeature;
	private Predicate predFeature;
	private Triple tripFeature;
	// location
	private Node subjLocation;
	private Node objLocation;
	private Predicate predLocation;
	private Triple tripLocation;
	// facility type, and category
	private Node subjFacility;
	private Node objFacility;
	private Predicate predFacility;
	private Triple tripFacility;	
	// minimum average rating
	private Node subjRating;
	private Node objRating;
	private Predicate predRating;
	private Triple tripRating;
	// start date
	private Node subjStartDate;
	private Node objStartDate;
	private Predicate predStartDate;
	private Triple tripStartDate;
	// end date
	
	public ImporterSD(ProviderServiceImpl provService)
	{
		this.provService = provService;
		provUserList = provService.getProviderList( "" );
	}
	
	// subject is concatenated string "provId_officeId"
	// object is the search criteria
	// predicate "has"
	public Set<Triple> createSquareDeskTriples ()
	{
		try
		{
	        Profile tempProvider;
	        String provId;
	        String officeId;
	        List<OfficeSpace> officeList;
			for ( User provUser: provUserList )
			{
				tempProvider = provUser.getProfile( "provider" );
				provId = tempProvider.getGuid();
				officeList = tempProvider.getOfficeSpacesList();
				
				for (OfficeSpace office:officeList)
				{
					//here it comes
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
	
	// very important method
	public void syncUpdate()
	{}
	
}

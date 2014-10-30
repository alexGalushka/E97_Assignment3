package cscie97.asn2.test;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cscie97.asn2.squaredesk.provider.AccessException;
import cscie97.asn2.squaredesk.provider.ContactInfo;
import cscie97.asn2.squaredesk.provider.Address;
import cscie97.asn2.squaredesk.provider.GuidGenerator;
import cscie97.asn2.squaredesk.provider.Image;
import cscie97.asn2.squaredesk.provider.Location;
import cscie97.asn2.squaredesk.provider.Features;
import cscie97.asn2.squaredesk.provider.Account;
import cscie97.asn2.squaredesk.provider.Capacity;
import cscie97.asn2.squaredesk.provider.Facility;
import cscie97.asn2.squaredesk.provider.Profile;
import cscie97.asn2.squaredesk.provider.Provider;
import cscie97.asn2.squaredesk.provider.ProviderServiceImpl;
import cscie97.asn2.squaredesk.provider.OfficeSpace;
import cscie97.asn2.squaredesk.provider.OfficeSpaceAlreadyExistException;
import cscie97.asn2.squaredesk.provider.ProviderAlreadyExistsException;
import cscie97.asn2.squaredesk.provider.ProviderNotFoundException;
import cscie97.asn2.squaredesk.provider.Rate;
import cscie97.asn2.squaredesk.provider.Rating;
import cscie97.asn2.squaredesk.provider.RatingAlreadyExistsException;
import cscie97.asn2.squaredesk.provider.User;
import cscie97.asn3.squaredesk.renter.SchedulingService;


/**
 * The Class TestDriver.
 */
public class TestDriver
{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws OfficeSpaceAlreadyExistException the office space already exist exception
	 * @throws AccessException the access exception
	 * @throws ProviderAlreadyExistsException the provider already exists exception
	 */
	public static void main(String[] args) throws OfficeSpaceAlreadyExistException, AccessException, ProviderAlreadyExistsException
    {
		// prepare data to load to the squaredesk objects
		User user;
		Profile uutOfficeProvider;
		OfficeSpace uutOfficeSpace;
        
		ProviderServiceImpl uutOfficeProviderService;
		
		// The guid generator
		GuidGenerator uutGuidGenerator;
		
		Account uutAccount;
		Address uutAddress;
		Capacity uutCapacity;
		ContactInfo uutContactInfo;
		
		Facility uutFacility;
		Features uutFeatures;
		Image uutImage;
		Location uutLocation;
		Rate uutRate1;
		Rate uutRate2;
		Rating uutRating1;
		Rating uutRating2;
		
		@SuppressWarnings("unused")
		SchedulingService schs = new SchedulingService() ; 
		
        // * Data to Office Space * //
			
		String countryCode = "USA";//subject to change to ISO if time permits
		String state = "MA";
		String city = "Cambridge"; //not in the YAML file
		String street1 = "1 story street";
		String street2 = "~";
		String zipcode = "02138";
		uutAddress = new Address( countryCode, street1, street2, city, state, zipcode);
		
        Float latitude = (float) 27.175015;
        Float longitude = (float) 78.042155;
		uutLocation = new Location(uutAddress, longitude, latitude);
		
		String officeSpaceName = "bills office space";
		
		Integer numberOfPeople = 1;
		Float squarefeet = (float) 25;
		Integer workSpaces = 1;
		uutCapacity = new Capacity ( numberOfPeople, workSpaces, squarefeet );

		List<String> uutCommonAccess = new ArrayList<String> ();
		uutCommonAccess.add( "kitchen" );
		uutCommonAccess.add( "backyard" );
		uutCommonAccess.add( "pool" );
		
		String category = "kitchen table";
	    String type = "home";
	    uutFacility = new Facility( category, type );
	    
		List<String> uutFeaturesList = new ArrayList<String> ();
		uutFeaturesList.add( "Whiteboard" );
		uutFeaturesList.add( "WIFI" );
		uutFeaturesList.add( "Windows" );
		uutFeaturesList.add( "Parking" );
		uutFeaturesList.add( "Free Coffee" );
		uutFeatures = new Features ( uutFeaturesList );
		
		String imageDescription = "spacious kitchen table work area";
		String  imageName = "kitchen area";
		URI imageUri = URI.create ("http://www.designdazzle.com/wp-content/uploads/2013/05/66_9thave_KitchenEatingArea-600x763.jpg");
		
		uutImage = new Image ( imageDescription, imageName, imageUri );
		List<Image> uutImageList = new ArrayList<Image>();
		uutImageList.add( uutImage );
		
		Float cost1 = (float) 40;
		String period1 = "week";
		uutRate1 = new Rate ( period1, cost1 );
		Float cost2 = (float) 10;
		String period2 = "day";
		uutRate2 = new Rate ( period2, cost2 );
		List<Rate> uutRateList = new ArrayList<Rate>();
		uutRateList.add( uutRate1 );
		uutRateList.add( uutRate2 );
		
        String authorsName1 = "Frank"; // ID is wrong
        String comment1 = "very quiet and easy to focus on work";
        String date1 = "9/19/2014"; //change the date
        Integer stars1 = 5;
		uutRating1 = new Rating( authorsName1, comment1, date1, stars1 );
        String authorsName2 = "Shirley"; // ID is wrong
        String comment2 = "nice place to work, very productive";
        String date2 = "10/1/2014"; //change the date
        Integer stars2 = 5;
        uutRating2 = new Rating( authorsName2, comment2, date2, stars2 );
        
        
        Map<String, Rating> uutRatingMap = new HashMap<String, Rating>();
        //unique author's id is required
        uutRatingMap.put( "00000001", uutRating1 );
        uutRatingMap.put( "00000002", uutRating2 );
        
		uutOfficeSpace = new OfficeSpace();
		//use mutator methods to populate office space
		uutOfficeSpace.setCapacity( uutCapacity );
		uutOfficeSpace.setCommonAccess( uutCommonAccess );
		uutOfficeSpace.setFacility( uutFacility );
		uutOfficeSpace.setFeatures( uutFeatures );
		uutOfficeSpace.setImages( uutImageList );
		uutOfficeSpace.setRates( uutRateList );
		uutOfficeSpace.setLocation( uutLocation );
		uutOfficeSpace.setRatings( uutRatingMap );
		uutOfficeSpace.setName( officeSpaceName );
			
		// *********************** //
		
		// * Data to Office Provider * //
		
		Integer payPalAccountNumber = 777888999;
		uutAccount = new Account ( payPalAccountNumber );
		
		String firstName = "Fred";
		String lastName = "";
		String email = "fred@gmail.com";
		String phoneNumber = "";
		String dateOfBirth = "";
		uutContactInfo = new ContactInfo ( firstName, lastName, email, phoneNumber, dateOfBirth, null);
		URI providerPictureUri = URI.create ( "http://www.wiredforbooks.org/images/FredRogers4.jpg" );

		// *********************** //
				
		uutGuidGenerator = new GuidGenerator();
		//create new office space with unique guid	
		String providerId = uutGuidGenerator.generateProviderGuid( "auth" );
		String officeId = uutGuidGenerator.generateOfficeSpaceGuid( "auth" );
		uutOfficeSpace.setOfficeSpaceGuid( officeId );
		uutOfficeSpace.setProviderId( providerId );
		
		//Instantiate ProviderService object
		uutOfficeProviderService = ProviderServiceImpl.getInstance();
		uutOfficeProviderService.createOfficeSpace( "auth", uutOfficeSpace, providerId );
		
		//create a new Provider with unique guid
		uutOfficeProvider = new Provider();
		uutOfficeProvider.setGuid( providerId );
		user = new User();
		user.setPicture( providerPictureUri );
		user.setContact( uutContactInfo );
		user.setAccount( uutAccount );
				
		HashMap<String, OfficeSpace> uutOfficeSpaceMap = new HashMap<String, OfficeSpace>();
		uutOfficeSpaceMap.put( providerId, uutOfficeSpace );
		uutOfficeProvider.setOfficeSpaces( uutOfficeSpaceMap );
		
		user.addProfile("provider", uutOfficeProvider);
		
		try 
		{
			uutOfficeProviderService.createProvider( "auth", user );
		}
		catch (ProviderNotFoundException e1)
		{
			System.out.println ( "ERROR: Provider not found." );
		}
		
		// TESTS:
		Collection<Rating> ratingList = null;
		Collection<Rate> rateList = null;
		Collection<OfficeSpace> officeSpaceList = null;
		List<String> listOfFeatures = null;
		User testUser = null;
		Profile provider = null;
		try
		{
			testUser = uutOfficeProviderService.getProvider( "auth" , providerId);
			provider = testUser.getProfile("provider");
		}
		catch (ProviderNotFoundException e)
		{
			String errorMessage = "ERROR: Provider with ID # "+ providerId + " not found.";
			System.out.println ( errorMessage );
		}
		finally
		{
			officeSpaceList = provider.getOfficeSpaces().values();
			
			for (OfficeSpace o : officeSpaceList) 
			{
				//TEST 1: Get office space ratings
				String id = o.getOfficeSpaceGuid();
				ratingList = o.getAllRatings();
				int listCount = ratingList.size();
				Integer ratingAccumulator = 0;
				for ( Rating r : ratingList )
				{
					ratingAccumulator += r.getStars();
				}
				
				Float averageRating = ratingAccumulator/ (float) listCount;
				String printOutMsg = "";
				System.out.println ( "Expected rating:" );
				printOutMsg = "Office Space ID # " + id + " has an average rating of 5.0 stars";
				System.out.println ( printOutMsg );
				System.out.println ( "Calculated rating:" );
			    printOutMsg = "Office Space ID # " + id + " has an average rating of " + averageRating  +" stars";
			    System.out.println ( printOutMsg );
			    
			    //TEST 2: What rate is cheaper if renter decides to rent office for 3 days knowing the fact that only 2 rates (weekly and daily) available?
			    rateList = o.getRates();
			    Float weeklyRate = (float) 0;
			    Float dailyRate = (float) 0;
				for ( Rate r : rateList )
				{
					if (r.getPeriod().equals("week"))
					{
						weeklyRate = r.getRate();
					}
					else
					{
						dailyRate = r.getRate();
					}
				}
				//consider only business days, so the normalization multiplier for dayly rate is 5 
				if ( weeklyRate < dailyRate*5 )
				{
					printOutMsg = "for 5 business days weekly rate is cheaper!";
				}
				else
				{
					printOutMsg = "for 5 business days daily rate is cheaper!";
				}
				 System.out.println ( "\nWhat rate is cheaper if renter decides to rent office for 3 days knowing the fact that only 2 rates (weekly and daily) available?");
				 System.out.println ( "Expected answer:" );
				 System.out.println ( "for 5 business days weekly rate is cheaper!" );
				 System.out.println ( "Derived answer:" );
				 System.out.println (printOutMsg);
				 
				//TEST 3: How many features does the OfficeSpace have?
				 listOfFeatures = o.getFeatures().getAllFeatures();
				 Integer numFeatures = listOfFeatures.size();
				 System.out.println ( "\nHow many features does the OfficeSpace have?");
				 System.out.println ( "Expected answer: 5" );
				 System.out.println ( "Derived answer: "+numFeatures );
				 
			}
			
			
			//Display and Update:
			//1. Non of the providers have been rated yet. Let's rate one (the only one :-) )
			//create the new Rating object with correspondent rating fields
			String author = "";
	        String authorsNameProvider = "Alexander Galushka"; // ID is wrong
	        String commentProvider = "gave me a great discount";
	        String dateProvider= "10/08/2014";
	        Integer starsProvider = 5;
			Rating uutProviderRating = new Rating( authorsNameProvider, commentProvider, dateProvider, starsProvider );
			try
			{
				uutOfficeProviderService.rateProvider( "auth", providerId, "77777777", uutProviderRating);
			} 
			catch (RatingAlreadyExistsException e)
			{
				System.out.println ( "ERROR: The Rating from this renter already exists." );
			} 
			catch (ProviderNotFoundException e)
			{
				System.out.println ( "ERROR: Provider with ID # "+ providerId + " not found." );
			}
			finally
			{
				System.out.println ( "\nWho has recently rated this provider?" );
				try
				{
					Profile tempProvider = uutOfficeProviderService.getProvider("auth", providerId).getProfile("provider");
					
					for (Rating r : tempProvider.getAllRatings())
					{
						author = r.getAuthorsName();
					}
				} 
				catch (ProviderNotFoundException e)
				{
					System.out.println ( "ERROR: Provider with ID # "+ providerId + " not found." );
				}
				finally
				{
					System.out.println ( author );
				}
			}
		}	
    }
}


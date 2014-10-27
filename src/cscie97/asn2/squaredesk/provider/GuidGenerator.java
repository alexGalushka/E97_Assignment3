package cscie97.asn2.squaredesk.provider;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class GuidGenerator.
 */
public class GuidGenerator
{
	
	/** The provider guids. */
	private List<String> providerGuids;
	
	/** The office space guids. */
	private List<String> officeSpaceGuids;
	
	/**
	 * Instantiates a new guid generator.
	 */
	public GuidGenerator()
	{
		this.officeSpaceGuids = new ArrayList<String>();
		this.providerGuids = new ArrayList<String>();
	}
	
	/**
	 * The method generates guid dedicated to provider and adds to the providers guid list
	 * Guid is generated using a cryptographically strong pseudo random number generator.
	 *
	 * @param authToken the auth token
	 * @return generatedId: String
	 */
	public String generateProviderGuid( String authToken )
	{
		String generatedId = "";
		Boolean generateFlag = false;
		while ( !generateFlag )
		{
			generatedId = java.util.UUID.randomUUID().toString();
			if ( !this.providerGuids.contains( generatedId )  )
			{
				this.providerGuids.add( java.util.UUID.randomUUID().toString() );
				generateFlag = true;
			}
		}
		return generatedId;
	}
	
	/**
	 * The method generates guid dedicated to office space and adds to the office space guid list
	 * Guid is generated using a cryptographically strong pseudo random number generator.
	 *
	 * @param authToken the auth token
	 * @return generatedId: String
	 */
	public String generateOfficeSpaceGuid( String authToken )
	{
		String generatedId = "";
		Boolean generateFlag = false;
		while ( !generateFlag )
		{
			generatedId = java.util.UUID.randomUUID().toString();
			if ( !this.officeSpaceGuids.contains( generatedId )  )
			{
				this.officeSpaceGuids.add( java.util.UUID.randomUUID().toString() );
				generateFlag = true;
			}
		}
		return generatedId;
	}
	
}

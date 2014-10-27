package cscie97.asn2.squaredesk.provider;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class User
{
	/** The picture. */
	private URI picture;
	
	/** The contact. */
	private ContactInfo contact;
	
	/** The account. */
	private Account account;
	
	/** The office provider guid. */
	private String guid;
	
	/** List of Profile: User can be Provider, Renter or both */
	private Map<String, Profile> profileMap;
	
	public User()
	{
		picture = null;
		contact = null;
		account = null;
		guid = "";
		profileMap = new HashMap<String, Profile>();
	}
	
	/**
	 * mutator method for guid attribute.
	 *
	 * @param guid the new user guid
	 */
	public void setGuid ( String guid )
	{
		this.guid = guid;
	}
	
	/**
	 * accessor method for guid attribute.
	 *
	 * @return String
	 */
	public String getGuid ()
	{
		return this.guid;
	}
	
	/**
	 * adds a new profile to the Map of Profiles
	 * @param type
	 * @param profile
	 * @throws ProviderAlreadyExistsException 
	 */
	public void addProfile (String type, Profile profile) throws ProviderAlreadyExistsException
	{
		if ( !profileMap.containsKey(type) )
		{
			profileMap.put(type, profile);
		}
		else
		{
			throw new ProviderAlreadyExistsException();
		}
					
	}
	
	/**
	 * Profile getter method
	 * @param type
	 * @throws ProviderNotFoundException
	 */
	public Profile  getProfile (String type) throws ProviderNotFoundException 
	{
		if ( profileMap.containsKey(type) )
		{
			return profileMap.get(type);
		}
		else
		{
			throw new ProviderNotFoundException();
		}
	}
	
	/**
	 * delete profile from the Map of Profiles
	 * @throws ProviderNotFoundException 
	 * @throws ProviderAlreadyExistsException 
	 */
	
	public void deleteProfile (String type) throws ProviderNotFoundException 
	{
		if ( profileMap.containsKey(type) )
		{
			profileMap.remove(type);
		}
		else
		{
			throw new ProviderNotFoundException();
		}
		
	}
	
	/**
	 * update profile of the Map of Profiles
	 * @throws ProviderNotFoundException 
	 * @throws ProviderAlreadyExistsException 
	 */
	
	public void updateProfile (String type, Profile profile) throws ProviderNotFoundException 
	{
		if ( profileMap.containsKey(type) )
		{
			profileMap.put(type, profile);
		}
		else
		{
			throw new ProviderNotFoundException();
		}
		
	}
	
	/**
	 * mutator method for picture attribute.
	 *
	 * @param picture the new picture
	 */
	public void setPicture ( URI picture )
	{
		this.picture = picture;
	}
	
	/**
	 * accessor method for picture attribute.
	 *
	 * @return URI
	 */
	public URI getPicture ()
	{
		return this.picture;
	}
	
	/**
	 * mutator method for contact attribute.
	 *
	 * @param contact the new contact
	 */
	public void setContact ( ContactInfo contact )
	{
		this.contact = contact;
	}
	
	/**
	 * accessor method for contact attribute.
	 *
	 * @return ContactInfo
	 */
	public ContactInfo getContact ()
	{
		return this.contact;
	}
	
	/**
	 * mutator method for account attribute.
	 *
	 * @param account the new account
	 */
	public void setAccount ( Account account )
	{
		this.account = account;
	}
	
	/**
	 * accessor method for account attribute.
	 *
	 * @return Account
	 */
	public Account getAccount ()
	{
		return this.account;
	}
	
}

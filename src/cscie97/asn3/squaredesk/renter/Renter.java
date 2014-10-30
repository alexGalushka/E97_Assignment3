package cscie97.asn3.squaredesk.renter;

import java.util.List;
import java.util.Map;

import cscie97.asn2.squaredesk.provider.OfficeSpace;
import cscie97.asn2.squaredesk.provider.Profile;
import cscie97.asn2.squaredesk.provider.Rating;

public class Renter implements Profile
{
	public enum Gender {MALE, FEMALE};
	
	private Criteria criteria;
	private Gender gender;
		
	
	public Renter()
	{
		criteria = new Criteria();
		gender = Gender.MALE;
	}


	/**
	 * @return the criteria
	 */
	public Criteria getCriteria() {
		return criteria;
	}


	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}


	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}


	@Override
	public void setGuid(String guid) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getGuid() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setOfficeSpaces(Map<String, OfficeSpace> officeSpacesMap) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Map<String, OfficeSpace> getOfficeSpaces() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setRatingsMap(Map<String, Rating> ratingsMap) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Map<String, Rating> getRatingsMap() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Rating> getAllRatings() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<OfficeSpace> getOfficeSpacesList() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getOfficeSpacesIds() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setOfficeSpacesIds(List<String> officeSpacesIds) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addOfficeSpacesIdToList(String officeSpacesId) {
		// TODO Auto-generated method stub
		
	}
	
	
}

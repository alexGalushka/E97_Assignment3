package cscie97.asn3.squaredesk.renter;

public class Renter
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
	
	
}

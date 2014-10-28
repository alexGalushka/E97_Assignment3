package cscie97.asn3.squaredesk.renter;

import java.util.Date;

import cscie97.asn2.squaredesk.provider.OfficeSpace;
import cscie97.asn2.squaredesk.provider.Rate;

public class Booking
{
	private Renter renter;
	private OfficeSpace officespace;
	private Rate rate;
	private Date startDate;
	private Date endDate;
	private boolean paymentStatus;
	private String period;
	
	public Booking(Renter renter, OfficeSpace officespace, Rate rate,
		       Date startDate, Date endDate, boolean paymentStatus, String period)
	{
		this.renter = renter;
		this.officespace = officespace;
		this.rate = rate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.paymentStatus = paymentStatus;
		this.period = period;
	}
	
	public Booking()
	{
		this.renter = null;
		this.officespace = null;
		this.rate = null;
		this.startDate = null;
		this.endDate = null;
		this.paymentStatus = false;
		this.period = "";
	}
	
	
	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	
	/**
	 * @return the renter
	 */
	public Renter getRenter() {
		return renter;
	}
	/**
	 * @param renter the renter to set
	 */
	public void setRenter(Renter renter) {
		this.renter = renter;
	}
	/**
	 * @return the officespace
	 */
	public OfficeSpace getOfficespace() {
		return officespace;
	}
	/**
	 * @param officespace the officespace to set
	 */
	public void setOfficespace(OfficeSpace officespace) {
		this.officespace = officespace;
	}
	/**
	 * @return the rate
	 */
	public Rate getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(Rate rate) {
		this.rate = rate;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the paymentStatus
	 */
	public boolean isPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
}

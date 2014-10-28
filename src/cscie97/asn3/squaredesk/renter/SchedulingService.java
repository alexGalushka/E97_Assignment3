package cscie97.asn3.squaredesk.renter;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SchedulingService
{
	/* 
	 
	 Calendar cal = Calendar.getInstance();
	try {
    	cal.setTime(yourDate);
		}
	catch (Exception e)
	    {
  		System.out.println("Invalid date");
		}
	 
	 */
	
	
	private Map<String, List<Booking>> bookingMap;
	private SimpleDateFormat sdf;
	private String date;
	private String[] dateArray;
	private Calendar calendar;
	private int FIX_DAYS = 100;
	
	public SchedulingService()
	{
		bookingMap = new HashMap<String,List<Booking>>();
		sdf = new SimpleDateFormat("yyyy M d");
		date = sdf.format(new Date());
		bookingMap.put(date, new LinkedList<Booking>());
		dateArray = date.split(" ");
		calendar = new GregorianCalendar(Integer.parseInt(dateArray[0]),
				                         Integer.parseInt(dateArray[1]) - 1, 
										 Integer.parseInt(dateArray[2]));
		
		//create a Map with booking up to 3 months, use - 95 days do be on the safe site
		String tempDateAfterRolling;
		String[] tempDateAfterRollingArr;
		String checkYear;
		for ( int increment = 0; increment < FIX_DAYS; increment++ )
		{
			calendar.roll(Calendar.DAY_OF_YEAR, true); 
			tempDateAfterRolling = sdf.format(calendar.getTime());
			tempDateAfterRollingArr = tempDateAfterRolling.split(" ");
			checkYear = tempDateAfterRollingArr[0]+" 1 1";
			if(checkYear.equals(tempDateAfterRolling))
			{
				calendar.roll(Calendar.YEAR, true);
				tempDateAfterRolling = sdf.format(calendar.getTime());
			}
			
			bookingMap.put(tempDateAfterRolling, null);
		}

	}
	
	
	
	public void createBooking ( Booking booking )
	{
		
		//Date tempStartDate = booking.getStartDate();
		//Date tempEndDate = booking.getEndDate();
		//booking can only be created if date startDate < endDate
		// todo: comparison!
		String tempStartDate = sdf.format(booking.getStartDate());
		String tempEndDate = sdf.format(booking.getEndDate());
		
		List<String> datesRangeList = getDatesRange ( tempStartDate, tempEndDate ); 
		
		
		
	}
	
	private List<String> getDatesRange (String startDate, String endDate)
	{
		List<String> datesList = new LinkedList<String>();
		Calendar tempCalendar; 
		String[] tempDataArray;
		tempDataArray = startDate.split(" ");
		tempCalendar = new GregorianCalendar(Integer.parseInt(tempDataArray[0]),
				                         Integer.parseInt(tempDataArray[1]) - 1, 
										 Integer.parseInt(tempDataArray[2]));
		String tempDateAfterRolling = "";
		String[]tempDateAfterRollingArr;
		String checkYear;
		
		while (!endDate.equals(tempDateAfterRolling))
		{
			tempCalendar.roll(Calendar.DAY_OF_YEAR, true); 
			tempDateAfterRolling = sdf.format(tempCalendar.getTime());
			tempDateAfterRollingArr = tempDateAfterRolling.split(" ");
			checkYear = tempDateAfterRollingArr[0]+" 1 1";
			if(checkYear.equals(tempDateAfterRolling))
			{
				tempCalendar.roll(Calendar.YEAR, true);
				tempDateAfterRolling = sdf.format(tempCalendar.getTime());
			}
			
			datesList.add(tempDateAfterRolling);
		}
		
		return datesList;
	}
	
	public boolean checkAvailability (Booking booking)
	{
		String tempStartDate = sdf.format(booking.getStartDate());
		String tempEndDate = sdf.format(booking.getEndDate());
		
		List<String> datesRangeList = getDatesRange ( tempStartDate, tempEndDate ); 
		
		List<Booking> tempBookingList; 
		for (String date:datesRangeList)
		{
			tempBookingList= bookingMap.get(date);
			for (Booking b:tempBookingList)
			{
				if 
			}
		}
		//Date date = sdf.parse(dateInString);
		return true;
	}
	
    public static Calendar calendarFor(int year, int month, int day) 
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal;
    }
}

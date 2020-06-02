package datesBefore1970withJavaUtilGregorianCalendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class GregorianCalendarHandling {
	public static void main(String[] args) {
		showYear1970();
		showYearMinus5();
		showYearSumerCivilization();
		showYearTyrannosaurus();
		showBeginningOfPurelyGregorianCalendar();
		showAddingHourChangesDay();
		showAddingDayChangesMonth();
		showAddingDayChangesYear();
		showAddingMonthChangesYear();
		showLeapAndNonLeapYear();
		showDifferencesBetweenDifferentTimezones();
		showAllTimezonesAvailable();
		showSomeDatesThroughPatterns();
		showParsingOfSomeStringsThroughPatterns();
		showSomeDST();
		showHourDifferenceBetweenLondonAndNewYorkVariesDueToDST();
		showSomeGregorianCalendarAndTimezoneClassesBehaviourDependingOnSystemTimezone();
		showSomeGregorianCalendarAndTimezoneClassesBehaviourNotDependingOnSystemTimezone();
		
	}
	
	private static void showSomeGregorianCalendarAndTimezoneClassesBehaviourDependingOnSystemTimezone() {
		System.out.println("showSomeGregorianCalendarAndTimezoneClassesBehaviourDependingOnSystemTimezone");
		GregorianCalendar gc = new GregorianCalendar();
		//"new GregorianCalendar()" uses jvm/system configuration (timezone), therefore the result of "new GregorianCalendar()" varies when executed in different timezones.
		//You may test it by executing that method using different OS timezone configuration.
		showSomeGregorianCalendarAndTimezoneClassesBehaviour(gc);
	}
	private static void showSomeGregorianCalendarAndTimezoneClassesBehaviourNotDependingOnSystemTimezone() {
		System.out.println("showSomeGregorianCalendarAndTimezoneClassesBehaviourNotDependingOnSystemTimezone");
		GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("America/New_York"));
		//other possibilities to have Calendar not depending on execution environment : 
		//	1) change value of Timezone.GetDefault(), but thisi is very invasive,and may affect the whole program
		//	2) Instead of using "new GregorianCalendar(myTimezone)" each time we need it, we could use one "BusinessCalendarFactory" (or several if needed) that returns a Calendar initialized with the appropriate timezone so we would avoid bugs due to wrongly initialized GregorianCalendars. 
		showSomeGregorianCalendarAndTimezoneClassesBehaviour(gc);
	}
	private static void showSomeGregorianCalendarAndTimezoneClassesBehaviour(GregorianCalendar gc) {
		showCalendarAndTime(gc);
		System.out.println(gc.getTimeZone().getID());
	}
	
	
	private static void showHourDifferenceBetweenLondonAndNewYorkVariesDueToDST() {
		System.out.println("showHourDifferenceBetweenLondonAndNewYorkVariesDueToDST");
		//Date in year : 	January		NY-switchToSummerTime	Lon-switchToSummerTime	Lon-switchToWinterTime	NY-switchToWinterTime	...EnfOfYear
		//hour difference :	+5			+4						+5						+4						+5
		System.out.println("beginning of the year, hour difference between London and New York is : 5 hours");
		whenItIsXHourAtYTimezoneThenWhatTimeIsItInZTimezone(2020, Calendar.JANUARY,  8,  12,  0, 0, "America/New_York", "Europe/London");//usual +5 hours
		System.out.println("New York clock goes 1 hour forward : hour difference now is : 4 hours");
		whenItIsXHourAtYTimezoneThenWhatTimeIsItInZTimezone(2020, Calendar.MARCH,  8,  2,  30, 0, "America/New_York", "Europe/London");//delta is 4 due to DST(summer time hour change) for NewYork at this date
		System.out.println("London clock goes 1 hour forward : hour difference now is : 5 hours");
		whenItIsXHourAtYTimezoneThenWhatTimeIsItInZTimezone(2020,  Calendar.MARCH,  29, 0, 30, 0, "America/New_York", "Europe/London");//usual +5 hours because DST for 
		//London Sunday, October 25, 2:00 am
		System.out.println("London clock goes 1 hour backward : hour difference now is : 4 hours");
		whenItIsXHourAtYTimezoneThenWhatTimeIsItInZTimezone(2020,  Calendar.OCTOBER,  25, 0, 30, 0, "America/New_York", "Europe/London");//delta is 4 hours
		//NY Sunday, November 1, 2:00 am
		System.out.println("New York clock goes 1 hour backward : hour difference now is : 5 hours");
		whenItIsXHourAtYTimezoneThenWhatTimeIsItInZTimezone(2020,  Calendar.NOVEMBER,  1, 2, 30, 0, "America/New_York", "Europe/London");//back to 5 ?
	}
	private static void showSomeDST() {
		System.out.println("showSomeDST");
		showDSTNewYork();
		showDSTLondon();
		showDSTBerlin();
	}
	private static void showDSTNewYork() {
		//8th of March 2020 2am -> 3am
		GregorianCalendar gcNewYork = new GregorianCalendar(TimeZone.getTimeZone("America/New_York"));
		gcNewYork.set(2020,  Calendar.MARCH,  8,  1,  30, 0);
		showCalendarAndTimeAndTimeZone(gcNewYork);
		gcNewYork.add(Calendar.HOUR, 1);
		showCalendarAndTimeAndTimeZone(gcNewYork);
	}
	private static void showDSTLondon() {
		//29th of March 2020 1am -> 2am
		GregorianCalendar gcLondon = new GregorianCalendar(TimeZone.getTimeZone("Europe/London"));
		gcLondon.set(2020,  Calendar.MARCH,  29, 0, 30, 0);
		showCalendarAndTimeAndTimeZone(gcLondon);
		gcLondon.add(Calendar.HOUR, 1);
		showCalendarAndTimeAndTimeZone(gcLondon);
	}
	private static void showDSTBerlin() {
		//29th of March 2020 2am -> 3am
		GregorianCalendar gcBerlin = new GregorianCalendar(TimeZone.getTimeZone("Europe/Berlin"));
		gcBerlin.set(2020,  Calendar.MARCH,  29, 1, 30, 0);
		showCalendarAndTimeAndTimeZone(gcBerlin);
		gcBerlin.add(Calendar.HOUR, 1);
		showCalendarAndTimeAndTimeZone(gcBerlin);
	}
	
	private static void showParsingOfSomeStringsThroughPatterns() {
		System.out.println("showParsingOfSomeStringsThroughPatterns");
		try {
			//parsing different timezones
			parseSomeStringsThroughPatterns("yyyy-MM-dd'T'HH:mm:ss.SSSZ", "2001-07-04T12:08:56.235-0700");
			parseSomeStringsThroughPatterns("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "2001-07-04T12:08:56.235-06:00");
			parseSomeStringsThroughPatterns("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "2001-07-04T12:08:56.235-00:00");
			parseSomeStringsThroughPatterns("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "2001-07-04T12:08:56.235+00:00");
			parseSomeStringsThroughPatterns("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "2001-07-04T12:08:56.235+01:00");
			parseSomeStringsThroughPatterns("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "2001-07-04T12:08:56.235+01:00");
			//parse dd/mm/yyy
			parseSomeStringsThroughPatterns("dd/MM/yyyy", "01/01/2000");
			parseSomeStringsThroughPatterns("dd/MM/yyyy-hh", "01/01/2000-08");
			//parse BC 
			parseSomeStringsThroughPatterns("dd/MM/yyyy", "01/01/-4");//will print year as 5 :)
			//parse weeks
			parseSomeStringsThroughPatterns("WWW/yyyy", "10/2000");//10th week of the year 2000
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	private static void showSomeDatesThroughPatterns() {
		System.out.println("showSomeDatesThroughPatterns");
		TimeZone myTz = TimeZone.getTimeZone("Europe/Berlin");
		GregorianCalendar myCal = new GregorianCalendar(2005, Calendar.JANUARY, 1, 0, 0);
		myCal.setTimeZone(myTz);
		
		showSomeDatesThroughPatterns("yyyy.MM.dd", myCal.getTime());
		showSomeDatesThroughPatterns("dd/MM/yyyy", myCal.getTime());
		showSomeDatesThroughPatterns("yyyy.MM.dd G", myCal.getTime());
		showSomeDatesThroughPatterns("yyyy.MM.dd HH:mm:ss", myCal.getTime());
		showSomeDatesThroughPatterns("yyyy.MM.dd HH:mm:ss:SSS", myCal.getTime());
		showSomeDatesThroughPatterns("yyyy-MM-dd'T'HH:mm:ss.SSSSSSz", myCal.getTime());
		showSomeDatesThroughPatterns("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ", myCal.getTime());
		showSomeDatesThroughPatterns("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", myCal.getTime());//ISO 8601
		//about "u" -> java 7 SimleDateFormat does not support "u" in pattern
	}
	
	private static void showDifferencesBetweenDifferentTimezones() {
		System.out.println("showDifferencesBetweenDifferentTimezones");
		TimeZone londonTz = TimeZone.getTimeZone("Europe/London");
		TimeZone belfastTz = TimeZone.getTimeZone("Europe/Belfast");
		TimeZone berlinTz = TimeZone.getTimeZone("Europe/Berlin");
		GregorianCalendar londonCal = new GregorianCalendar(2004, Calendar.JANUARY, 1);
		londonCal.setTimeZone(londonTz);
		GregorianCalendar belfastCal = new GregorianCalendar(2004, Calendar.JANUARY, 1);
		belfastCal.setTimeZone(belfastTz);
		GregorianCalendar berlinCal = new GregorianCalendar(2004, Calendar.JANUARY, 1);
		berlinCal.setTimeZone(berlinTz);
		System.out.println("londonCal.compareTo(belfastCal) : "+londonCal.compareTo(belfastCal));//0
		System.out.println("londonCal.compareTo(berlinCal) : "+londonCal.compareTo(berlinCal));//1
	}

	private static void showAllTimezonesAvailable() {
		System.out.println("showAllTimezonesAvailable");
		for(String id : TimeZone.getAvailableIDs()) {
			TimeZone tz = TimeZone.getTimeZone(id);
			System.out.println("timezone : "+id+" --- decalage avec UTC : "+(tz.getRawOffset())/(1000*60*60)+"h");
			GregorianCalendar cal = new GregorianCalendar(2004, Calendar.JANUARY, 1);
			cal.setTimeZone(tz);
		}
	}

	private static void showLeapAndNonLeapYear() {
		System.out.println("showLeapAndNonLeapYear");
		GregorianCalendar myCal = new GregorianCalendar(2004, Calendar.JANUARY, 1);
		System.out.println("is 2004 a leap year : "+ myCal.isLeapYear(2004));
		System.out.println("is 2003 : "+ myCal.isLeapYear(2003));
	}

	private static void showAddingMonthChangesYear() {
		System.out.println("showAddingMonthChangesYear");
		GregorianCalendar myCal = new GregorianCalendar(2001, Calendar.DECEMBER, 15);
		showCalendar(myCal);
		myCal.add(Calendar.MONTH, 1);
		showCalendar(myCal);
	}

	private static void showAddingDayChangesYear() {
		System.out.println("showAddingDayChangesYear");
		GregorianCalendar myCal = new GregorianCalendar(2001, Calendar.DECEMBER, 31);
		showCalendar(myCal);
		myCal.add(Calendar.DAY_OF_MONTH, 1);
		showCalendar(myCal);
	}


	private static void showAddingDayChangesMonth() {
		System.out.println("showAddingDayChangesMonth");
		GregorianCalendar myCal = new GregorianCalendar(2001, Calendar.JANUARY, 31);
		showCalendar(myCal);
		myCal.add(Calendar.DAY_OF_MONTH, 1);
		showCalendar(myCal);
	}

	private static void showAddingHourChangesDay() {
		System.out.println("showAddingHourChangesDay");
		GregorianCalendar myCal = new GregorianCalendar(2000, Month.JANUARY.getValue(), 1, 23, 50);
		showCalendarAndTime(myCal);
		myCal.add(Calendar.HOUR, 1);
		System.out.println("+1 hour");
		showCalendarAndTime(myCal);
	}

	private static void showBeginningOfPurelyGregorianCalendar() {
		// Fri Oct 15 01:00:00 CET 1582
		System.out.println("showBeginningOfPurelyGregorianCalendar");
		GregorianCalendar myCal = new GregorianCalendar(1582, Month.OCTOBER.getValue(), 15);
		showCalendar(myCal);
	}
	
	private static void showYear1970() {
		System.out.println("showYear1970");
		GregorianCalendar myCal = new GregorianCalendar(1970, Month.JANUARY.getValue(), 1);
		showCalendar(myCal);
	}
	private static void showYearMinus5() {
		System.out.println("showYearMinus5");
		GregorianCalendar myCal = new GregorianCalendar(-(5-1), Month.JANUARY.getValue(), 1);
		showCalendarAndEra(myCal);
	}
	private static void showYearSumerCivilization() {
		System.out.println("showYearSumerCivilization");
		GregorianCalendar myCal = new GregorianCalendar(-3400, Month.JANUARY.getValue(), 1);//more or less 3400 years
		showCalendarAndEra(myCal);
	}
	private static void showYearTyrannosaurus() {
		System.out.println("showYearTyrannosaurus");
		GregorianCalendar myCal = new GregorianCalendar(-68000000, Month.JANUARY.getValue(), 1);//more or less 68000000 years, let's not be picky
		showCalendarAndEra(myCal);
	}
	
	
	//util methods
	private static void showCalendar(GregorianCalendar cal) {
		System.out.println(cal.get(Calendar.YEAR) +" - "+cal.get(Calendar.MONTH) +" - "+cal.get(Calendar.DAY_OF_MONTH));
	}
	private static void showCalendarAndEra(GregorianCalendar cal) {
		System.out.println(cal.get(Calendar.YEAR) +" - "+cal.get(Calendar.MONTH) +" - "+cal.get(Calendar.DAY_OF_MONTH) + ", era : "+(cal.get(Calendar.ERA) == GregorianCalendar.AD ? "AD" : "BC"));
	}
	private static void showCalendarAndTime(GregorianCalendar cal) {
		System.out.println(cal.get(Calendar.YEAR) +" - "+cal.get(Calendar.MONTH) +" - "+cal.get(Calendar.DAY_OF_MONTH)+ " - "+cal.get(Calendar.HOUR_OF_DAY)+ "h"+cal.get(Calendar.MINUTE)+ "m"+cal.get(Calendar.SECOND)+"s");
	}
	private static void showCalendarAndTimeAndTimeZone(GregorianCalendar cal) {
		System.out.println(cal.get(Calendar.YEAR) +" - "+cal.get(Calendar.MONTH) +" - "+cal.get(Calendar.DAY_OF_MONTH)+ " - "+cal.get(Calendar.HOUR_OF_DAY)+ "h"+cal.get(Calendar.MINUTE)+ "m"+cal.get(Calendar.SECOND)+"s TZ : " + cal.getTimeZone().getID());
	}
	private static void showSomeDatesThroughPatterns(String pattern, Date date) {
		SimpleDateFormat myFormat = new SimpleDateFormat(pattern);
		String strDate = myFormat.format(date);
		System.out.println("pattern : "+pattern+" : "+strDate);
	}
	private static void parseSomeStringsThroughPatterns(String pattern, String sDate) throws ParseException {
		DateFormat myDateFormat = new SimpleDateFormat(pattern);
		Date result = myDateFormat.parse(sDate);
		System.out.println(result);
	}
	private static void whenItIsXHourAtYTimezoneThenWhatTimeIsItInZTimezone(int year, int month, int day, int hour, int min, int sec, String timezoneIdY, String timezoneIdZ) {
		GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone(timezoneIdY));
		gc.set(year, month, day, hour, min, sec);
		showCalendarAndTimeAndTimeZone(gc);
		gc.setTimeZone(TimeZone.getTimeZone(timezoneIdZ));
		showCalendarAndTimeAndTimeZone(gc);
	}
}

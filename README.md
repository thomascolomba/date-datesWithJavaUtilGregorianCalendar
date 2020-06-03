Handling some dates with java.util.GregorianCalendar.<br/>
<br/>
How to compile and execute :<br/>
mvn package<br/>
java -cp target/datesBefore1970withJavaUtilGregorianCalendar-0.0.1-SNAPSHOT.jar datesBefore1970withJavaUtilGregorianCalendar.GregorianCalendarHandling<br/>
<br/>
I) Generalities<br/>
a)Calendar<br/>
A java.util.Calendar is not meant to represent an instant (the old java.util.Date does this).<br/>
It represents a set of rules to perform operations on instants (add a day or an hour while keeping the date consistent).<br/>
<br/>
The java.util.GregorianCalendar makes sense from tuesday the 4th October 1582 (Julian Calendar) onward.<br/>
The day after is the friday the 15th October 1582 (Gregorian Calendar).<br/>
The java.util.GregorianCalendar allows computing time before that time but does not match the date system used by previous calendar (although it's similar to the Julian Calendar).<br/>
The java.util.GregorianCalendar should be used to compute java.util.Date from the 15th October 1582 onward. <br/>
<br/>
b)Timezone<br/>
A time zone is a region of the globe that observes a uniform standard time for legal, commercial, and social purposes (they follow meridians).<br/>
Timezones can be referred as UTC-12:00, UTC-05:00, UTC+00:00, UTC+03:00, UTC+05:45...<br/>
UTC+00:00 : timezone where London is located.<br/>
UTC-12:00 : timezone considered 12 hours late on UTC.<br/>
UTC is not DST dependant, it won't change whether DST is applied or not.<br/>
<br/>
c)DST (Daylight saving time)<br/>
What does DST stand for ?<br/>
Daylight saving time<br/>
<br/>
What is "DST" ?<br/> 
The practice of advancing clocks during warmer months so that darkness falls later each day according to the clock. Advancing clock should be applied on a specific day.<br/>
Then set it back at mid-autumn because there is no more time with daylight at the end of the day (due to the duration of daylight decreasing after summer solstice).<br/>
<br/>
What does it mean for the official common clock in a country which uses DST ?<br/>
1) At mid-spring, the clock will advance 1 hour (2am will become 3am overnight) and most activities in this country should align with that clock (for instance : if a person is supposed to start working at 8am during winter, the day the clock advances, that person should start working at the "new" 8am, which may feel 1 hour sooner than usual).<br/>
2) At mid-autumn the clock will set back 1 hour.<br/>
<br/>
What does it mean for a human being ?<br/>
With an 1 hour advanced clock, lots of jobs will be finished sooner, therefore human being can enjoy daylight longer.<br/>
<br/>
Why was it invented ?<br/>
So people could emjoy more daylight time if they have a clock-based employment.<br/>
<br/>
Which countries use/do not use DST ?<br/>
Countries located close to the equator have no use for it because summer is not relevant.<br/>
Countries located far from equator can use it but summer does not happen at the same time in the northern/southern hemisphere.<br/>
<br/>
<br/>
II)The code<br/>
<b>showYear1970()</b> shows how to represent Unix epoch.<br/>
<b>showYearMinus5()</b> shows how to represent a date before current era.<br/>
<b>showYearSumerCivilization()</b> shows how to represent early Sumer civilization date (-3400 BC).<br/>
<b>showYearTyrannosaurus()</b> shows how to... well, you know.<br/>
<b>showBeginningOfPurelyGregorianCalendar()</b> shows when humans began to use the Gregorian calendar.<br/>
<b>showAddingHourChangesDay() - showAddingDayChangesMonth() - showAddingDayChangesYear() - showAddingMonthChangesYear()</b> shows how to add days/month to a Date through a GregorianCalendar.<br/>
<b>showLeapAndNonLeapYear()</b> tells whether a year is leap or not.<br/>
<b>showDifferencesBetweenDifferentTimezones()</b> shows that same "day/month/year" are different depending on the timezone.<br/>
<b>showAllTimezonesAvailable()</b> shows the timezones available.<br/>
<b>showSomeDatesThroughPatterns()</b> shows how to print dates with different formats, ex : "dd/MM/yyyy", "yyyy.MM.dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"...<br/>
<b>showParsingOfSomeStringsThroughPatterns()</b> parses strings into dates through different formats.<br/>
<b>showSomeDST()</b> shows DST of London, New York and Berlin in march 2020.<br/> 
<b>showHourDifferenceBetweenLondonAndNewYorkVariesDueToDST()</b> shows how time difference varies between New York and London due to DST not being applied at the same moment.<br/>
<b>showSomeGregorianCalendarAndTimezoneClassesBehaviourDependingOnSystemTimezone()</b> shows that a GregorianCalendar instance behaviour depends of the execution environment (the operating system/jvm configuration) by default.<br/>
<b>showSomeGregorianCalendarAndTimezoneClassesBehaviourNotDependingOnSystemTimezone()</b> shows how to have a GregorianCalendar behaving regardless of the system configuration.<br/>
<b>showGregorianCalendarDontHandleLeapSecond()</b> shows GregorianCalendar don't handle leap seconds.<br/>
<b>showHowToAddressAnHourThatHappensTwiceDuringTheSameDayWhenTheClockGoesBackward()</b> shows how a developer can set a GregorianCalendar to both times referred by the same hour if that hour happens twice in the same day (DST clock backward). It is done through elapsed milliseconds.<br/>
<br/>










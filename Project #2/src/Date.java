import java.text.DecimalFormat;

/**
 * A Comparable class which represents a date.
 * A date is defined by the day, month, and year.
 * @author Evan Maggio, Nilay Naik
 */
public class Date implements Comparable <Date>{
    private int year;                                                                       //represents the year of a given date
    private int month;                                                                      //represents the month of a given date
    private int day;                                                                        //represents the day of a given date

    /**
     * Creates an instance of Date
     * @param year sets the value of year
     * @param month sets the value of month
     * @param day sets the value of day
     */
    public Date (int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Compare this instance of Date to another.
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * @param date: the date which is being compared
     * @return 1 if this date is more recent than the other, -1 if this date is older than the other, 0 otherwise
     */
    public int compareTo (Date date) {
        if (year > date.year){
            return 1;
        }
        if (year < date.year){
            return -1;
        }
        if (month > date.month){
            return 1;
        }
        if (month < date.month){
            return -1;
        }
        if (day > date.day){
            return 1;
        }
        if (day < date.day) {
            return -1;
        }
        return 0;
    }

    /**
     * Creates a String representation of the date
     * @see java.lang.Object#toString()
     * @return a String representation of the date in the format: mm/dd/yyyy
     */
    @Override
    public String toString () {
        DecimalFormat yearFormat = new DecimalFormat ("0000");           //used to format years properly as yyyy
        DecimalFormat dayMonthFormat = new DecimalFormat ("00");         //used to format months and days properly as mm/dd
        return dayMonthFormat.format(month) + '/' + dayMonthFormat.format(day) + '/' + yearFormat.format(year);
    }

    /**
     * Determines whether the date is valid or not.
     * @return false if any of the parameters are 0 or lower, month is greater than 12, or the day is greater than the last day in the given month; true otherwise
     */
    public boolean isValid () {
        int [] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};  //contains the amount of days in each month of a non-leap year
        if (month <= 0 || month > 12 || year <= 0 || day <= 0){
            return false;
        }
        if (month == 2 && year % 4 == 0){
            return day <= 29;
        }
        return day <= daysPerMonth [month - 1];
    }
}


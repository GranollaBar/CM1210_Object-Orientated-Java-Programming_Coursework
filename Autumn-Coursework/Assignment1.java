/*
 * Name: Joshua Noble
 * Student number: 22018971
 */

import java.util.HashSet;
import java.util.Set;
import java.util.*;

public class Assignment1 {

    /*
     * A method to find the day-of-week for a date.
     *
     * Arguments:
     * `date` -- the SimpleDate for which the day-of-week is to be found.
     *
     * Return value:
     * A String representing the day of week. The day of week should be
     * expressed as a three-letter abbreviation; in other words, this method
     * returns one of:
     *   "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
     */
    public static String dayOfWeek( SimpleDate date ) {
        String finalday;
        String fulldate = date.toString();
        String[] seperated_dates = fulldate.split("/");
        int y = Integer.parseInt(seperated_dates[0]);
        int m = Integer.parseInt(seperated_dates[1]);
        int d = Integer.parseInt(seperated_dates[2]);
        System.out.println(fulldate);
        if (seperated_dates[0].length() == 4 && y >= 1753 && seperated_dates[1].length() == 2 && m <= 12 && seperated_dates[2].length() == 2 && d <= 31) {
            if (m < 3){
                m += 12;
                y -= 1;
            }

            Character D_firstChar = seperated_dates[0].charAt(0);
            Character D_secondChar = seperated_dates[0].charAt(1);
            Character C_firstChar = seperated_dates[0].charAt(2);
            Character C_secondChar = seperated_dates[0].charAt(3);
            String C_finalStringDate = String.format("%c%c", C_firstChar, C_secondChar);
            String D_finalStringDate = String.format("%c%c", D_firstChar, D_secondChar);
            int C = Integer.parseInt(C_finalStringDate);
            int D = Integer.parseInt(D_finalStringDate);
            if (C >= 100 || D < 17){
                System.out.print("c and d are not valid");
                return null;
            }
            int W = (13 * (m + 1)) / 5;
            int X = C / 4;
            int Y = D / 4;
            int Z = W + X + Y + d + C - 2 * D;
            int day = Z % 7;
            if (day < 0){
                day += 7;
            }

            String[] days_arr =  {"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
            finalday = days_arr[day];
        }

        else {
            System.out.print("use a valid date to begin");
            return null;
        }
        return finalday;
    }

    /*
     * Given a set of dates, this method will count the number of dates in the
     * set that fall on a particular day-of-week.
     *
     * Arguments:
     * `dates` -- an array of SimpleDate objects
     * `dayOfWeek` -- a String representing the day of week ("Mon" to "Sun")
     *
     * Return value:
     * An integer giving the number of dates that fell on `dayOfWeek`.
     */
    public static int countDatesOnDay( SimpleDate[] dates, String dayOfWeek ) {
        int count = 0;
        for (SimpleDate element:dates){
            String date = dayOfWeek(element);
            if (date == dayOfWeek) {
                count += 1;
            }
        }
        return count;
    }

    /*
     * A method to find the most frequent day-of-week among a collection of
     * dates.
     *
     * Arguments:
     * `dates` -- an array of SimpleDate objects
     *
     * Return value:
     * If the array `dates` is empty, then this method should return the null
     * reference. Otherwise, the method should return the three-letter
     * abbreviation ("Mon", "Tue", etc.) of the day-of-week that was most
     * frequent.
     * In the case that there is a tie for the most-frequent day-of-week,
     * priority should be given to the day-of-week that comes earliest in the
     * week. (For this method, "Mon" is assumed to be the first day of the
     * week.)
     * For example, if there were a tie between Tuesday, Wednesday, and Sunday,
     * the method should return "Tue".
     */
    public static String mostFrequentDayOfWeek( SimpleDate[] dates ) {
        Set<String> alldays = new HashSet<String>();
        String[] alldaysofweek =  {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        if (dates.length == 0){
            return null;
        }

        for (SimpleDate element:dates){
            String date = dayOfWeek(element);
            alldays.add(date);
        }

        int highestoccurence = 0;
        String highestday = "";
        for (String element:alldaysofweek) {
            int occurrenceofday = Collections.frequency(alldays, element);
            if (occurrenceofday > highestoccurence) {
                highestday = element;
            }
        }
        return highestday;
    }
}
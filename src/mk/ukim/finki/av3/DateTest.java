package mk.ukim.finki.av3;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;


class Date {
    int days; //since 1.1.1800, 0days = 1.1.1800, 1day=2.1.1800, 365days=1.1.1801

    String date = null;

    final static int START_YEAR = 1800;

    final static int DAYS_IN_YEAR = 365;

    final static int DAYS_IN_LEAP_YEAR = 366;

    private static final int[] DAYS_OF_MONTH = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    private static final int[] DAYS_OF_MONTH_IN_LEAP_YEAR = {
            0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public Date(int days) {
        this.days = days;
    }

    public Date (int d, int m, int y){

        this.date = String.format("%04d-%02d-%02d", y, m, d);

        days = 0;
        int year = y;
        while (year>START_YEAR){
            if (isLeapYear(year)){
                days+=DAYS_IN_LEAP_YEAR;
            } else {
                days+=DAYS_IN_YEAR;
            }
            --year;
        }

        if (isLeapYear(y)){
            for (int i=1;i<m;i++){
                days+=DAYS_OF_MONTH_IN_LEAP_YEAR[i];
            }
        } else {
            for (int i=1;i<m;i++){
                days+=DAYS_OF_MONTH[i];
            }
        }

        days += (d-2);
    }

    public static boolean isLeapYear (int year) {
        if (year % 4 == 0) {
            return year % 100 != 0 || year % 400 == 0;
        }
        return false;
    }

    @Override
    public String toString() {
        if (date!=null){
            return date;
        }

        int d = days;
        int year = START_YEAR;

        while (true){
            if (isLeapYear(year)){
                if (d - DAYS_IN_LEAP_YEAR < 0){
                    break;
                }
            } else {
                if (d - DAYS_IN_YEAR < 0) {
                    break;
                }
            }

            if (isLeapYear(year)){
                d -= DAYS_IN_LEAP_YEAR;
            } else {
                d -= DAYS_IN_YEAR;
            }

            ++year;
        }

        int month = 1;
        while (true){
            if (isLeapYear(year)){
                if (d - DAYS_OF_MONTH_IN_LEAP_YEAR[month] < 0){
                    break;
                }
            } else {
                if (d - DAYS_OF_MONTH[month] < 0){
                    break;
                }
            }

            if (isLeapYear(year)){
                d -= DAYS_OF_MONTH_IN_LEAP_YEAR[month];
            } else {
                d -= DAYS_OF_MONTH[month];
            }
            ++month;
        }

        d++;

        return String.format("%04d-%02d-%02d", year, month, d);
    }

    public Date addDays (int days) {
        return new Date(this.days+days);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return days == date.days;
    }

    public int compareTo(Date date) {
        return this.days - date.days;
    }

    @Override
    public int hashCode() {
        return Objects.hash(days);
    }
}

public class DateTest {
    public static void main(String[] args) {
        Date date = new Date(1520);
        System.out.println(date);
        System.out.println(Date.isLeapYear(1804));
        System.out.println(date.addDays(6));


        Date date1 = new Date(1,3,1804);
        System.out.println(date1);

    }
}

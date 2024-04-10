package src;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class DayOfTheYear implements TreeTemplate {

    record TestCase(String input, int output) {
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[]{
                new TestCase("2019-01-09", 9),
                new TestCase("2019-02-10", 41),
                new TestCase("1992-09-14", 258)
        };
        for (int i = 0; i < testCases.length; i++) {
            tester(i + 1, testCases[i]);
        }
    }

    private static void tester(int testNumber, TestCase testCase) {
        DayOfTheYear obj = new DayOfTheYear();
        Instant start = Instant.now();
        var result = obj.dayOfYear(testCase.input());
        boolean success = (result == testCase.output());
        StringBuilder message = new StringBuilder();
        message.append("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns")
                .append(" ").append("Success:" + (success))
                .append(" ").append("Output:" + result);
        if (success) {
            System.out.println(message);
        } else {
            System.err.println(message);
        }
        System.out.println();
    }

    public int dayOfYear(String date) {
        int[] daysTillMonth = new int[]{31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(5, 7));
        int day = Integer.valueOf(date.substring(8));
        if (month == 1) {
            return day;
        } else if (month > 2) {
            boolean leapYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
            return daysTillMonth[month - 1 - 1] + day + (leapYear ? 1 : 0);
        } else {
            return daysTillMonth[month - 1 - 1] + day;
        }
    }

}

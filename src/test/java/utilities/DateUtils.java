package utilities;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils
{
    private DateUtils()
    {

    }

    public static String getCurrentDateTime(String format)
    {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    public static String getCurrentDateTime1(String format)
    {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        return dateTime.replaceAll("am", "AM").replaceAll("pm", "PM");
    }

    public static String getCurrentDate(String format)
    {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(format, Locale.ENGLISH));
    }

    public static String getCurrentTime(String format)
    {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    public static String addDaysToCurrentDate(int days, String format)
    {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(format));
    }

    public static String addDaysToCurrentDate1(int days)
    {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static String addDaysToGivenDate(String date, int days)
    {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
        LocalDate parsedDate = LocalDate.parse(date, inputFormatter);
        LocalDate updatedDate = parsedDate.plusDays(days);
        return updatedDate.format(outputFormatter);
    }

    public static String subtractDaysFromCurrentDate(int days, String format)
    {
        return LocalDate.now().minusDays(days).format(DateTimeFormatter.ofPattern(format));
    }

    public static String addMonthsToCurrentDate(int months, String format)
    {
        return LocalDate.now().plusMonths(months).format(DateTimeFormatter.ofPattern(format));
    }

    public static String addYearsToCurrentDate(int years, String format)
    {
        return LocalDate.now().plusYears(years).format(DateTimeFormatter.ofPattern(format));
    }

    public static String getDayOfWeek()
    {
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        return day.toString();
    }

    public static String formatDate(String date, String inputFormat, String outputFormat)
    {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat, Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat, Locale.ENGLISH);
        LocalDate parsedDate = LocalDate.parse(date, inputFormatter);
        return parsedDate.format(outputFormatter);
    }

    public static String formattedDateMMM(String dateString)
    {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(dateString, inputFormatter);
        return date.format(outputFormatter);
    }

    public static String formattedDateMM(String dateString)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date.format(formatter);
    }

    public static String currentDateInCustomFormat()
    {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH));
    }
}
package utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;

public class DataUtils
{

    private DataUtils()
    {

    }

    public static String randomString(int count)
    {
        String generatedString = RandomStringUtils.randomAlphabetic(count);
        return generatedString;
    }

    public static String randomNumber(int count)
    {
        String generatedNumber = RandomStringUtils.randomNumeric(count);
        return generatedNumber;
    }

    public static String randomAlphaNumeric(int count)
    {
        String alphanumeric = RandomStringUtils.randomAlphanumeric(count);
        return alphanumeric;
    }

    public static String randomEmail()
    {
        String generatedString = RandomStringUtils.randomAlphabetic(6);
        String generatedNumber = RandomStringUtils.randomNumeric(3);
        return generatedString + generatedNumber + "@" + "gmail.com";
    }

    public static String randomMblNum()
    {
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public static double extractNumericValueFromText(WebElement element)
    {
        String bal = element.getText();
        String number = bal.replaceAll("[^0-9.]", "").trim();
        // String numberPart = bal.substring(0, 5);
        double expBal = Double.parseDouble(number);
        // expBal += 1;
        return expBal;

    }

	public static String randomAlphaNumeric()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(3).toUpperCase();
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + generatedNumber);
	}

}
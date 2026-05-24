package utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;

public class DataUtils
{

    private DataUtils()
    {

    }

    public static String randomString()
    {
        String generatedString = RandomStringUtils.randomAlphabetic(6);
        return generatedString;
    }

    public static String randomNumber()
    {
        String generatedNumber = RandomStringUtils.randomNumeric(4);
        return generatedNumber;
    }

    public static String randomAlphaNumeric()
    {
        String alphanumeric = RandomStringUtils.randomAlphanumeric(10);
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

//	public String randomAlphaNumeric()
//	{
//		String generatedString = RandomStringUtils.randomAlphabetic(3);
//		String generatedNumber = RandomStringUtils.randomNumeric(3);
//		return (generatedString + "@" + generatedNumer);
//	}

}
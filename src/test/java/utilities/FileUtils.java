package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils
{
    private FileUtils()
    {

    }

    public static String getDataFile(String module, String subFolder, String filename)
    {
        String baseDirectory = System.getProperty("user.dir") + "\\src\\test\\java\\models\\" + module + "\\";

        if (subFolder != null && !subFolder.isEmpty())
        {
            baseDirectory += subFolder + "\\";
        }

        baseDirectory += filename + ".json";

        return getFile(baseDirectory);
    }

    public static String getFile(String filename)
    {
        String result;
        File file = new File(filename);

        if (!file.exists())
        {
            throw new RuntimeException("Invalid path or file: " + filename + " does not exist");
        }

        try (FileInputStream fileStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileStream);
             BufferedReader reader = new BufferedReader(inputStreamReader))
        {

            StringBuilder contentBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
            {
                contentBuilder.append(line).append(System.lineSeparator());
            }

            result = contentBuilder.toString().trim();
        } catch (IOException e)
        {
            throw new RuntimeException("Error reading file: " + filename, e);
        }

        return result;
    }

//	public static String getDataFile(String product, String module, String subFolder, String filename)
//	{
//		String baseDirectory = System.getProperty("user.dir").substring(0, 1)
//				+ ":\\Enfinity.Online\\Enfinity.Web.Test\\Enfinity." + product + ".Test.UI\\Models\\" + module + "\\";
//
//		if (subFolder != null && !subFolder.isEmpty())
//		{
//			baseDirectory += subFolder + "\\";
//		}
//
//		baseDirectory += filename + ".json";
//
//		return getFile(baseDirectory);
//	}

}
package utilities;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader
{
    private static final String JSON_FILE_PATH = ".\\testdata\\Payroll.json";

    public static JsonNode readJsonData()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            return objectMapper.readTree(new File(JSON_FILE_PATH));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
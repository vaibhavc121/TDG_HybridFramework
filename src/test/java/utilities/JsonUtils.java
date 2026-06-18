package utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils
{
    private JsonUtils()
    {

    }

    //region Ultimate JSON Parser
    public static <T> List<T> convertJsonListDataModel(String fileData, String sectionPath, Class<T> clazz)
    {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode currentNode = objectMapper.readTree(fileData);

            // Split path by dot, support array indexing like departments[0]
            String[] pathParts = sectionPath.split("\\.");

            for (String part : pathParts) {
                if (part.contains("[") && part.contains("]")) {
                    String key = part.substring(0, part.indexOf("["));
                    int index = Integer.parseInt(part.substring(part.indexOf("[") + 1, part.indexOf("]")));

                    currentNode = currentNode.get(key);
                    if (currentNode == null || !currentNode.isArray() || currentNode.size() <= index) {
                        throw new RuntimeException("Invalid array access in path: " + part);
                    }
                    currentNode = currentNode.get(index);
                } else {
                    currentNode = currentNode.get(part);
                    if (currentNode == null) {
                        throw new RuntimeException("Section '" + sectionPath + "' not found in JSON.");
                    }
                }
            }

            if (!currentNode.isArray()) {
                return Collections.emptyList(); // Or throw exception if you expect array
            }

            List<T> resultList = new ArrayList<>();
            for (JsonNode node : currentNode) {
                resultList.add(objectMapper.treeToValue(node, clazz));
            }

            return resultList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON list data model", e);
        }
    }
    //endregion

    //region Same As Above
    public static <T> List<T> extractJsonListByPath(String fileData, String path, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(fileData);

            JsonNode targetNode = navigateToNode(rootNode, path);
            if (targetNode == null) {
                throw new RuntimeException("Path not found in JSON: " + path);
            }

            List<T> resultList = new ArrayList<>();

            // If it's an array, parse all items
            if (targetNode.isArray()) {
                for (JsonNode item : targetNode) {
                    resultList.add(objectMapper.treeToValue(item, clazz));
                }
            }
            // If it's a single object, parse as one item
            else if (targetNode.isObject()) {
                resultList.add(objectMapper.treeToValue(targetNode, clazz));
            }
            else {
                throw new RuntimeException("Target node is neither an object nor an array: " + path);
            }

            return resultList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract data from JSON using path: " + path, e);
        }
    }
    private static JsonNode navigateToNode(JsonNode rootNode, String path) {
        String[] parts = path.split("\\.");
        JsonNode currentNode = rootNode;

        for (String part : parts) {
            // Handle array indexes like items[0]
            if (part.contains("[") && part.contains("]")) {
                String fieldName = part.substring(0, part.indexOf("["));
                int index = Integer.parseInt(part.substring(part.indexOf("[") + 1, part.indexOf("]")));

                currentNode = currentNode.get(fieldName);
                if (currentNode == null || !currentNode.isArray() || currentNode.size() <= index) {
                    return null;
                }
                currentNode = currentNode.get(index);
            } else {
                currentNode = currentNode.get(part);
                if (currentNode == null) {
                    return null;
                }
            }
        }

        return currentNode;
    }

    //endregion

    //region Optional 1- to Handle Nested Sections
    public static <T> List<T> convertJsonListDataModel1(String fileData, String sectionPath, Class<T> clazz)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(fileData);

            // Split path by dot for nested access
            String[] pathParts = sectionPath.split("\\.");
            JsonNode currentNode = rootNode;

            for (String part : pathParts)
            {
                currentNode = currentNode.get(part);
                if (currentNode == null)
                {
                    throw new RuntimeException("Section '" + sectionPath + "' not found in JSON.");
                }
            }

            if (!currentNode.isArray())
            {
                return Collections.emptyList();
            }

            List<T> resultList = new ArrayList<>();
            for (JsonNode node : currentNode)
            {
                T obj = objectMapper.treeToValue(node, clazz);
                resultList.add(obj);
            }

            return resultList;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Failed to parse JSON list data model", e);
        }
    }
    //endregion

    //region Optional 2- Older
    public static <T> List<T> convertJsonListDataModel2(String fileData, String section, Class<T> clazz)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the file into a tree
            JsonNode rootNode = objectMapper.readTree(fileData);
            JsonNode targetNode = rootNode.get(section);

            if (targetNode == null || !targetNode.isArray())
            {
                return Collections.emptyList();
            }

            // Deserialize into list of target class
            List<T> resultList = new ArrayList<>();
            for (JsonNode node : targetNode)
            {
                T obj = objectMapper.treeToValue(node, clazz);
                resultList.add(obj);
            }

            return resultList;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to parse JSON list data model", e);
        }
    }
    //endregion













//	public static <T> List<T> convertJsonListDataModel(String fileData, String section)
//	{
//		try
//		{
//			ObjectMapper objectMapper = new ObjectMapper();
//			Map<String, List<T>> jsonData = objectMapper.readValue(fileData, new TypeReference<Map<String, List<T>>>()
//			{
//			});
//			return jsonData.containsKey(section) ? jsonData.get(section) : Collections.emptyList();
//		} catch (Exception e)
//		{
//			throw new RuntimeException("Failed to parse JSON list data model", e);
//		}
//	}
//
//	public static <T> T convertJsonDataModel(String fileData, Class<T> clazz)
//	{
//		try
//		{
//			ObjectMapper objectMapper = new ObjectMapper();
//			return objectMapper.readValue(fileData, clazz);
//		} catch (Exception e)
//		{
//			throw new RuntimeException("Failed to parse JSON data model", e);
//		}
//	}

}
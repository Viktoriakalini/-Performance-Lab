import com.google.gson.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class task3 {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Укажите три пути к файлам: values.json, tests.json и report.json.");
            return;
        }

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        try {
            Gson gson = new Gson();
            JsonObject valuesData = gson.fromJson(new FileReader(valuesFilePath), JsonObject.class);
            Map<Integer, String> valuesMap = new HashMap<>();

            for (JsonElement element : valuesData.getAsJsonArray("values")) {
                JsonObject valueObject = element.getAsJsonObject();
                int id = valueObject.get("id").getAsInt();
                String value = valueObject.get("value").getAsString();
                valuesMap.put(id, value);
            }

            JsonObject testsData = gson.fromJson(new FileReader(testsFilePath), JsonObject.class);
            updateTestValues(testsData.getAsJsonArray("tests"), valuesMap);

            try (FileWriter writer = new FileWriter(reportFilePath)) {
                gson.toJson(testsData, writer);
            }

            System.out.println("Файл report.json успешно создан.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateTestValues(JsonArray testsArray, Map<Integer, String> valuesMap) {
        for (JsonElement element : testsArray) {
            JsonObject testObject = element.getAsJsonObject();
            int id = testObject.get("id").getAsInt();
            if (valuesMap.containsKey(id)) {
                testObject.addProperty("value", valuesMap.get(id));
            }

            if (testObject.has("values")) {
                updateTestValues(testObject.getAsJsonArray("values"), valuesMap);
            }
        }
    }
}

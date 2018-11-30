package Protocol;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import static Controller.Utility.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public abstract class PrclInitializer {


    // load json protocol data and save to HashMap using "request key"
    // to pair with PrclSchema object
    // NOTE: loadList & keyList should have the same length
    public static HashMap<String, PrclSchema> loadJsonData(String[] loadList, String[] keyList, String pathNoFileName)
        throws ParseException, IOException {
        HashMap<String, PrclSchema> prclMap = new HashMap<>();

        for (int i = 0; i < loadList.length; i++) {
            String fullPath = pathNoFileName + loadList[i];
            debugPrint("Reading json file: " + fullPath);
            JSONObject data = loadDataFromJson(fullPath);
            prclMap.put(keyList[i], makePrclSchema(data));
        }
        return prclMap;
    }


    public static JSONObject loadConfigData(String configPath)
        throws ParseException, IOException {
        JSONObject config = readJsonFile(configPath);
        config = (JSONObject) config.get("Protocol Settings");

        return config;
    }

    private static JSONObject loadDataFromJson(String path)
        throws ParseException, IOException {
        JSONObject prclData = readJsonFile(path);
        prclData = (JSONObject) prclData.get("Data");

        return prclData;
    }

    private static int[] parseJsonValueField(JSONObject dataField, String[] keys) {
        debugPrint("Keys for data parsing: " + Arrays.deepToString(keys));
        int[] values = new int[dataField.size()];
        for (int i = 0; i < values.length; i++) {
            String str = (String) dataField.get(keys[i]);
            debugPrint("Switch on: " + str);
            switch (str) {
                case "null":
                    debugPrint("Case: null");
                    values[i] = -1; // signifying that this is a variable field
                    break;

                case "A":
                    debugPrint("Case: A");
                    values[i] = 65; // Char 'A' - hex 41, decimal 65
                    break;

                case "N":
                    debugPrint("Case: N");
                    values[i] = 78; // Char 'N' - hex 4E, decimal 78
                    break;

                default:
                    debugPrint("Case: default");
                    values[i] = hexStringToInt(str); // parse to int
                    break;
            }
        }
        return values;
    }

    private static PrclSchema makePrclSchema(JSONObject data) {
        DataPair req = makeDataPair(data, "Request Structure", "Request Values");
        DataPair ack = makeDataPair(data, "Ack Structure", "Ack Values");
        DataPair nak = makeDataPair(data, "Nak Structure", "Nak Values");

        return new PrclSchema(req, ack, nak);
    }

    private static DataPair makeDataPair(JSONObject data, String structKey, String valueKey) {
        JSONArray dataStruct = (JSONArray) data.get(structKey);
        JSONObject dataValues = (JSONObject) data.get(valueKey);

        String[] strStruct = jsonArrayToStringArray(dataStruct);
        debugPrint("jsonArrayToStringArrayDebug: " + Arrays.deepToString(strStruct));
        int[] values = parseJsonValueField(dataValues, strStruct);

        return new DataPair(strStruct, values);
    }

}
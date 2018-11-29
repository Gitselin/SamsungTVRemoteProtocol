package Protocol;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import static Controller.Utility.*;

import java.io.IOException;
import java.util.HashMap;

public abstract class PrclInitializer {

    // load json protocol data and save to HashMap using "request key"
    // to pair with PrclFunction object
    public static HashMap<String, PrclFunction> loadJsonData(String[] loadList, String path) {
        HashMap<String, PrclFunction> prclMap = new HashMap<>();
    }


    public static JSONObject loadConfigData(String configPath)
        throws ParseException, IOException {
        JSONObject config = readJsonFile(configPath);
        config = (JSONObject) config.get("Protocol Settings");

        return config;
    }

    private static JSONObject loadRequestFromJson(String path) {

    }

}

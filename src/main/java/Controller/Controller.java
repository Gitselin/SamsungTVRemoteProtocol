package Controller;

import Protocol.PrclSchema;
import Protocol.PrclInitializer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import static Controller.Utility.*;

public class Controller {
    private static final String JSON_PATH = "JSON/"; // path for JSON-files
    private static final String CONFIG_PATH = JSON_PATH + "config.json";

    // Config variables
    private final String IP;
    private final int PORT;
    private final int SCREEN_ID;
    private final String[] LOAD_LIST;
    private final String[] KEY_LIST;

    private static HashMap<String, PrclSchema> prclLibrary;

    public static void main(String[] args) {
        DbAdapter.createNewDatabase("Tester");
        DbAdapter.connect("Tester");
    }

    public Controller()
        throws ParseException, IOException {
            JSONObject config = PrclInitializer.loadConfigData(CONFIG_PATH);
            IP = (String) config.get("IP");
            PORT = longToInt(hexStringToInt((String) config.get("Screen ID")));
            SCREEN_ID = longToInt((long) config.get("Screen ID"));
            LOAD_LIST = jsonArrayToStringArray((JSONArray) config.get("load list"));
            KEY_LIST = jsonArrayToStringArray((JSONArray) config.get("key list"));

            // build prclLibrary from PrclInitializer method call
            prclLibrary = PrclInitializer.loadJsonData(LOAD_LIST, KEY_LIST, JSON_PATH);
    }

    public HashMap<String, PrclSchema> getPrclLibrary() {
        return prclLibrary;
    }

    public String[] getConfigForDebug() {
        return new String[] {IP, Integer.toString(PORT), Integer.toString(SCREEN_ID), Arrays.deepToString(LOAD_LIST), Arrays.deepToString(KEY_LIST)};
    }

    public String[] getLOAD_LIST() {
        return LOAD_LIST;
    }

    public String[] getKEY_LIST() {
        return KEY_LIST;
    }


}

package Controller;

import Protocol.PrclFunction;
import Protocol.PrclInitializer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

import static Controller.Utility.*;

public class Controller {
    private static final String CONFIG_PATH = "Protocol/JSON/config.json"; // path for JSON-files

    // Config variables
    private final String IP;
    private final int PORT;
    private final int SCREEN_ID;
    private final String[] LOAD_LIST;
    private final String[] KEY_LIST;

    private static HashMap<String, PrclFunction> prclLibrary;

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

            // TODO - build prclLibrary from PrclInitializer method call
    }

    /*
    public static void loadConfig() {
        try {
            JSONObject config = readJsonFile(CONFIG_PATH);
            config = (JSONObject) config.get("Protocol Settings");
            IP = (String) config.get("IP");

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}

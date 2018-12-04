package Controller;

import Protocol.PrclInitializer;
import Protocol.PrclSchema;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import static Controller.Utility.*;

public class Controller {
    private static final String JSON_PATH = "res/JSON/"; // path for JSON-files
    private static final String CONFIG_PATH = JSON_PATH + "config.json";

    // Config variables
    private final String IP;
    private final int PORT;
    private final int SCREEN_ID;
    private final int NET_READ_DELAY_MS = 10;
    private final String[] LOAD_LIST;
    private final String[] KEY_LIST;

    private static iPrclAdapter prclHandler;
    private static NetConnector network;


    public static void main(String[] args) {
        DbAdapter.createNewDatabase("external test");
        //DbAdapter.connect("Tester");
    }

    public Controller()
        throws ParseException, IOException {
            JSONObject config = PrclInitializer.loadConfigData(CONFIG_PATH);
            IP = (String) config.get("IP");
            PORT = longToInt((long) config.get("PORT"));
            SCREEN_ID = hexStringToInt((String) config.get("Screen ID"));
            LOAD_LIST = jsonArrayToStringArray((JSONArray) config.get("load list"));
            KEY_LIST = jsonArrayToStringArray((JSONArray) config.get("key list"));
            debugPrint("Config loading complete..");
            // build prclLibrary from PrclInitializer method call
            prclHandler = new PrclAdapter(SCREEN_ID, LOAD_LIST, KEY_LIST, JSON_PATH);
            network = new NetConnector(IP, PORT, NET_READ_DELAY_MS);
    }

    public String[][] sendRequest(String requestKey, int[] varData)
        throws IOException, InterruptedException {
        int[] fullData = prclHandler.parseRequest(requestKey, varData);
        byte[] response = network.sendData(fullData, prclHandler.getFullAckLength(requestKey));

        return prclHandler.parseResponse(requestKey, byteArrayToUnsignedIntArray(response));
    }

    public String[][] sendRequestNoVars(String key)
        throws InterruptedException, IOException {
        int[] varData = {SCREEN_ID};
        return sendRequest(key,varData);
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


    /*
     *  DEBUG METHODS
     */

    public HashMap<String, PrclSchema> debugGetPrclLibrary() {
        return prclHandler.debugGetPrclLibrary();
    }


}

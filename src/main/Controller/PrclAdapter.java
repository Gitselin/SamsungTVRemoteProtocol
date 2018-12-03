package Controller;

import Protocol.PrclInitializer;
import Protocol.PrclSchema;
import static Controller.Utility.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class PrclAdapter implements iPrclAdapter {
    private HashMap<String, PrclSchema> prclLibrary;
    private final int SCREEN_ID;




    public PrclAdapter(int screenID, String[] loadList, String[] keyList, String jsonPath)
        throws ParseException, IOException {
        // run PrclInitializer and build Prcl HashMap
        prclLibrary = PrclInitializer.loadJsonData(loadList, keyList, jsonPath);
        SCREEN_ID = screenID;
    }

    @Override
    public String[] getRequestKeys() {

        return null; // TODO
    }

    @Override // Send a request matched to requestKey. variableData is int array for fields that are variable and likely set by user in GUI
              // Header etc static fields are handled internally
    public int[] buildRequest(String requestKey, int[] variableData) {
        // Case Switch by requestKey (valid keys defined in config.json)
        // The switch should contain an entry for all implemented protocol requests
        int[] result;

        switch (requestKey) {
            case "Get Status": {
                PrclSchema action = prclLibrary.get("Get Status");
                result =  processRequest(variableData, action);
                break;
            }

            default: {
                result = null; // Should not happen, included to make it obvious something went wrong if we end here
                debugPrint("WARNING: sendRequest() switch went to default (in Controller.PrclAdapter)");
            }
        }

        return result;
    }

    private int[] processRequest(int[] variableData, PrclSchema action) {
        int[] result = action.getRequest().getDataValues();
        int[] varPositions = action.getRequestVarPos();

        // set screen id
        result[varPositions[0]] = SCREEN_ID; // first position of a variable is the screen id
        // set other variables from parameters
        for (int i = 1; i < varPositions.length-1; i++) { // skip the first (ID) & the last (checksum)
            result[varPositions[i]] = variableData[i];
        }
        // calc checksum
        result[result.length-1] = calcChecksum(result);
        return result;
    }


    private int calcChecksum(int[] data) {
        int checksum = 0;//commandType + id + dataLength + dataValue;
        for (int i = 1; i < data.length-1; i++) { // skip first and last (header and checksum positions)
            checksum += data[i];
        }

        // Specification for checksum is only the last to digits
        return checksum & 0xff; // Anding for the 8 least significant bits
    }



}

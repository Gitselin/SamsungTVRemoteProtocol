package Controller;

import Protocol.DataPair;
import Protocol.PrclInitializer;
import Protocol.PrclSchema;
import static Controller.Utility.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class PrclAdapter implements iPrclAdapter {
    private final int BASE_ACK_LENGTH = 5;
    private HashMap<String, PrclSchema> prclLibrary;
    private final int SCREEN_ID;


    public PrclAdapter(int screenID, String[] loadList, String[] getKeyList, String[] setKeyList,String jsonPath)
        throws ParseException, IOException {
        // run PrclInitializer and build Prcl HashMap
        prclLibrary = PrclInitializer.loadJsonData(loadList, getKeyList, setKeyList,jsonPath);
        SCREEN_ID = screenID;
    }

    // Send a request matched to requestKey. variableData is int array for fields that are variable and likely set by user in GUI
    // Header etc static fields are handled internally
    @Override
    public int[] parseRequest(String requestKey, int[] variableData) {
        // Case Switch by requestKey (valid keys defined in config.json)
        // The switch should contain an entry for all implemented protocol function
        int[] result;

        switch (requestKey) {
            case "Get Status": {
                PrclSchema action = prclLibrary.get("Get Status");
                result =  processRequest(variableData, action.getGetRequest());
                break;
            }

            case "Get Video Status": {
                PrclSchema action = prclLibrary.get("Get Video Status");
                result =  processRequest(variableData, action.getGetRequest());
                break;
            }

            case "Get Standby Setting": {
                PrclSchema action = prclLibrary.get("Get Standby Setting");
                result =  processRequest(variableData, action.getGetRequest());
                break;
            }

            case "Set Standby Setting": {
                PrclSchema action = prclLibrary.get("Set Standby Setting");
                result =  processRequest(variableData, action.getSetRequest());
                break;
            }

            case "Get Color": {
                PrclSchema action = prclLibrary.get("Get Color");
                result =  processRequest(variableData, action.getGetRequest());
                break;
            }

            case "Set Color": {
                PrclSchema action = prclLibrary.get("Set Color");
                result =  processRequest(variableData, action.getSetRequest());
                break;
            }

            case "Get Tint": {
                PrclSchema action = prclLibrary.get("Get Tint");
                result =  processRequest(variableData, action.getGetRequest());
                break;
            }

            case "Set Tint": {
                PrclSchema action = prclLibrary.get("Set Tint");
                result =  processRequest(variableData, action.getSetRequest());
                break;
            }

            case "Get Contrast": {
                PrclSchema action = prclLibrary.get("Get Contrast");
                result =  processRequest(variableData, action.getGetRequest());
                break;
            }

            case "Set Contrast": {
                PrclSchema action = prclLibrary.get("Set Contrast");
                result =  processRequest(variableData, action.getSetRequest());
                break;
            }

            case "Get Brightness": {
                PrclSchema action = prclLibrary.get("Get Brightness");
                result =  processRequest(variableData, action.getGetRequest());
                break;
            }

            case "Set Brightness": {
                PrclSchema action = prclLibrary.get("Set Brightness");
                result =  processRequest(variableData, action.getSetRequest());
                break;
            }

            case "Get Sharpness": {
                PrclSchema action = prclLibrary.get("Get Sharpness");
                result =  processRequest(variableData, action.getGetRequest());
                break;
            }

            case "Set Sharpness": {
                PrclSchema action = prclLibrary.get("Set Sharpness");
                result =  processRequest(variableData, action.getSetRequest());
                break;
            }

            case "Get Panel State": {
                PrclSchema action = prclLibrary.get("Get Panel State");
                result =  processRequest(variableData, action.getGetRequest());
                break;
            }

            case "Set Panel State": {
                PrclSchema action = prclLibrary.get("Set Panel State");
                result =  processRequest(variableData, action.getSetRequest());
                break;
            }

            case "Get Color Tone": {
                PrclSchema action = prclLibrary.get("Get Color Tone");
                result =  processRequest(variableData, action.getGetRequest());
                break;
            }

            case "Set Color Tone": {
                PrclSchema action = prclLibrary.get("Set Color Tone");
                result =  processRequest(variableData, action.getSetRequest());
                break;
            }

            default: {
                result = null; // Should not happen, included to make it obvious something went wrong if we end here
                debugPrint("WARNING: parseRequest() switch went to default (in Controller.PrclAdapter)");
            }
        }

        return result;
    }

    @Override
    public String[][] parseResponse(String requestKey, int[] response) {
        // Case Switch by requestKey (valid keys defined in config.json)
        // The switch should contain an entry for all implemented protocol function
        String[][] result;
        System.out.println("ParseResponse switch on: " + requestKey);

        switch (requestKey) {
            case "Get Status": {
                System.out.println("    case: Get Status");
                PrclSchema action = prclLibrary.get("Get Status");
                result = processResponse(response, action);
                break;
            }

            case "Get Video Status": {
                System.out.println("    case: Get Video Status");
                PrclSchema action = prclLibrary.get("Get Video Status");
                result = processResponse(response, action);
                break;
            }

            case "Set Standby Setting": {
                System.out.println("    case: Set Standby Setting");
                PrclSchema action = prclLibrary.get("Set Standby Setting");
                result = processResponse(response, action);
                break;
            }

            case "Get Standby Setting": {
                System.out.println("    case: Get Standby Setting");
                PrclSchema action = prclLibrary.get("Set Standby Setting");
                result = processResponse(response, action);
                break;
            }

            case "Get Color": {
                System.out.println("    case: Get Color");
                PrclSchema action = prclLibrary.get("Get Color");
                result = processResponse(response, action);
                break;
            }

            case "Set Color": {
                System.out.println("    case: Set Color");
                PrclSchema action = prclLibrary.get("Set Color");
                result = processResponse(response, action);
                break;
            }

            case "Get Tint": {
                System.out.println("    case: Get Tint");
                PrclSchema action = prclLibrary.get("Get Tint");
                result = processResponse(response, action);
                break;
            }

            case "Set Tint": {
                System.out.println("    case: Set Tint");
                PrclSchema action = prclLibrary.get("Set Tint");
                result = processResponse(response, action);
                break;
            }

            case "Get Contrast": {
                System.out.println("    case: Get Contrast");
                PrclSchema action = prclLibrary.get("Get Contrast");
                result = processResponse(response, action);
                break;
            }

            case "Set Contrast": {
                System.out.println("    case: Set Contrast");
                PrclSchema action = prclLibrary.get("Set Contrast");
                result = processResponse(response, action);
                break;
            }

            case "Get Brightness": {
                System.out.println("    case: Get Brightness");
                PrclSchema action = prclLibrary.get("Get Brightness");
                result = processResponse(response, action);
                break;
            }

            case "Set Brightness": {
                System.out.println("    case: Set Brightness");
                PrclSchema action = prclLibrary.get("Set Brightness");
                result = processResponse(response, action);
                break;
            }

            case "Get Sharpness": {
                System.out.println("    case: Get Sharpness");
                PrclSchema action = prclLibrary.get("Get Sharpness");
                result = processResponse(response, action);
                break;
            }

            case "Set Sharpness": {
                System.out.println("    case: Set Sharpness");
                PrclSchema action = prclLibrary.get("Set Sharpness");
                result = processResponse(response, action);
                break;
            }

            case "Get Panel State": {
                System.out.println("    case: Get Panel State");
                PrclSchema action = prclLibrary.get("Get Panel State");
                result = processResponse(response, action);
                break;
            }

            case "Set Panel State": {
                System.out.println("    case: Set Panel State");
                PrclSchema action = prclLibrary.get("Set Panel State");
                result = processResponse(response, action);
                break;
            }

            case "Get Color Tone": {
                System.out.println("    case: Get Color Tone");
                PrclSchema action = prclLibrary.get("Get Color Tone");
                result = processResponse(response, action);
                break;
            }

            case "Set Color Tone": {
                System.out.println("    case: Set Color Tone");
                PrclSchema action = prclLibrary.get("Set Color Tone");
                result = processResponse(response, action);
                break;
            }

            default: {
                result = null; // Should not happen, included to make it obvious something went wrong if we end here
                debugPrint("WARNING: parseResponse() switch went to default (in Controller.PrclAdapter)");
            }
        }
        return result;
    }

    @Override
    public int getFullAckLength(String requestKey) {
        return BASE_ACK_LENGTH + prclLibrary.get(requestKey).getAck().getDataLength();
    }

    private int[] processRequest(int[] variableData, DataPair request) {
        int[] result = request.getDataValues();
        int[] varPositions = request.getVarPos();

        // set screen id
        result[varPositions[0]] = SCREEN_ID; // first position of a variable is the screen id
        // set other variables from parameters
        debugPrint("varPositions lenght: " + varPositions.length + " | varData length: " + variableData.length);
        for (int i = 1; i < varPositions.length-1; i++) { // skip the first (ID) & the last (checksum)
            result[varPositions[i]] = variableData[i];
        }
        // calc checksum
        result[result.length-1] = calcChecksum(result);
        return result;
    }

    private String[][] processResponse(int[] response, PrclSchema action) {
        // 2D String array package for data structure and converted response data
        String[][] ackData = new String[2][action.getAck().getDataValues().length]; // hard 2 slots for structure and data
        ackData[0] = action.getAck().getDataStructure();
        HashMap<String, HashMap<Integer, String>> definitions = action.getAckDefinitions();

        String[] result = new String[ackData[1].length];
        for (int i = 0; i < ackData[0].length; i++) {
            String key = ackData[0][i]; // Response structure
            int value = response[i];
            if (definitions.containsKey(key)) {
                if (definitions.get(key).size() == 1) { // if there is only one value (null, -1) means it is a range and we just want the int as string without conversion
                    result[i] = Integer.toString(value);
                } else { // Something if value is not found (eks. return hex value & "not found in definitions")
                    if (definitions.get(key).containsKey(value)) {
                        result[i] = definitions.get(key).get(value);
                    } else {
                        result[i] = "Error - Value not found in definitions (" + Integer.toHexString(response[i]) + ")";
                    }
                }
            } else {
                result[i] = Integer.toHexString(response[i]); // if key is not in definitions just do straight toHexString
            }
        }
        ackData[1] = result;

        return ackData;
    }

    // fall back method if we want to revert to just processing response to hex string
    // and not convert the values based on definitions
    private String[][] processResponseToHexStrings(int[] response, PrclSchema action) {
        // 2D String array package for data structure and converted response data
        String[][] ackData = new String[2][action.getAck().getDataValues().length]; // hard 2 slots for structure and data
        ackData[0] = action.getAck().getDataStructure();
        ackData[1] = intArrayToHexStringArray(response);

        return ackData;
    }


    private int calcChecksum(int[] data) {
        int checksum = 0;//commandType + id + dataLength + dataValue;
        for (int i = 1; i < data.length-1; i++) { // skip first and last (header and checksum positions)
            checksum += data[i];
        }

        // Specification for checksum is only the last to digits
        return checksum & 0xff; // Anding for the 8 least significant bits
    }


    /*
     *  DEBUG METHODS
     */

    public HashMap<String, PrclSchema> debugGetPrclLibrary() {
        return prclLibrary;
    }

}

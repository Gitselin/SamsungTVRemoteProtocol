package Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public abstract class Utility {

    private static boolean debugMode = true;

    /*
     *  Utility print methods
     */

    public static void debugPrint(String msg, boolean addSpacing) {
        if (debugMode) {
            System.out.println(msg);
            if (addSpacing) {
                System.out.println();
            }
        }
    }

    public static void debugPrint(String msg) {
        debugPrint(msg, false);
    }

    public static String printByteArray(byte[] bytes) {
        String s ="";
        for (byte b : bytes) {
            s += Byte.toString(b);
        }
        return s;
    }

    public static String toStringIntArray(int[] ints) {
        String s = "[";
        for (int i = 0; i < ints.length; i++) {
            if (i < ints.length-1) {
                s = s + ints[i] + ",";
            } else {
                s = s + ints[i] + "]";
            }
        }
        return s;
    }

    /*
     *  Utility conversion methods
     */

    // long to int (json-simple)
    public static int longToInt(long l) {
        Long x = new Long(l);

        return x.intValue();
    }

    public static int unsignedByteToInt(byte b) {
        //return Byte.toUnsignedInt(b);

        return b & 0xff; // Anding least significant 8 bits to get unsigned value
    }

    // byte array to int array using above
    public static int[] byteArrayToUnsignedIntArray(byte[] bytes) {
        int[] ints = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            ints[i] = unsignedByteToInt(bytes[i]);
        }
        return ints;
    }

    // Parse String hex to int
    public static int hexStringToInt(String hex) {
        debugPrint("As string: " + hex);
        if (hex.startsWith("0x")) {
            hex = hex.substring(2);
            debugPrint("Filtered String: " + hex);
        }
        return Integer.parseInt(hex, 16);
    }

    public static String[] jsonArrayToStringArray(JSONArray jArr) {
        String[] strArr = new String[jArr.size()];
        for (int i = 0; i < jArr.size(); i++) {
            strArr[i] = (String) jArr.get(i);
        }
        return strArr;
    }

    public static String[] intArrayToHexStringArray(int[] ints) {
        String[] str = new String[ints.length];
        for (int i = 0; i < ints.length; i++) {
            str[i] = Integer.toHexString(ints[i]);
        }
        return str;
    }


    /*
     *  JSON Utility
     */

    // Generic read from JSON
    // based on example: https://www.mkyong.com/java/json-simple-example-read-and-write-json/
    public static JSONObject readJsonFile(String filePath)
        throws ParseException, IOException { // deal with these in the other end
        JSONParser parser = new JSONParser();
        debugPrint("reading config from: " + filePath);
        Object obj = parser.parse(new FileReader(filePath));

        JSONObject jObj = (JSONObject) obj;

        return jObj;
    }
}

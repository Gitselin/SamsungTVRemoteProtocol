package Controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
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
        return Integer.parseInt(hex);
    }


    /*
     *  Misc Utility
     */

    // Generic read from JSON
    // based on example: https://www.mkyong.com/java/json-simple-example-read-and-write-json/
    public static JSONObject readJsonFile(String filePath)
        throws ParseException, IOException { // deal with these in the other end
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader(filePath));

        JSONObject jObj = (JSONObject) obj;

        return jObj;
    }
}

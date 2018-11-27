package Controller;

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

    // TODO - Generic read from JSON
}

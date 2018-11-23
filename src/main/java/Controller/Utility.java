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

    public static int unsignedByteToInt(byte b) {
        //return Byte.toUnsignedInt(b);

        return b & 0xFF; // Anding least significant 8 bits to get unsigned value
    }

    // TODO - byte array to int array using above
}

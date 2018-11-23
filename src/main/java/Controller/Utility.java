package Controller;

public abstract class Utility {

    private boolean debugMode = true;

    /*
     *  Utility print methods
     */

    public void debugPrint(String msg, boolean addSpacing) {
        System.out.println(msg);
        if (addSpacing) {
            System.out.println();
        }
    }

    public String printByteArray(byte[] bytes) {
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


}

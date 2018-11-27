package Protocol;

import java.util.HashMap;

public abstract class PrclInitializer {

    // load json protocol data and save to HashMap using "request key"
    // to pair with PrclFunction object
    public static HashMap<String, PrclFunction> loadJsonData(String configPath) {
        HashMap<String, PrclFunction> prclMap = new HashMap<>();
        String[] requestKeys = loadRequestKeyes();
    }

    public static String[] loadRequestKeyes() {
        // Read request keys from config and return
    }

    private static String[] loadConfigData() {
        // load json protocol filenames
    }

}

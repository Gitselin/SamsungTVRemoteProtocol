package Controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static Controller.Utility.readJsonFile;

public class Controller {
    private static final String IP;
    private static final String PORT;
    private static final int SCREEN_ID;
    private static final String[] LOAD_LIST;
    private static final String[] KEY_LIST;

    private static final String CONFIG_PATH = "Protocol/JSON/config.json"; // path for JSON-files


    public static void main(String[] args) {
        DbAdapter.createNewDatabase("Tester");
        DbAdapter.connect("Tester");
    }

    public static void loadConfig() {
        try {
            JSONObject config = readJsonFile(CONFIG_PATH);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

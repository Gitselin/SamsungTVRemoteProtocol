package Testing;

import Controller.Controller;
import static Controller.Utility.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;

public class MWTestDriver {
    private static boolean hasNewRequest;
    private static boolean keepRunning;
    private static final int SLEEP_TIME_MS = 10;
    private static final int ACTIVE_REFRESH_MS = 2000; // 2 sec
    private static final int INACTIVE_REFRESH_MS = 600000; // 10 min



    public static void main(String[] args) {
        hasNewRequest = true;
        keepRunning = true;

        try {
            Controller ctrl = new Controller();
            String[] keyList = ctrl.getKEY_LIST();

            String[][] response = ctrl.sendRequestNoVars(keyList[0]);

            debugPrint("Response: " + Arrays.deepToString(response[1]));
            /*
            while(keepRunning) { // infinite loop just for this test

                //Thread.sleep(SLEEP_TIME_MS);
            }
            // terminate actions
            // - DB logging finish up, etc.
            */
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

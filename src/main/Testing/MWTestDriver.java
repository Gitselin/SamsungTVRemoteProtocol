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
            String[] keyList = ctrl.getGET_KEY_LIST();
            //String keyToSend = keyList[2];
            int[] varData = {0xff, 0x01}; // Set Standby Setting - screen id & Standby Setting

            String keyToSend = "Set Standby Setting";

            debugPrint("Protocol function key: " + keyToSend);
            String[][] response = ctrl.sendRequest(keyToSend, varData);

            //debugPrint("Response: " + Arrays.deepToString(response[1]));
            System.out.println("Response: " + Arrays.deepToString(response[1]));
            /*
            while(keepRunning) { // infinite loop just for this test

                //Thread.sleep(SLEEP_TIME_MS);
            }
            // terminate actions
            // - DB logging finish up, etc.
            */
            Thread.sleep(4000);
            ctrl.closeNetConnection();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

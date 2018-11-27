package Testing;

public class MWTestDriver {
    private static boolean hasNewRequest;
    private static boolean keepRunning;
    private static final int SLEEP_TIME_MS = 10;
    private static final int ACTIVE_REFRESH_MS = 2000; // 2 sec
    private static final int INACTIVE_REFRESH_MS = 600000; // 10 min
    private static final String JSON_PATH = "Protocol/JSON"; // path for JSON-files

    public static void main(String[] args) {
        hasNewRequest = true;
        keepRunning = true;

        try {

            while(keepRunning) { // infinite loop just for this test
                /*
                if (hasNewRequest) {
                    sendData(intTest);
                    hasNewRequest = false;
                } else {
                    //hasNewRequest = false;
                    receiveData();
                }
                */
                Thread.sleep(10);
            }
            // terminate actions
            // - DB logging finish up, etc.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package Controller;

public interface iPtclAdapter {

    String[] getRequestKeys();

    boolean sendRequest(String requestKey); // boolean to indicate success

    // Something to get data ready for display - Maybe custom class for this purpose


}

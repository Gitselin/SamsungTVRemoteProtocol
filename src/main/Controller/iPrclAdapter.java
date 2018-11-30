package Controller;

public interface iPrclAdapter {

    String[] getRequestKeys();

    boolean sendRequest(String requestKey, int[] variableData); // boolean to indicate success

    // Something to get data ready for display - Maybe custom class for this purpose


}

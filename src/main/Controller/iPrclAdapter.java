package Controller;


public interface iPrclAdapter {


    int[] parseRequest(String requestKey, int[] variableData); // int array with byte values ready for NetConnector to send

    String[][] parseResponse(String requestKey, int[] response); // get int array response from NetConnector and parse data according to protocol definitions



}

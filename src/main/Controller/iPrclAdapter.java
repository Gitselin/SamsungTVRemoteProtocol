package Controller;

import Protocol.PrclSchema;

public interface iPrclAdapter {

    String[] getRequestKeys();

    int[] buildRequest(String requestKey, int[] variableData); // return object with structure data and actual response data



}

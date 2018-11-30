package Controller;

import Protocol.PrclSchema;

import java.util.HashMap;

public class PrclAdapter implements iPrclAdapter {
    private HashMap<String, PrclSchema> requests;


    public PrclAdapter() {
        // run PrclInitializer and build Prcl HashMap
        // requests = run initializer
    }

    @Override
    public String[] getRequestKeys() {

        return null; // TODO
    }

    @Override
    public boolean sendRequest(String requestKey, int[] variableData) {
        // TODO - Case Switch by requestKey (valid keys defined in config.json)
        // The switch should contain an entry for all implemented protocol requests
        switch (requestKey) {
            case "Get Request": {
                processGetStatus(variableData);
            }
        }

        return false;
    }

    private void processGetStatus(int[] variableData) {

    }



}

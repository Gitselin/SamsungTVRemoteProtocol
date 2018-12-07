package Protocol;

import java.util.HashMap;

public class PrclSchema {
    private DataPair getRequest;
    private DataPair setRequest;
    private DataPair ack;
    private DataPair nak;
    private String[] ackDefKeys;
    private HashMap<String, HashMap<Integer, String>> ackDefinitions; // for all the variable ack values have a hashmap to convert value to key

    public PrclSchema(DataPair getRequest, DataPair serRequest,DataPair ack, DataPair nak, HashMap<String, HashMap<Integer, String>> ackDefinitions, String[] ackDefKeys) {
        this.getRequest = getRequest;
        this.setRequest = serRequest;
        this.ack = ack;
        this.nak = nak;
        this.ackDefinitions = ackDefinitions;
        this.ackDefKeys = ackDefKeys;
    }



    public String getAckDefForByteValue(String key, int value) {
        return ackDefinitions.get(key).get(value);
    }

    public HashMap<String, HashMap<Integer, String>> getAckDefinitions() {
        return ackDefinitions;
    }

    public DataPair getGetRequest() {
        return getRequest;
    }

    public DataPair getSetRequest() { return setRequest; }

    public DataPair getAck() {
        return ack;
    }

    public String[] getAckDefKeys() {
        return ackDefKeys;
    }

    public String toString() {
        return getRequest.toString() + '\n' + setRequest.toString() + '\n' + ack.toString() + '\n' + nak.toString();
    }
}

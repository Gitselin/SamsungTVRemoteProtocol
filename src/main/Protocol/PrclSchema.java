package Protocol;

import java.util.ArrayList;
import java.util.HashMap;

public class PrclSchema {
    private DataPair request;
    private DataPair ack;
    private DataPair nak;
    private int[] requestVarPos; // array of positions for variable data that needs to be generated
    private int[] ackVarPos;
    private String[] ackDefKeys;
    private HashMap<String, HashMap<Integer, String>> ackDefinitions; // for all the variable ack values have a hashmap to convert value to key

    public PrclSchema(DataPair request, DataPair ack, DataPair nak, HashMap<String, HashMap<Integer, String>> ackDefinitions, String[] ackDefKeys) {
        this.request = request;
        this.ack = ack;
        this.nak = nak;
        requestVarPos = buildVarPositions(request.getDataValues());
        ackVarPos = buildVarPositions(ack.getDataValues());
        this.ackDefinitions = ackDefinitions;
        this.ackDefKeys = ackDefKeys;
    }

    // Build register of array positions for variable dataValues that we need to be able to set
    private int[] buildVarPositions(int[] dataValues) {
        ArrayList<Integer> buffer = new ArrayList<>();

        // Get index positions for values equal to -1 (signifying variable data field)
        // need variable size
        for (int i = 0; i < dataValues.length; i++) {
            if (dataValues[i] == -1) {
                buffer.add(i);
            }
        }

        // copy results to int array
        int[] ints = new int[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            ints[i] = buffer.get(i);
        }
        return ints;
    }

    public String getAckDefForByteValue(String key, int value) {
        return ackDefinitions.get(key).get(value);
    }

    public HashMap<String, HashMap<Integer, String>> getAckDefinitions() {
        return ackDefinitions;
    }

    public DataPair getRequest() {
        return request;
    }

    public int[] getRequestVarPos() {
        return requestVarPos;
    }

    public DataPair getAck() {
        return ack;
    }

    public String[] getAckDefKeys() {
        return ackDefKeys;
    }

    public int[] getAckVarPos() {
        return ackVarPos;
    }

    public String toString() {
        return request.toString() + '\n' + ack.toString() + '\n' + nak.toString();
    }
}

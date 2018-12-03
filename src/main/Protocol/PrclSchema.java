package Protocol;

import java.util.ArrayList;
import java.util.HashMap;

public class PrclSchema {
    private DataPair request;
    private DataPair ack;
    private DataPair nak;
    private int[] requestVarPos; // array of positions for variable data that needs to be generated
    private int[] ackVarPos;
    private ArrayList<HashMap<Integer, DataPair>> varEncodingKeys; // for all the variable ack values have a hashmap to convert value to key

    public PrclSchema(DataPair request, DataPair ack, DataPair nak) {
        this.request = request;
        this.ack = ack;
        this.nak = nak;
        requestVarPos = buildVarPositions(request.getDataValues());
        ackVarPos = buildVarPositions(ack.getDataValues());
        varEncodingKeys = new ArrayList<>();
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



    public DataPair getRequest() {
        return request;
    }

    public int[] getRequestVarPos() {
        return requestVarPos;
    }

    public DataPair getAck() {
        return ack;
    }

    public int[] getAckVarPos() {
        return ackVarPos;
    }

    public String toString() {
        return request.toString() + '\n' + ack.toString() + '\n' + nak.toString();
    }
}

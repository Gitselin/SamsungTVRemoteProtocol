package Protocol;

import java.util.ArrayList;
import java.util.HashMap;

public class ProtocolFunction {
    private DataPair request;
    private DataPair ack;
    private DataPair nak;
    private int[] requestVarPos; // array of positions for variable data that needs to be generated
    private ArrayList<HashMap<Integer, String>> varEncodingKeys; // for all the variable ack values have a hashmap to convert value to key

    public ProtocolFunction(DataPair request, DataPair ack, DataPair nak) {
        this.request = request;
        this.ack = ack;
        this.nak = nak;
        requestVarPos = buildRequestVarPos(request.getDataValues());
        varEncodingKeys = new ArrayList<>();
    }

    // Build register of array positions for variable dataValues that we need to be able to set
    private int[] buildRequestVarPos(int[] dataValues) {
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

    public int[] getRequestInts() {
        // TODO - Custom Exception - throw error if any datavalues are -1 (means that some data variables have not been set)
        int[] request = this.request.getDataValues();
        request[request.length-1] = calcChecksum(request);
        return request;
    }

    private int calcChecksum(int[] data) {
        int checksum = 0;//commandType + id + dataLength + dataValue;
        for (int i = 1; i < data.length-1; i++) { // skip first and last (header and checksum positions)
            checksum += data[i];
        }

        // Specification for check sum is only the last to digits
        return checksum & 0xff; // Anding for the 8 least significant bits
    }
}

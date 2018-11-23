package Protocol;

import java.util.ArrayList;

public class ProtocolFunction {
    private DataPair request;
    private DataPair ack;
    private DataPair nak;
    private int[] requestVarPos; // array of positions for variable data that needs to be generated

    public ProtocolFunction(DataPair request, DataPair ack, DataPair nak) {
        this.request = request;
        this.ack = ack;
        this.nak = nak;
        requestVarPos = buildRequestVarPos(request.getDataValues());
    }


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
}

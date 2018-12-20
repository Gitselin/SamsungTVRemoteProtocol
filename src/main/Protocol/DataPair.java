/*
 * Basic data structure for protocol modelling.
 * Contains Strings for data descriptor and key to get data when loaded from JSON,
 * along with ints for data values.
 * The two field array should always have the same length, pairing of date is
 * based on index position.
 */

package Protocol;

import java.util.ArrayList;
import java.util.Arrays;
import static Controller.Utility.toStringIntArray;

public class DataPair {
    private final String[] DATASTRUCTURE;
    private final int[] DATAVALUES;
    private final int[] VAR_POS; // array of positions for variable data that needs to be generated
    private final int DATALENGTH;

    public DataPair(String[] dataStructure, int[] dataValues) {
        // TODO - Throw error if not same length (custom exception)
        this.DATASTRUCTURE = dataStructure;
        this.DATAVALUES = dataValues;
        VAR_POS = buildVarPositions(DATAVALUES);
        int lengthPos = -1; // force OutOfBounds if not found
        if (DATAVALUES.length > 0) { // Enable construction for Protocol Functions that do not have either a Get or a Set request
            for (int i = 0; i < DATASTRUCTURE.length; i++) {
                if (DATASTRUCTURE[i].equals("data length")) {
                    lengthPos = i;
                    break;
                }
            }
            DATALENGTH = DATAVALUES[lengthPos];
        } else {
            DATALENGTH = -1;
        }

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


    public String[] getDataStructure() {
        return DATASTRUCTURE;
    }

    public int[] getDataValues() {
        return DATAVALUES;
    }

    public int[] getVarPos() { return VAR_POS; }

    public int getDataLength() {
        return DATALENGTH;
    }

    public String toString() {
        return Arrays.deepToString(DATASTRUCTURE) + '\n' + toStringIntArray(DATAVALUES) + '\n';
    }

}

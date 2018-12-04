/*
 * Basic data structure for protocol modelling.
 * Contains Strings for data descriptor and key to get data when loaded from JSON,
 * along with ints for data values.
 * The two field array should always have the same length, pairing of date is
 * based on index position.
 */

package Protocol;

import java.util.Arrays;
import static Controller.Utility.toStringIntArray;

public class DataPair {
    private final String[] DATASTRUCTURE;
    private final int[] DATAVALUES;
    private final int DATALENGTH;

    public DataPair(String[] dataStructure, int[] dataValues) {
        // TODO - Throw error if not same length (custom exception)
        this.DATASTRUCTURE = dataStructure;
        this.DATAVALUES = dataValues;
        int lengthPos = -1; // force OutOfBounds if not found
        for (int i = 0; i < DATASTRUCTURE.length; i++) {
            if (DATASTRUCTURE[i].equals("data length")) {
                lengthPos = i;
                break;
            }
        }
        DATALENGTH = DATAVALUES[lengthPos];
    }


    public String[] getDataStructure() {
        return DATASTRUCTURE;
    }

    public int[] getDataValues() {
        return DATAVALUES;
    }

    public int getDataLength() {
        return DATALENGTH;
    }

    public String toString() {
        return Arrays.deepToString(DATASTRUCTURE) + '\n' + toStringIntArray(DATAVALUES) + '\n';
    }



    /* OLD MATHODS _ MAYBE USEFULL LATER
    public String getDataType(int position) {
        return DATASTRUCTURE[position];
    }

    public int getDataValue(int position) {
        return DATAVALUES[position];
    }

    public void setDataValueByIndex(int index, int newValue) {
        DATAVALUES[index] = newValue;
    }


     */
}

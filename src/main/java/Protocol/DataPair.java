/*
 * Basic data structure for protocol modelling.
 * Contains Strings for data descriptor and key to get data when loaded from JSON,
 * along with ints for data values.
 * The two field array should always have the same length, pairing of date is
 * based on index position.
 */

package Protocol;

public class DataPair {
    private final String[] DATASTRUCTURE;
    private final int[] DATAVALUES;

    public DataPair(String[] dataStructure, int[] dataValues) {
        // TODO - Throw error if not same length (custom exception)
        this.DATASTRUCTURE = dataStructure;
        this.DATAVALUES = dataValues;
    }

    public String getDataType(int position) {
        return DATASTRUCTURE[position];
    }

    public String[] getDataStructure() {
        return DATASTRUCTURE;
    }

    public int getDataValue(int position) {
        return DATAVALUES[position];
    }

    public int[] getDataValues() {
        return DATAVALUES;
    }

    public void setDataValueByIndex(int index, int newValue) {
        DATAVALUES[index] = newValue;
    }
}

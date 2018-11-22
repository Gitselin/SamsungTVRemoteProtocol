package Protocol;

public class DataPair {
    private final String[] DATASTRUCTURE;
    private final int[] DATAVALUES;

    public DataPair(String[] dataStructure, int[] dataValues) {
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
}

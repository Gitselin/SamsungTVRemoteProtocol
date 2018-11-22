package Protocol;

public class DataPair {
    private final String[] dataStructure;
    private final int[] dataValues;

    public DataPair(String[] dataStructure, int[] dataValues) {
        this.dataStructure = dataStructure;
        this.dataValues = dataValues;
    }

    public String getDataType(int position) {
        return dataStructure[position];
    }

    public String[] getDataStructure() {
        return dataStructure;
    }

    public int getDataValue(int position) {
        return dataValues[position];
    }

    public int[] getDataValues() {
        return dataValues;
    }
}

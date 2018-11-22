package Model;

public class Screen {
    private int ID;
    private String name;
    private String model;
    private int stateUptime;
    private int stateTemp;
    private boolean stateOn;

    @Override
    public String toString() {
        return "Screen{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", stateOn=" + stateOn +
                ", stateUptime=" + stateUptime +
                ", stateTemp=" + stateTemp +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isStateOn() {
        return stateOn;
    }

    public void setStateOn(boolean stateOn) {
        this.stateOn = stateOn;
    }

    public int getStateUptime() {
        return stateUptime;
    }

    public void setStateUptime(int stateUptime) {
        this.stateUptime = stateUptime;
    }

    public int getStateTemp() {
        return stateTemp;
    }

    public void setStateTemp(int stateTemp) {
        this.stateTemp = stateTemp;
    }
}

package Controller;

public class PtclAdapter implements iPtclAdapter {

    // abstract? do we need to make instance
    // Or static methods?

    @Override
    public String[] getRequestKeys() {

        return null; // TODO
    }

    @Override
    public boolean sendRequest(String requestKey) {
        // TODO - Case Switch by requestKey (valid keys defined in config.json)
        return false;
    }



}

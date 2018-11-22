package Protocol;

public class ProtocolInitializer {

    // TODO - Hardcoded for now, data should be loaded from JSON
    private final String[] requestStructure = {
            "header",
            "command",
            "id",
            "data length",
            "checksum"
    };
    private final int[] requestValues = {
            0xAA,
            0x04,
            -1,
            0x0A,
            -1,
    };


    public ProtocolInitializer() {
        // TODO - Read from JSON and create DataPair objects
    }

}

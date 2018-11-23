package Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetConnector {

    private Socket socket;
    private DataInputStream netIn;
    private DataOutputStream netOut;


    public NetConnector(String hostIp, int port) {
        try {
            socket = new Socket(hostIp, port);
            Controller.Utility.debugPrint("Connection ready..", false);
            netIn = new DataInputStream(socket.getInputStream());
            netOut = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

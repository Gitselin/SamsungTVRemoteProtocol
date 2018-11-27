package Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static Controller.Utility.*;

public class NetConnector {

    private Socket socket;
    private DataInputStream netIn;
    private DataOutputStream netOut;


    public NetConnector(String hostIp, int port) {
        try {
            socket = new Socket(hostIp, port);
            debugPrint("Connection ready..", false);
            netIn = new DataInputStream(socket.getInputStream());
            netOut = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendData(int[] data) {
        try {
            for (int i : data) {
                netOut.writeByte(i);
                //debugPrint("Send data: " + i);
            }
            netOut.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] receiveData(int ackLength) {
        byte[] buffer = new byte[ackLength];

        try {
            //debugPrint("..Waiting to read data");
            int bytesRead = netIn.read(buffer, 0, ackLength);
            //debugPrint("received " + bytesRead + " bytes of data: " + printByteArray(buffer), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}

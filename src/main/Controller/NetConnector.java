package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import static Controller.Utility.*;

public class NetConnector {

    private Socket socket;
    private DataInputStream netIn;
    private DataOutputStream netOut;
    private final int READ_DELAY;


    public NetConnector(String hostIp, int port, int readDelay)
        throws IOException {
        socket = new Socket(hostIp, port);
        debugPrint("Connection ready..", false);
        netIn = new DataInputStream(socket.getInputStream());
        netOut = new DataOutputStream(socket.getOutputStream());
        READ_DELAY = readDelay;
    }


    public byte[] sendData(int[] fullData, int ackLength)
        throws IOException {
        //debugPrint("Sending: " + Arrays.deepToString(intArrayToHexStringArray(fullData)) + " with expected response length: " + ackLength);
        System.out.println("Sending: " + Arrays.deepToString(intArrayToHexStringArray(fullData)) + " with expected response length: " + ackLength);
        for (int i : fullData) {
            netOut.writeByte(i);
            //debugPrint("Send data: " + i);
        }
        netOut.flush();

        //Thread.sleep(READ_DELAY);

        return receiveData(ackLength);

    }

    public void close()
        throws IOException {
        netIn.close();
        netOut.close();
        socket.close();
    }

    private byte[] receiveData(int ackLength)
    throws IOException {
        byte[] buffer = new byte[ackLength];

        //debugPrint("..Waiting to read data");
        int bytesRead = netIn.read(buffer, 0, ackLength); // tricky should we used defined length and risk problems if we don't get what we expect or generic readbytes and risk "polluted" data
        //debugPrint("received " + bytesRead + " bytes of data: " + printByteArray(buffer), true);

        return buffer;
    }
}

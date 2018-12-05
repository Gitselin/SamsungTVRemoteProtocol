package Testing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import static Controller.Utility.intArrayToHexStringArray;

public class EchoServer {
    private static final String IP = "127.0.0.1";
    private static final int PORT = 1515;
    private static ServerSocket server;
    private static Socket client;

    private static DataInputStream netIn;
    private static DataOutputStream netOut;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(PORT);
            client = server.accept();

            netIn = new DataInputStream(client.getInputStream());
            netOut = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void sendData(byte[] fullData, int byteCount)
            throws IOException {
        for (int i = 0; i < byteCount; i++) {
            netOut.writeByte(i);
        }
        netOut.flush();

    }


    private void receiveData()
            throws IOException {
        byte[] buffer = new byte[256]; // 256 bytes should be enough

        int bytesRead = netIn.read(buffer); // tricky should we used defined length and risk problems if we don't get what we expect or generic readbytes and risk "polluted" data

        sendData(buffer, bytesRead);

    }

    public void close()
            throws IOException {
        netIn.close();
        netOut.close();
        client.close();
        server.close();
    }

}

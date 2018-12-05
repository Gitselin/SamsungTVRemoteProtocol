package Testing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {
    private static final int PORT = 1515;
    private static ServerSocket server;
    private static Socket client;

    private static DataInputStream netIn;
    private static DataOutputStream netOut;
    private static boolean running;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(PORT);
            connect();

            while (running) {
                if (client.isConnected()) {
                    receiveData();
                    Thread.sleep(10);
                } else {
                    connect();
                }
            }
            close();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void sendData(byte[] fullData, int byteCount)
            throws IOException {
        for (int i = 0; i < byteCount; i++) {
            netOut.writeByte(i);
        }
        netOut.flush();

    }


    private static void receiveData()
            throws IOException {
        byte[] buffer = new byte[256]; // 256 bytes should be enough

        int bytesRead = netIn.read(buffer); // tricky should we used defined length and risk problems if we don't get what we expect or generic readbytes and risk "polluted" data

        sendData(buffer, bytesRead);

    }

    public static void connect()
        throws IOException {
        client = server.accept();

        netIn = new DataInputStream(client.getInputStream());
        netOut = new DataOutputStream(client.getOutputStream());
        running = true;
    }

    public static void close()
            throws IOException {
        netIn.close();
        netOut.close();
        client.close();
        server.close();
    }

}

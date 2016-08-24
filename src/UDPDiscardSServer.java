import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import javax.swing.*;

public class UDPDiscardSServer extends Thread {
    int mPort;
    JTextArea mTextArea = null;
    byte[] mBuffer = new byte[1024];

    @Override
    public void run() {
        DatagramSocket server = null;
        try {
            server = new DatagramSocket(mPort);
            DatagramPacket packet = new DatagramPacket(mBuffer, mBuffer.length);
            while (true) {
                try {
                    server.receive(packet);
                    String s = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
                    System.out.println("address : " + packet.getAddress().getHostAddress() + ", port : " + packet.getPort() + ", content : " + s);

                    DatagramSocket theSocket = new DatagramSocket();
                    String sendData = "Word";
                    DatagramPacket theOutput = new DatagramPacket(sendData.getBytes(), sendData.length(), packet.getAddress(), packet.getPort());
                    theSocket.send(theOutput);
                    if (theSocket != null)
                        theSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } finally {
            if (server != null)
                server.close();
        }
    }

    public UDPDiscardSServer(int port, JTextArea textArea) {
        mPort = port;
        mTextArea = textArea;


    }
}

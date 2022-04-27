import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MulticastSender {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String ip = "239.0.1.2";
    InetAddress iadr = InetAddress.getByName(ip);
    int port = 55555;
    MulticastSocket socket = new MulticastSocket(port);

    DatagramPacket packet;
    byte[] data;

    String message;

    public MulticastSender() throws UnknownHostException, SocketException, IOException {

        }

        public void MulticastSender(String message) throws IOException {
            System.out.println(message);
            data = message.getBytes();
            packet = new DatagramPacket(data, data.length, iadr, port);
            socket.send(packet);
        }

    public void close() {
        socket.close();
    }
}
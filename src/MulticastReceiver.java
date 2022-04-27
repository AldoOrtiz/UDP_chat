import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;

public class MulticastReceiver extends Thread {

    MulticastSocket socket;
    int port = 55555;

    String ip = "239.0.1.2";
    InetAddress iadr = InetAddress.getByName(ip);

    InetSocketAddress group = new InetSocketAddress(iadr, port);
    NetworkInterface netIf = NetworkInterface.getByName("en0");

    public MulticastReceiver () throws SocketException, IOException {

        socket = new MulticastSocket(port);
        socket.joinGroup(group, netIf);


    }

    public String MulticastReceiv () throws  IOException{

        while (true) {

        }
    }

    public String getMessage() throws IOException {
        byte[] data = new byte[256];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        socket.receive(packet);
        System.out.println("Meddelande från " + packet.getAddress().getHostAddress() + " " + LocalDateTime.now());
        String message = new String(packet.getData(), 0, packet.getLength());
        return message;
    }

    @Override
    public void run(){
        while (true){
            try {
                GUI.area.append(getMessage() + "\n");
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void disconnected() {
        try {
            socket.leaveGroup(group, netIf);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
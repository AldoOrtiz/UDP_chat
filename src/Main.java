import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MulticastReceiver mr = new MulticastReceiver();
        mr.start();
        MulticastSender ms = new MulticastSender();
        ms.MulticastSender("CONNECTED");
        //System.out.println(mr.MulticastReceiver());

        GUI g = new GUI(ms,mr);

    }
}

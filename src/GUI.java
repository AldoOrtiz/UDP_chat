import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {

    public static JTextArea area = new JTextArea(20,30);
    JTextField t = new JTextField(30);
    JTextField name = new JTextField("ALDO", 10);
    MulticastSender ms;
    MulticastReceiver mr;
    JButton button1 = new JButton(" Close ");

    public GUI(MulticastSender ms, MulticastReceiver mr){
        this.ms = ms;
        this.mr = mr;
        name.addActionListener(this);
        button1.addActionListener(this);
        setTitle("KlassChatt");

        JPanel p = new JPanel();

        this.add(p);
        p.add(area);
        p.add(button1);


        p.add(t);
        String txt = t.getText();
        t.addActionListener(this);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == t){
            System.out.println(t.getText());
            try {
                ms.MulticastSender(name.getText() + ": " + t.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            t.setText("");
        } else if (e.getSource() == button1) {
            System.out.println("----");
            mr.interrupt();
            mr.disconnected();
            ms.close();
            t.setEditable(false);
            t.removeActionListener(this);
        }
    }
}

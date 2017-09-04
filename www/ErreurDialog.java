import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marwen on 12/26/16.
 */
public class ErreurDialog extends JDialog implements ActionListener {
    public ErreurDialog(JFrame f,Gui g,String s){
        super(f);
        setModal(true);
        setSize(new Dimension(300, 100));
        setResizable(false);

        JLabel l = new JLabel(s);

        JButton ok = new JButton("OK");
        ok.addActionListener(this);

        JPanel c = new JPanel(new FlowLayout(FlowLayout.CENTER));
        c.add(l);
        c.add(ok);
        this.setContentPane(c);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}

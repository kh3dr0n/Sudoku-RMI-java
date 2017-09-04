import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marwen on 12/26/16.
 */
public class FinishDialog extends JDialog implements ActionListener{

    public FinishDialog(JFrame f, Gui g){
        super(f);
        setModal(true);
        setSize(new Dimension(250, 150));
        setResizable(false);

        JLabel l = new JLabel("Facilitation ! bien joué");

        JButton exit = new JButton("Exit");
        exit.addActionListener(this);
        exit.addActionListener(g);
        exit.setActionCommand("exit");


        JButton newGame = new JButton("Rejouer");
        newGame.addActionListener(this);
        newGame.addActionListener(g);
        newGame.setActionCommand("newGame");



        JPanel c = new JPanel(new GridLayout(3,1));
        c.add(l);
        c.add(newGame);
        c.add(exit);
        this.setContentPane(c);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}

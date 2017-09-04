import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marwen on 12/25/16.
 */
public class Level extends JDialog implements ActionListener {

    public Level(JFrame f,Gui gui){
        super(f);
        setModal(true);
        setSize(new Dimension(100, 120));
        setResizable(false);
        JButton easy = new JButton("Easy");
        easy.addActionListener(this);
        easy.addActionListener(gui);
        easy.setActionCommand("start-easy");


        JButton medium = new JButton("Medium");
        medium.addActionListener(this);
        medium.addActionListener(gui);
        medium.setActionCommand("start-medium");

        JButton hard = new JButton("Hard");
        hard.addActionListener(this);
        hard.addActionListener(gui);
        hard.setActionCommand("start-hard");


        JPanel c = new JPanel(new GridLayout(3,1));
        c.add(easy);
        c.add(medium);
        c.add(hard);
        this.setContentPane(c);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marwen on 12/25/16.
 */
public class NumberSelect extends JDialog implements ActionListener {
    public NumberSelect(JFrame f,Gui gui, int l, int k) {
        super(f);
        setModal(true);
        setSize(new Dimension(100, 100));

        GridLayout grid = new GridLayout(3, 3);
        grid.setHgap(0);
        grid.setVgap(0);

        JPanel p = new JPanel();
        p.setLayout(grid);
        JButton[] btn = new JButton[9];
        for (int i = 0; i < 9; i++) {
            btn[i] = new JButton(Integer.toString(i + 1));
            btn[i].addActionListener(gui);
            btn[i].addActionListener(this);
            btn[i].setActionCommand("set-" + k + "-" + l + "-" + (i + 1));
            p.add(btn[i]);
        }

        this.setLocationRelativeTo(null);
        this.setContentPane(p);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }

}

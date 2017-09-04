import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Marwen on 12/20/16.
 */
public class Gui extends UnicastRemoteObject implements ActionListener, ClientIntefrace {
    private JButton btn[][];
    private JPanel grps[][];
    private NumberSelect modal;
    private SudokuInterface s;
    private JFrame gui;

    public Gui(SudokuInterface s) throws RemoteException{
        super();
        try {
                this.s = s;
                s.setCallback(this);
                initGui();
                new Level(gui,this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initGui(){

        try {
            gui = new JFrame();
            gui.setSize(new Dimension(500, 400));

            GridLayout grid = new GridLayout(3, 3);
            grid.setHgap(0);
            grid.setVgap(0);

            JPanel p = new JPanel();
            p.setLayout(grid);
            btn = new JButton[9][9];
            grps = new JPanel[3][3];
            p.setPreferredSize(new Dimension(400, 400));
            p.setMinimumSize(new Dimension(400, 400));
            p.setMaximumSize(new Dimension(400, 400));
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    grps[i][j] = new JPanel(grid);
                    grps[i][j].setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
                    p.add(grps[i][j]);
                }
            }

            int g[][] = s.getGrid();


            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = i * 3; k < (i + 1) * 3; k++) {
                        for (int l = j * 3; l < (j + 1) * 3; l++) {
                            btn[k][l] = new JButton(" ");
                            btn[k][l].setOpaque(true);
                            btn[k][l].setBackground(Color.white);
                            btn[k][l].setFont(new Font("Dialog", Font.BOLD, 12));
                            btn[k][l].setBorder((BorderFactory.createStrokeBorder(new BasicStroke(0.5f))));
                            grps[i][j].add(btn[k][l]);
                        }
                    }
                }
            }

            JPanel status = new JPanel();
            status.setPreferredSize(new Dimension(100, 400));
            status.setMinimumSize(new Dimension(100, 400));
            status.setMaximumSize(new Dimension(100, 400));

            status.setLayout(new FlowLayout(FlowLayout.CENTER));
            JButton newGame = new JButton("New Game");
            newGame.addActionListener(this);
            newGame.setActionCommand("newGame");
            status.add(newGame);


            JButton exit = new JButton("Exit");
            exit.addActionListener(this);
            exit.setActionCommand("exit");
            status.add(exit);

            gui.setLayout(new BorderLayout());
            gui.add(p, BorderLayout.LINE_START);
            gui.add(status, BorderLayout.LINE_END);
            gui.setLocationRelativeTo(null);
            //gui.setContentPane(p);

            gui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            WindowListener exitListener = new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        s.exit();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                    System.exit(0);
                }
            };
            gui.addWindowListener(exitListener);

            gui.setVisible(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initGame(String level) throws RemoteException{
        try {
            switch (level){
                case "easy":
                    s.newGrid(40);
                    break;
                case "medium":
                    s.newGrid(50);
                    break;
                case "hard":
                    s.newGrid(54);
                    break;
            }

            int g[][] = s.getGrid();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = i * 3; k < (i + 1) * 3; k++) {
                        for (int l = j * 3; l < (j + 1) * 3; l++) {


                            if (g[k][l] != 0) {
                                btn[k][l].setText(Integer.toString(g[k][l]));
                                btn[k][l].setBackground(new Color(174, 216, 254));
                            } else {
                                btn[k][l].setBackground(Color.white);
                                btn[k][l].setText(" ");
                                btn[k][l].addActionListener(this);
                                btn[k][l].setActionCommand("number-" + k + "-" + l);
                            }

                        }
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGrid(int[][] g) throws RemoteException{
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = i * 3; k < (i + 1) * 3; k++) {
                    for (int l = j * 3; l < (j + 1) * 3; l++) {
                        if (g[k][l] != 0) {
                            btn[k][l].setText(Integer.toString(g[k][l]));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void showError(String s) throws RemoteException{
        Gui this1 = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ErreurDialog(gui,this1,s);
            }
        });


    }

    @Override
    public void showFin() throws RemoteException{
        Gui this1 = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinishDialog(gui,this1);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        String[] buff = e.getActionCommand().split("-");

        switch (buff[0]) {
            case "number":
                this.modal = new NumberSelect(gui,this, Integer.parseInt(buff[1]), Integer.parseInt(buff[2]));
                break;
            case "set":
                try {
                    s.add(Integer.parseInt(buff[2]), Integer.parseInt(buff[1]), Integer.parseInt(buff[3]));
                    //updateGrid();
                } catch (RemoteException ex) {
                    System.out.println("Error");
                }
                break;
            case "newGame":
                new Level(gui,this);
                break;
            case "start":
                try {
                    initGame(buff[1]);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            case "exit":
                gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
        }


    }

    
}

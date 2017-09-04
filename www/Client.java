//import javax.swing.*;
//import java.awt.*;
import java.rmi.*;
import java.rmi.registry.*;


public class Client {
    public Client(String[] args) {
        try {


            if (System.getSecurityManager() == null)
                System.setSecurityManager(new RMISecurityManager());
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            FabricSudokuInterface fabrique = (FabricSudokuInterface) reg.lookup("Fabrique");

            SudokuInterface sudoku = (SudokuInterface) fabrique.newSudoku();
            if(sudoku == null){
                System.out.println("Seveur saturé, ressayer dans quelques minutes...");
//                JFrame f = new JFrame();
//                f.setSize(new Dimension(500, 400));
//                f.setLayout(new BoxLayout(null, BoxLayout.Y_AXIS));
//                f.add(new Label("Seveur saturé, ressayer dans quelques minutes ..."));
//                //JButton replay = new JButton("Ressayer");
//                //replay.setActionCommand("retry");
//                //replay.addActionListener(this);
//                //f.add(replay);
//                f.setLocationRelativeTo(null);
//                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                f.setVisible(true);
            }else{
                new Gui(sudoku);
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Probleme au niveau de l'appel distant..");
        }
    }
}


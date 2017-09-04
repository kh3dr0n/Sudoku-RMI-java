import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Marwen on 12/30/16.
 */
public class Middle extends UnicastRemoteObject implements SudokuInterface,Serializable {
    private Sudoku s;
    public Middle(Sudoku s) throws RemoteException {
        this.s = s;

    }

    @Override
    public void setCallback(ClientIntefrace g) throws RemoteException {
        s.setCallback(g);
    }

    @Override
    public int[][] getGrid() throws RemoteException {
        return s.getGrid();
    }

    @Override
    public int[][] newGrid(int holes) throws RemoteException {
        return s.newGrid(holes);
    }

    @Override
    public boolean canAdd(int x, int i, int j) throws RemoteException {
        return s.canAdd(x,i,j);
    }

    @Override
    public boolean finish() throws RemoteException {
        return s.finish();
    }

    @Override
    public void add(int i, int j, int x) throws RemoteException {
        s.add(i,j,x);
    }

    @Override
    public void exit() throws RemoteException {
        s.interrupt();
    }
}

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Marwen on 12/25/16.
 */
public interface SudokuInterface extends Remote,Serializable{
    void setCallback(ClientIntefrace g) throws RemoteException;

    int[][] getGrid() throws RemoteException;

    int[][] newGrid(int holes) throws RemoteException;

    boolean canAdd(int x, int i, int j) throws RemoteException;

    boolean finish() throws RemoteException;

    void add(int i, int j, int x) throws RemoteException;

    void exit() throws  RemoteException;
}

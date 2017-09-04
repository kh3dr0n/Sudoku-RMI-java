import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Marwen on 12/25/16.
 */
public interface ClientIntefrace extends Remote {
    void initGame(String level) throws RemoteException;

    void updateGrid(int[][] g) throws RemoteException;

    void showError(String s)throws RemoteException;

    void showFin()throws RemoteException;
}

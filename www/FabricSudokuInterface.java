import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Marwen on 12/25/16.
 */
public interface FabricSudokuInterface extends Remote, Serializable {
    SudokuInterface newSudoku() throws RemoteException;
}

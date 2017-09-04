import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Marwen on 12/25/16.
 */
public class FabricSudoku extends UnicastRemoteObject implements FabricSudokuInterface {
    ThreadGroup threadGroup;
    public FabricSudoku() throws RemoteException {
        threadGroup = new ThreadGroup("sudoku");
    }

    @Override
    public SudokuInterface newSudoku() throws RemoteException {
        if(threadGroup.activeCount() < 10){
            Sudoku s = new Sudoku(threadGroup);
            s.start();
            return (new Middle(s));
        }else
            return null;

    }
}

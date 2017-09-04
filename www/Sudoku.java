import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.rmi.*;

/**
 * Created by Marwen on 12/21/16.
 */
public class Sudoku extends Thread{

    private int[][] grid;
    private int[][] solution;
    private ClientIntefrace client;
    int holes;


    public Sudoku(){

    }
    public Sudoku(ThreadGroup tg){
        super(tg,"sudoku");


    }

    public void run(){
        try {
            while(true) {
                sleep(50);

                if (Thread.interrupted()) {
                    return;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void setCallback(ClientIntefrace g){
        client = g;
    }

    public int[][] getGrid(){
        if (grid == null) {
            newGrid(holes);
        }
        return grid;
    }

    public int[][] newGrid(int holes){
        grid = new int[9][9];
        solution = new int[9][9];
        this.holes = holes;
        Random r = new Random();
        int i1 = r.nextInt(8);
        int firstval = i1;
        int x = i1, v = 1;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((x + j + v) <= 9)
                    solution[i][j] = j + x + v;
                else
                    solution[i][j] = j + x + v - 9;
                if (solution[i][j] == 10)
                    solution[i][j] = 1;
            }
            x += 3;
            if (x >= 9)
                x = x - 9;
            if (i == 2) {
                v = 2;
                x = firstval;
            }
            if (i == 5) {
                v = 3;
                x = firstval;
            }

        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = solution[i][j];
            }
        }
        makeHoles(holes);


        return grid;

    }


    public boolean isValid(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != solution[i][j])
                    return false;
            }
        }
        return true;
    }

    public boolean canAdd(int x, int i, int j){
        for (int k = 0; k < 9; k++) {
            if (grid[i][k] == x) {
                return false;
            }

        }

        for (int k = 0; k < 9; k++) {
            if (grid[k][j] == x) {
                return false;
            }

        }

        for (int k = (i / 3) * 3; k < ((i / 3) + 1) * 3; k++) {
            for (int l = (j / 3) * 3; l < ((j / 3) + 1) * 3; l++) {
                if (grid[k][l] == x) {
                    return false;
                }

            }
        }
        return true;
    }

    public boolean finish(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (solution[i][j] != grid[i][j])
                    return false;
            }
        }
        return true;
    }

    public void add(int i, int j, int x){
        try {
            if (canAdd(x, i, j)) {
                grid[i][j] = x;
                client.updateGrid(grid);
                if (finish())
                    client.showFin();
            } else {
                client.showError("Impossible d'ajouter " + x + " dans case :" + (i + 1) + "," + (j + 1));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void makeHoles(int holesToMake){
        double remainingSquares = 81;
        double remainingHoles = (double) holesToMake;

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                double holeChance = remainingHoles / remainingSquares;
                if (Math.random() <= holeChance) {
                    grid[i][j] = 0;
                    remainingHoles--;
                }
                remainingSquares--;
            }
    }

}

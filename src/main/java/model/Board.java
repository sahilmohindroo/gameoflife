package main.java.model;

public class Board {

    private int[][] matrix;

    /**
     * matrix can have 4 values each of which represents a set of old and new values before and after tick;
     * 0 - dead before, dead after
     * 1 live before, live after
     * 2 live before, dead after
     * 3 dead before live after
     *
     */
    synchronized public void tick(){
        int maxy = matrix.length;
        int maxx = matrix[0].length;

        // create a temporary state of all 4 possible values
        for (int i=0; i < maxy; i++){
            for (int j=0;j<maxx;j++){
                int c = countLiveNeighbours(j,i,maxx,maxy);
                if (matrix[i][j] == 1){
                    if (c < 2 || c > 3){
                        matrix[i][j] = 2;
                    }
                } else if (matrix[i][j]== 0 && c == 3){
                    matrix[i][j] = 3;
                }
            }
        }

        //
        for (int i=0; i < maxy; i++){
            for (int j=0;j<maxx;j++){
                if (matrix[i][j] == 3){
                    matrix[i][j] = 1;
                } else if (matrix[i][j]== 2){
                    matrix[i][j] = 0;
                }
            }
        }

    }

    private int countLiveNeighbours(int x, int y, int maxx, int maxy) {
        int c = 0;
        if (validAndLive(x-1,y-1, maxx, maxy)) c++;
        if (validAndLive(x-1,y, maxx, maxy)) c++;
        if (validAndLive(x-1,y+1, maxx, maxy)) c++;
        if (validAndLive(x,y-1, maxx, maxy)) c++;
        if (validAndLive(x,y+1, maxx, maxy)) c++;
        if (validAndLive(x+1,y-1, maxx, maxy)) c++;
        if (validAndLive(x+1,y, maxx, maxy)) c++;
        if (validAndLive(x+1,y+1, maxx, maxy)) c++;

        return c;
    }

    private boolean validAndLive(int x, int y, int maxx, int maxy) {
        if (x >= 0 && y >= 0
            && x < maxx && y < maxy
            && (matrix[y][x] == 1 || matrix[y][x] == 2)){
            return true;
        }
        return false;
    }

    public Board(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix[0].length];
        for (int i=0; i < matrix.length; i++){
            for (int j=0;j<matrix[0].length;j++){
                try{
                    this.matrix[i][j] = matrix[i][j];
                } catch (IndexOutOfBoundsException e){
                    System.out.println("invalid input");
                }
            }
        }
    }


    public int[][] getMatrix() {
        return matrix;
    }


}

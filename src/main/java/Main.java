package main.java;

import main.java.model.Board;

import java.util.Arrays;

public class Main {
    static int noOfTicks =3;

    static int[][] inputMatrix =  {
            {0,1,0},
            {0,0,1},
            {1,1,1},
            {0,0,0}
    };

    public static void main(String[] args) {

        Board board = new Board(inputMatrix);
        printBoard(board, 0);

        for (int i = 0 ; i< noOfTicks; i++){
            board.tick();
            printBoard(board, i+1);
        }
    }

    private static void printBoard(Board board, int tick) {
        System.out.println("tick - " + tick);
        for (int i=0; i < board.getMatrix().length; i++){
            System.out.println(Arrays.toString(board.getMatrix()[i]));
        }

    }

}
package Sudoku.Test;

import Sudoku.Mechanics;

public class TestMixer {

    int colomm,line;
    final int SIZE = 9;

    public static void main(String[] args) {
        TestMixer test = new TestMixer();
        Mechanics mechanics = new Mechanics();
        //test.random();

        int[][]matrix = new int[test.SIZE][test.SIZE];
        int[] matrixSec = new int[test.SIZE+1];

        //matrixSec = test.doubleInit(matrixSec);
        //test.printMatrix(matrixSec);

        matrix = test.setMatrix(matrix, "LINES");
        test.printMatrix(matrix);
        //matrix = mechanics.mix(Type.LINES, test.SIZE, matrix);
        //test.printMatrix(matrix);
        //matrix = test.setMatrix(matrix, Type.COLLOMS);
        //test.printMatrix(matrix);
        //matrix = mechanics.mix(Type.COLLOMS, test.SIZE, matrix);
        //test.printMatrix(matrix);

    }

    private void random(){
        int[] list = new int[9];
        boolean repeat=true;
        int a;

        for (int i =0 ; i < list.length ; i++){
            list[i] = -1;
        }

        for (int i =0 ; i < 3 ; i++){
            for (int j=0 ; j < 3 ; j++) {

                do {
                    a = (int) (3 * i + Math.random( ) * 3);

                    for (int b = 0; b < list.length; b++) {
                        if (list[b] == a) {
                            repeat = true;
                        } else repeat = false;

                    }
                }while (repeat);

                for (int b = 0; b < list.length; b++) {
                    if (list[b] == -1) {
                        list[b] = a;
                    }
                }

                System.out.println(a);
            }
        }
    }

    private void setColomm(int colomm){
        this.colomm = colomm;
    }
    private void setLine(int line){
        this.line = line;
    }

    public int getColomm(){
        return colomm;
    }

    public int getLine(){
        return line;
    }

    private int[][] setMatrix(int[][] matrix, String type){

        switch (type) {//определение типа, того что будет меняться если линии то x=0, если столбцы то x=1 и т.д.
            case "LINES":
                setColomm(0);
                setLine(1);
                break;
            case "COLLOMS":
                setColomm(1);
                setLine(0);
                break;
        }

        for (int i =0 ; i < matrix.length ; i++){
            for (int j=0 ; j < matrix.length ; j++) {
                matrix[i][j] = i * getLine() + j *getColomm() +1;
            }
        }

     return matrix;
    }

    private void printMatrix(int[][] matrix){
        for (int i =0 ; i < matrix.length ; i++){
            for (int j=0 ; j < matrix.length ; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    private int[] doubleInit (int[] matrix){
        if (matrix.length%2 == 0){
            for (int i =0 ; i < matrix.length ; i += 2){
                System.out.println(" i = " + i);
                matrix[i] = i+1;
                matrix[i + 1] = (i+1) *100;
            }
        }
        return matrix;
    }
    private void printMatrix(int[] matrix){
        for (int i =0 ; i < matrix.length ; i++){
                System.out.print(matrix[i] + " ");
        }
        System.out.println();
    }

}

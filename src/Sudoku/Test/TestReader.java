package Sudoku.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestReader {
    public static void main(String[] args) {
        final int FIELD_SIZE = 9;
        final String FILE_NAME = "GameConfig.txt";
        int [][] matrix = new int[FIELD_SIZE][FIELD_SIZE];
        int x, y;
        //countZeroCells = 0;
        String stringOut = "";
        boolean saved = false;



        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            for (x = 0; x < FIELD_SIZE; x++) {// заполняет массив
                stringOut = br.readLine();
                System.out.println(stringOut);
                System.out.println(stringOut.length());
                for (y = 0; y < FIELD_SIZE; y++) {
                    char c = stringOut.charAt(y);
                    System.out.println("c = " + c);
                    System.out.println("--" );
                    //int i = c;
                    int i = Integer.parseInt(String.valueOf(c));
                    System.out.println("i = "+ i );
                    matrix[y][x] = i;
                }
            }
            //saved = true;
        } catch(IOException exc) {
            System.out.println("Ошибка ввода-вывода: " + exc);
            saved = false;
        }

        for (x = 0; x < FIELD_SIZE; x++){
            for (y = 0; y < FIELD_SIZE; y++){
                System.out.print(matrix[y][x] + " ");
            }
            System.out.println( );
        }
    }
}

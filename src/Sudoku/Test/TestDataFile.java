package Sudoku.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestDataFile {

    public static void main(String[] args) {
        int i;
        FileInputStream fin=null;
        FileOutputStream fout=null;

        // Проверка, передается ли программе имя файла.
        if (args.length != 2){
            System.out.println("Использование файла!");
            return;
        }

        try {
            fin = new FileInputStream("TEXT1.txt");// Открыть файл.
            fout = new FileOutputStream("TEXT2.txt");
            do{// Читать файл и переписать файл.
                i = fin.read();
                if (i != -1) fout.write(i);
            }while ((i != -1));
        } catch (IOException exc){
            System.out.println("Error:" +exc );
        }
        finally {
            try{
                if (fin != null) fin.close();// Закрыть файл.
            }catch (IOException exc){
                System.out.println("Error: "+exc );
            }
            try{
                if (fout != null) fout.close();// Закрыть файл.
            }catch (IOException exc){
                System.out.println("Error: "+exc );
            }
        }
    }
}

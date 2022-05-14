package Sudoku.Test;

import java.io.*;

public class RWData {
    public static void main(String[] args) {
        int i = 10;
        double d = 1023.56;
        boolean b = true;

        try(DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("Testdata"))){
            System.out.println("Зaпиcaнo: " + i);
            dataOut.writeInt(i);
            System.out.println ("Записано: " + d);
            dataOut.writeDouble(d);
            System.out.println("Зaпиcaнo: " + b );
            dataOut.writeBoolean(b);
            System.out.println("Зaпиcaнo: " + 12.2 * 7.4);
            dataOut.writeDouble(12.5 + 1.23);
        } catch (FileNotFoundException e) {
            e.printStackTrace( );
        } catch (IOException e) {
            e.printStackTrace( );
        }

        try(DataInputStream dataIn = new DataInputStream(new FileInputStream("Testdata"))){
            i = dataIn.readInt();
            System.out.println("Прочитано: " + i);
            d = dataIn.readDouble();
            System.out.println ("Прочитано: " + d);
            b = dataIn.readBoolean();
            System.out.println("Прочитано: " + b );
            d = dataIn.readDouble();
            System.out.println("Прочитано: " + d);
        } catch (FileNotFoundException e) {
            e.printStackTrace( );
        } catch (IOException e) {
            e.printStackTrace( );
        }

    }
}

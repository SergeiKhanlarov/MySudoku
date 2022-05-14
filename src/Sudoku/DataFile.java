/**
 * Java. DataFile
 *
 * данный клас сслужит для записи данных об игре в файл
 * данные записывабтся построчно - одна строка - одна ячейка.
 * число/заморожена или нет/цвет цифры/цвет ячейки/есть ли одинаковые
 * count/frozen/colorText/color/equal
 * значение варируется от 0 до 9, цвет ячецки - 0, 2 или 4, остальные значения равны 0 или 1,
 * что аналогично true или false
 *
 * @author Sergey
 * @version 0.1.1 dated November 2, 2020
 */
package Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataFile {

    final String FILE_NAME = ".\\resources\\GameConfig.txt";

    public void writeData (Cell[][] gameField, int FIELD_SIZE){

        String string = "";
        String stringOut;

        try(FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            for (int x=0; x < FIELD_SIZE; x++){
                for (int y=0; y < FIELD_SIZE; y++){
                    string = String.valueOf(gameField[y][x].getCount());//Записать значение ячейки
                    if (gameField[y][x].getFrozen()){//записать заморожена она или нет.
                        string = string + "1";//заморожена
                    }else string = string + "0";//нет

                    if (gameField[y][x].getColorText()){//Записать цвет цифры
                        string = string + "1";//цвет ячейки красный
                    }else string = string + "0";//цвет ячейки черный

                    string = string + gameField[y][x].getColorType();//записать цвет ячейки.

                    if (gameField[y][x].getEqual()){//записать есть ли одинаковые.
                        string = string + "1";//Одинаковые есть
                    }else string = string + "0";//одниковых нет.

                    if (gameField[y][x].getNullify()){//записать обнулена ли ячейка
                        string = string + "1";//обнулена
                    }else string = string + "0";//нет.

                    fileWriter.write(string);
                    string = "\n";
                    fileWriter.write(string);
                    //string = "\r";
                    //fileWriter.write(string);
                }
                //string = "\n";
                //fileWriter.write(string);
            }


        } catch (IOException e) {
            e.printStackTrace( );
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            while((stringOut = br.readLine()) != null){
                System.out.println(stringOut);
            }
        } catch(IOException exc) {
            System.out.println("Ошибка ввода-вывода: " + exc);
        }

    }

    public String getData(int x, int y){
        String string = "";
        return string;
    }
}

package Sudoku;

public class Mechanics {

    public Cell[][] mix(int size, Cell[][] gameField){

        int mixIndex = 1;//перемнная, помогающая определить что именно будет миксоваться
        int index1, index2;
        boolean repeat = false;//перемнная отвечающая за повторения. принимает значение true если сгенерирована таже послоедовательность, что и раньше
        boolean equal;//переменная отвечающая за нахождение одинаковых пар.
        //две переменные выше отвечают занахождене одинаковых пар индексов столвбоц или строк
        //генератор должен сгенерировать два числа в диапазоне.
        //далее числа сортируются, меньшее становиться первым, большее вторым.
        //далее происходит перемешивание строк или столбцов согласно индексам
        //если программа генерирует два одинаковых числа, выше указанные переменные принимают значение true
        //и цикл повторяется, генерируются новые числа

        for (int type = 0; type < 2; type++) {
            int mixColloms = mixIndex - type;//type = 1, то будут проверяться столбцы.
            // все выражение равно нулю
            int mixLines = type;//если type = 0, то будут проверятся строки.
            for (int i = 0; i < 3; i++) {
                int timesToMix = (int) (1 + Math.random( ) * 2);//определяет сколько раз нужно перемешивать строки/столбцы.
                int[] mixedList = new int[timesToMix * 2];// массив для хранения перемешанных строк.
                for (int num = 0; num < mixedList.length; num++) {//заполняет массив элементами -1.
                    mixedList[num] = -1;
                }
                for (int a = 0; a < timesToMix; a++) {
                    do {
                        index1 = (int) (3 * i + Math.random( ) * 3);// генерирует случайные цифры в нужном диапазоне
                        // если i = 0, диапазон [0, 3)
                        // при i = 1, [3, 6)
                        // при i = 2, [6, 9)
                        // стоит обратить внимание на круглые скобки у верхней границы!
                        index2 = (int) (3 * i + Math.random( ) * 3);
                        if (index1 == index2) equal = true;
                        else equal = false;
                        if (!equal) {
                            if (index1 > index2) {
                                int temp = index1;
                                index1 = index2;
                                index2 = temp;
                            }
                            if (a > 0) {
                                for (int j = 0; j < mixedList.length; j += 2) {// проверка на наличие повторений.
                                    if (mixedList[j] != -1) {
                                        repeat = (mixedList[j] == index1) & (mixedList[j + 1] == index2) ? true : false;
                                        if (repeat) break;// если есть повторения - прервать цикл
                                    }
                                }
                            } else repeat = false;
                        }
                    } while (equal | repeat);

                    for (int j = 0; j < mixedList.length; j += 2) {//заполняет пустые элементы массива (если элемент равен -1).
                        if (mixedList[j] == -1) {
                            mixedList[j] = index1;
                            mixedList[(j + 1)] = index2;
                            break;
                        }
                    }

                    for (int j = 0; j < size; j++) {
                        int indexX1 = index1 * mixColloms + j * mixLines;
                        int indexX2 = index2 * mixColloms + j * mixLines;
                        int indexY1 = index1 * mixLines + j * mixColloms;
                        int indexY2 = index2 * mixLines + j * mixColloms;
                        int temp = gameField[indexX1][indexY1].getCount( );//записываем значение index1 столбца
                        gameField[indexX1][indexY1].setCount(gameField[indexX2][indexY2].getCount( ));//приравниваем значение index1 столбца к значению того же элемента index2 столбца.
                        gameField[indexX2][indexY2].setCount(temp);// прирасваиваем значению index2 столбца значение index1 столбца.
                    }
                }
            }
        }
        return gameField;
    }

    public Cell[][] check(Cell[][] gameField, int x, int y, int FIELD_SIZE, boolean primary){
        //если переменная primary принимает значение true - инструкция
        // else if (gameField[x * checkLines + i * checkColloms][y * checkColloms + i * checkLines].getEqual())
        //долэна сработать
        //если переменная primary принимает значение false - инструкция не срабатвает.
        int checkIndex = 1;//проверка столбцов
        int countIsEqual=0; // если проверяемый элемент быдет больше 0, то есть повторения.
        int auxilaryIndexX = x/3;//индекс для проверки квадратов 3х3
        //если равен 0, первая колонка
        //если равен 1, вторая. если рвен 2, то 3-я
        int auxilaryIndexY = y/3;//индекс для проверки квадратов 3х3
        //аналогично вышеописанному, но касается строк.
        // Нужно выделить проверяемый элемент в красный
        for (int timesToCheck = 0 ; timesToCheck < 2 ; timesToCheck++) {//сколько раз проверять
            int checkColloms = checkIndex - timesToCheck;//если timesToCheck = 1, то будут проверяться столбцы.
            // все выражение равно нулю
            int checkLines = timesToCheck;//если timesToCheck = 0, то будут проверятся строки.
            int indexToCheck = x * checkColloms + y * checkLines;//служит для проверки
            for (int i = 0; i < FIELD_SIZE; i++) {
                int indexX = x * checkLines + i * checkColloms;
                int indexY = y * checkColloms + i * checkLines;
                if ((gameField[indexX][indexY].getCount( ) != 0) && (indexToCheck != i)) {
                    //Проверка, элемент должен быть больше нуля.
                    //Изначально проверяемый элемент с индексом x y необходимо пропустить.
                    if (gameField[indexX][indexY].getCount( ) == gameField[x][y].getCount()) {
                        gameField[indexX][indexY].setColorText(true);
                        gameField[indexX][indexY].setEqual(true);
                        countIsEqual++;
                    } else if ((gameField[indexX][indexY].getEqual()) & primary){
                        //если уже окрашен - проверить есть совпадения или нет.
                        //выполнено по аналогии с верхними инструкциями.
                        gameField = check(gameField, indexX, indexY, FIELD_SIZE, false);
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                int indexX = i + auxilaryIndexX * 3;
                int indexY = j + auxilaryIndexY * 3;
                if((indexX != x) & (indexY != y) & (gameField[indexX][indexY].getCount( ) != 0)){
                    if (gameField[indexX][indexY].getCount( ) == gameField[x][y].getCount()) {
                        gameField[indexX][indexY].setColorText(true);
                        gameField[indexX][indexY].setEqual(true);
                        countIsEqual++;
                    } else if ((gameField[indexX][indexY].getEqual()) & primary){
                        //если уже окрашен - проверить есть совпадения или нет.
                        //выполнено по аналогии с верхними инструкциями.
                        gameField = check(gameField, indexX, indexY, FIELD_SIZE, false);
                    }
                }
            }
        }
        if (countIsEqual > 0) {
            gameField[x][y].setEqual(true);
            gameField[x][y].setColorText(true);
        } else {
            gameField[x][y].setEqual(false);
            gameField[x][y].setColorText(false);
        }
        return gameField;
    }

    public boolean check (Cell[][] gameField, int FIELD_SIZE){
        boolean haveIWon = false;
        int countEqualCells = 0;
        int countEmptyCells = 0;

        for (int x=0; x < FIELD_SIZE; x++){
            for (int y=0; y < FIELD_SIZE; y++){
                if (gameField[x][y].getEqual()){
                    countEqualCells++;
                }
                if (gameField[x][y].getNullify()){
                    countEmptyCells++;
                }
            }
        }

        if ((countEqualCells > 0) | (countEmptyCells > 0)){
            haveIWon = false;
        } else haveIWon = true;

        return haveIWon;
    }

}

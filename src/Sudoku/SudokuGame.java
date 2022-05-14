package Sudoku;
/**
 * Java. Classic Game Sudoku
 *
 * @author Sergey
 * @version 0.1.1 dated November 2, 2020
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class SudokuGame extends JFrame implements ActionListener, MouseMotionListener, MouseListener {

    final String TITLE_OF_PROGRAMM = "Sudoku";
    final String FILE_NAME = ".\\resources\\GameConfig.txt";
    final int BLOCK_SIZE = 30;
    final int FIELD_SIZE = 9;
    final int FIELD_DX = 6;
    final int FIELD_DY = 28+50;
    final int START_LOCATION = 200;
    final int MOUSE_BUTTON_LEFT = 1;
    final int MOUSE_BUTTON_RIGHT = 3;
    final int[] NUMBER_EMPTY_CELLS = {25, 40, 60};
    int numberEmptyCells = NUMBER_EMPTY_CELLS[0];
    static int countZeroCells;
    private boolean hasBeenSaved = true;
    Cell[][] gameField = new Cell[FIELD_SIZE][FIELD_SIZE];
    Random random = new Random();
    Mechanics mechanics = new Mechanics();
    DataFile dataFile = new DataFile();
    JMenuBar jMenuBar;
    JMenu jMenuFile;
    JMenu jMenuOptions;
    JMenu jMenuColors;
    JMenu jMenuDificulty;
    JMenuItem jMenuItemRestart;
    JMenuItem jMenuItemLow;
    JMenuItem jMenuItemMedium;
    JMenuItem jMenuItemHigh;
    JMenuItem jMenuItemExit;
    JMenuItem jMenuItemRed;
    JMenuItem jMenuItemGreen;
    JMenuItem jMenuItemBlue;

    SudokuGame(){
        setTitle(TITLE_OF_PROGRAMM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, FIELD_SIZE * BLOCK_SIZE + FIELD_DX*2, FIELD_SIZE * BLOCK_SIZE + FIELD_DY);
        setResizable(false);
        final Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX()/BLOCK_SIZE;
                int y = e.getY()/BLOCK_SIZE;
                    if (e.getButton() == MOUSE_BUTTON_LEFT){// left button mouse
                        if (x+1 > 0 & x < FIELD_SIZE & y+1 > 0 & y < FIELD_SIZE) {
                            gameField[x][y].increase( );
                        }
                    }

                    if (e.getButton() == MOUSE_BUTTON_RIGHT){
                        if (x+1 > 0 & x < FIELD_SIZE & y+1 > 0 & y < FIELD_SIZE) {
                            gameField[x][y].decrease( );
                        }
                    }
                    canvas.repaint();
                    action(x, y);
            }
        });

        jMenuBar = new JMenuBar();

        //Меню файл
        jMenuFile = new JMenu("FILE");
        jMenuItemRestart = new JMenuItem("RESTART");
        jMenuItemRestart.addActionListener(this);
        jMenuDificulty = new JMenu("DIFICULTY");
        jMenuItemLow = new JMenuItem("LOW");
        jMenuItemLow.addActionListener(this);
        jMenuItemMedium = new JMenuItem("MEDIUM");
        jMenuItemMedium.addActionListener(this);
        jMenuItemHigh = new JMenuItem("HIGH");
        jMenuItemHigh.addActionListener(this);
        jMenuItemExit = new JMenuItem("EXIT");
        jMenuItemExit.addActionListener(this);

        jMenuFile.add(jMenuItemRestart);
        jMenuDificulty.add(jMenuItemLow);
        jMenuDificulty.add(jMenuItemMedium);
        jMenuDificulty.add(jMenuItemHigh);
        jMenuFile.add(jMenuDificulty);
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuItemExit);

        jMenuBar.add(jMenuFile);

        //Меню Опции
        jMenuOptions = new JMenu("OPTIONS");
        jMenuColors = new JMenu("COLOR");
        jMenuItemRed = new JMenuItem("RED");
        jMenuItemRed.addActionListener(this);
        jMenuItemGreen = new JMenuItem("GREEN");
        jMenuItemGreen.addActionListener(this);
        jMenuItemBlue = new JMenuItem("BLUE");
        jMenuItemBlue.addActionListener(this);

        jMenuColors.add(jMenuItemRed);
        jMenuColors.add(jMenuItemGreen);
        jMenuColors.add(jMenuItemBlue);

        jMenuOptions.add(jMenuColors);

        jMenuBar.add(jMenuOptions);

        setJMenuBar(jMenuBar);
        add(BorderLayout.CENTER, canvas);
        setVisible(true);
        initField();
    }

    private void action(int x, int y){
        gameField = mechanics.check(gameField,x,y,FIELD_SIZE,true);
        dataFile.writeData(gameField, FIELD_SIZE);

        if (mechanics.check(gameField, FIELD_SIZE)){
            win();
        }
    }

    private void win(){
        String[] restart = {"RESTART"};
        JOptionPane.showOptionDialog(null,"YOU WON! CONGRATULATIONS!", "VICTORY!",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, restart, "RESTART" );
        restart();
    }

    private void restart(){
        hasBeenSaved = false;
        initField();
        repaint();
        dataFile.writeData(gameField, FIELD_SIZE);
    }

    private void initField() {
        int x, y;
        countZeroCells = 0;
        String stringOut = "";
        boolean saved = false;

        // заполнить массив данными из файла
        if (hasBeenSaved) {
            try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
                for (x = 0; x < FIELD_SIZE; x++) {// заполняет массив
                    for (y = 0; y < FIELD_SIZE; y++) {
                        stringOut = br.readLine( );
                        System.out.println(stringOut);
                        System.out.println(stringOut.length( ));
                        gameField[y][x] = new Cell( );
                        for(int i = 0; i < 6; i++) {
                            char c = stringOut.charAt(i);
                            System.out.println("c = " + c);
                            System.out.println("--");
                            int a = Integer.parseInt(String.valueOf(c));
                            switch (i){
                                case 0:
                                    gameField[y][x].setCount(Integer.parseInt(String.valueOf(c)));
                                    break;
                                case 1:
                                    if (a == 1){
                                        gameField[y][x].setFrozen(true);
                                    } else gameField[y][x].setFrozen(false);
                                    break;
                                case 2:
                                    if (a == 1){
                                        gameField[y][x].setColorText(true);
                                    } else gameField[y][x].setColorText(false);
                                    break;
                                case 3:
                                    gameField[y][x].setColorType(a);
                                    break;
                                case 4:
                                    if (a == 1){
                                        gameField[y][x].setEqual(true);
                                    } else gameField[y][x].setEqual(false);
                                    break;
                                case 5:
                                    if (a == 1){
                                        gameField[y][x].setNullify(true);
                                    } else gameField[y][x].setNullify(false);
                                    break;
                            }
                        }
                    }
                }
                saved = true;
            } catch (Exception exc) {
                System.out.println("Ошибка ввода-вывода: " + exc);
                saved = false;
                JOptionPane.showMessageDialog(this, exc + " \n" +
                        "GAME WILL BE RESTARTED!", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
        }

        //заполнить массив с "нуля"
        if (!saved) {
            System.out.println("Все с начала!" );
            for (x = 0; x < FIELD_SIZE; x++) {// заполняет массив
                for (y = 0; y < FIELD_SIZE; y++) {
                    gameField[y][x] = new Cell( );
                    int a = x / 3;
                    int b = 3 * (x - 3 * a);
                    int c = (y + 2 + b + a) / 11;
                    gameField[y][x].setCount(y + 1 + b - 9 * c + a);
                    // 1 2 3 4 5 6 7 8 9
                    // 4 5 6 7 8 9 1 2 3
                    // 7 8 9 1 2 3 4 5 6
                    // 2 3 4 5 6 7 8 9 1
                    // 5 6 7 8 9 1 2 3 4
                    // 8 9 1 2 3 4 5 6 7
                    // 3 4 5 6 7 8 9 1 2
                    // 6 7 8 9 1 2 3 4 5
                    // 9 1 2 3 4 5 6 7 8
                    gameField[y][x].setFrozen(true);
                }
            }

            //перемешать массив
            gameField = mechanics.mix(FIELD_SIZE, gameField);

            //"обнулить" некоторые ячейки
            while (countZeroCells < numberEmptyCells) {
                do {
                    x = random.nextInt(FIELD_SIZE);
                    y = random.nextInt(FIELD_SIZE);
                } while (gameField[y][x].getNullify());
                gameField[y][x].setNullify(true);
                gameField[y][x].setFrozen(false);
                countZeroCells++;
            }
        }
    }

    public static void main(String[] args) {
        new SudokuGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comand = e.getActionCommand();
        int colorType = -1;

            switch (comand) {
                case "RESTART":
                    restart();
                    break;
                case "EXIT":
                    System.exit(0);
                    break;
                case "RED":
                    colorType = 0;
                    break;
                case "GREEN":
                    colorType = 2;
                    break;
                case "BLUE":
                    colorType = 4;
                    break;
                case "LOW":
                    System.out.println("LOW" );
                    responce(0);
                    break;
                case "MEDIUM":
                    System.out.println("MEDIUM" );
                    responce(1);
                    break;
                case "HIGH":
                    System.out.println("HIGH" );
                    responce(2);
                    break;
            }

            if ((colorType == 0) | (colorType == 2) | (colorType == 4)) {
                for (int x = 0; x < FIELD_SIZE; x++) {
                    for (int y = 0; y < FIELD_SIZE; y++) {
                        gameField[x][y].setColorType(colorType);
                    }
                }
                repaint();
            }
    }

    private boolean responce(int dificulty){
        boolean yesStartAndSetNewDificulty = true;
        final String MESSAGE = "DO YOU WISH TO START NEW GAME?" + '\n'+ "IT IS NESSESARY TO SET NEW DIFICULTY";
        final String TITLE = "DO YOU WISH TO SET NEW DIFICULTY AND START NEW GAME?";

        int responce = JOptionPane.showConfirmDialog(this, MESSAGE, TITLE, JOptionPane.YES_NO_OPTION);

        switch (responce){
            case JOptionPane.YES_OPTION:
                numberEmptyCells = NUMBER_EMPTY_CELLS[dificulty];
                restart();
                System.out.println("set new dif" );
                break;
            case JOptionPane.NO_OPTION:
                break;
            case JOptionPane.CLOSED_OPTION:
                break;
        }


        return yesStartAndSetNewDificulty;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public class Canvas extends JPanel{
        @Override
        public void paint(Graphics g){
            super.paint(g);
            for (int x=0; x < FIELD_SIZE; x++){
                for (int y=0; y < FIELD_SIZE; y++){
                    gameField[x][y].paint(g, x, y, BLOCK_SIZE, FIELD_DX);
                }
            }
        }
    }

}

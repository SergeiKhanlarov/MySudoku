package Sudoku;

import java.awt.*;

public class Cell {

    private int count;//определяет цифру в ячейке
    private int colorType = 4;//определяет цвет ячеек
    private boolean nullify = false;//определяет обнулена ли ячейка
    private boolean frozen = false;//определяет заморожена ли ячейка, если да, то менять в ней цифры нельзя
    private boolean equal = false;//определяет есть ли одинаковые ячейки
    private String color;
    private String  colorText = "0x000000";
    private String[] colors = {
            "0xCD5C5C",//темно-красный
            "0xF08080",//розовый
            "0x32CD32",//темно-зеленый
            "0x00FF00",//светло-зеленый
            "0x00BFFF",//темно-синий
            "0x87CEFA"//голубой
    };


    public void setColorType(int colorType){
        if ((colorType != 0) & (colorType != 2) & (colorType != 4)){
            this.colorType = 4;
        }else this.colorType = colorType;
    }

    public int getColorType(){
        return colorType;
    }

    public void setEqual(boolean equal){ this.equal = equal;}

    public boolean getEqual(){
        return equal;
    }

    public void setColor(int x, int y){
        if (frozen){
            if (x/3==1 ^ y/3==1){
                this.color = "0xFFFFFF";
            }else this.color = colors[0 + this.colorType];
        }  else {
            if (x/3==1 ^ y/3==1) {
                this.color = "0xF0FFFF";
            } else this.color = colors[1 + this.colorType];
        }
    }

    public void setColorText(boolean colorRed){
        if (colorRed){
            colorText = "0xFF0000";
        } else {
            colorText = "0x000000";
        }
    }

    public boolean getColorText(){
        if (colorText == "0xFF0000"){
            return true;
        }else return false;
    }

    public void setCount (int count){
        if (count > -1 & count < 10) {
            this.count = count;
        } else{
            setNullify(true);
            SudokuGame.countZeroCells++;
        }
    }

    public int getCount(){
        return count;
    }

    public void paint(Graphics g, int x, int y, int BLOCK_SIZE, int FIELD_DX) {

        int a = x/3;
        int b = y/3;

        setColor(x, y);
        g.setColor(Color.decode(color));
        //System.out.println("P.15:Eq = " + getEqual() + " x  = " + x + " y = " + y + " color =" + colorText );
        //setColorText(getEqual());

        g.fill3DRect(x*BLOCK_SIZE + FIELD_DX/2,
                y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, true);
        if (!nullify){
            paintString(g, Integer.toString(count), x, y, BLOCK_SIZE, FIELD_DX);
        }
    }
    public void paintString(Graphics g, String str, int x, int y, int BLOCK_SIZE, int FIELD_DX) {
        g.setColor(Color.decode(colorText));
        g.setFont(new Font("", Font.BOLD, BLOCK_SIZE));
        g.drawString(str, x*BLOCK_SIZE + 8 + FIELD_DX/2, y*BLOCK_SIZE + 26);
    }


    public void increase (){
        //System.out.println("Увеличено!" );
        if (frozen) return;
        nullify = false;
        if (count < 9) count++;
        else if (count == 9) count = 1;
    }

    public void decrease (){
        if (frozen) return;
        if (count > 1) count--;
        else if (count == 1) setNullify(true);
        else if (getNullify()) {
            count = 9;
            setNullify(false);
        }
    }

    public void act(){
        if (!frozen){

        }
    }

    public void setNullify (boolean nullify){
        if (nullify){
            setCount(0);
        }
        this.nullify = nullify;
    }

    public boolean getNullify (){
        return nullify;
    }

    public void setFrozen(boolean frozen){
        this.frozen = frozen;
    }

    public boolean getFrozen(){
        return frozen;
    }
}

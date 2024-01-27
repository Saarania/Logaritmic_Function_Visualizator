/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Pocitac
 */
public class Field extends Rectangle {
    
    private int i;
    private int j;

    public Field() {
    }

    public Field(double width, double height) {
        super(width, height);
    }

    public Field(double width, double height, Paint fill) {
        super(width, height, fill);
    }

    public Field(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public int getI() {
        return i;
    }
    public void setIJ(int i,int j) {
        this.j = j;
        this.i = i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    
}

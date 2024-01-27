/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import javafx.scene.control.TextArea;

/**
 *
 * @author Pocitac
 */
public class TextAreaMine extends  TextArea {

    public TextAreaMine() {
    }

    public TextAreaMine(String text, int x, int y, int width, int height) {
        super(text);
        setTranslateX(x);
        setTranslateY(y);
        setPrefWidth(width);
        setPrefHeight(height);
        Function.root.getChildren().add(this);
    }
    
    public TextAreaMine(String s) {
        super(s);
    }
    
}

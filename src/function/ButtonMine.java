/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import javafx.scene.control.Button;

/**
 *
 * @author Pocitac
 */
public class ButtonMine extends Button {

    public ButtonMine() {
    }

    public ButtonMine(String text) {
        super(text);
    }
    
    public ButtonMine(String text, int x, int y) {
        super(text);
        setTranslateX(x);
        setTranslateY(y);
        Function.root.getChildren().add(this);
    }
    
    
}

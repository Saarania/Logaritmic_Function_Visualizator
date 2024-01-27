/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Pocitac
 */
public class TextMine extends Text {

    public TextMine() {
    }

    public TextMine(String text) {
        super(text);
    }

    public TextMine(String text, int x, int y, int size) {
        super(text);
        setTranslateX(x);
        setTranslateY(y);
        setFont(Font.font(size));
        Function.root.getChildren().add(this);
    }
    
    public TextMine(double x, double y, String text) {
        super(x, y, text);
    }
    
    
    
}

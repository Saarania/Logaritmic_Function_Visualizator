/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Pocitac
 */
public class Predpis {

    private Node[] prvky;
    private ButtonMine buttonOk;
    private int numberOfTextAreas = 0;

    /**
     *
     * @param x souradnice X pro vsechny prvky
     * @param y souradnice Y pro vsechny prvky
     * @param prvky pole vsech prvku
     */
    public Predpis(Node... prvky) {
        this.prvky = prvky;        
        for (int i = 0; i < prvky.length; i++) {
            prvky[i].setTranslateX(Board.DISTANCE_FOR_TEXT + i * 60);
            prvky[i].setTranslateY(100);
            if (prvky[i] instanceof TextMine) {
                TextMine textPomoc = (TextMine) prvky[i];
                textPomoc.setFont(Font.font(40)); 
            }
            if (prvky[i] instanceof TextAreaMine) {
                TextAreaMine textAreaPomoc = (TextAreaMine) prvky[i];
                textAreaPomoc.setPrefWidth(3);
                textAreaPomoc.setPrefHeight(2);
                textAreaPomoc.setTranslateY(textAreaPomoc.getTranslateY() - 20);
                numberOfTextAreas++;
            }
            Function.root.getChildren().add(prvky[i]);
        }
        buttonOk = new ButtonMine("OK", (int) prvky[0].getTranslateX(), (int) prvky[0].getTranslateY() + 100);
        buttonOk.setOnAction((ActionEvent event) -> {
            Board.rightFunctionChooser(buttonAction());
        });
    }
     //ulozi cisla z textAren a smaze po sobe predpis
    private int[] buttonAction() {
        //NASTAVOVANI HODNOT Z TEXTAREN, KONTROLA JESTLI JSOU TO OPRAVDU CISLA
        int[] hodnotyZAren = new int[numberOfTextAreas];
        int pomocne = 0;
        try {
            for (Node node : prvky) {
                if (node instanceof TextArea) {
                    hodnotyZAren[pomocne] = Integer.parseInt(((TextArea) node).getText());
                    pomocne++;
                }
            }
        } catch (NumberFormatException ex) {
            pomocne = 0;
            for (Node node : prvky) {
                if (node instanceof TextArea) {
                    hodnotyZAren[pomocne] = 1;
                    pomocne++;
                }
            }
        }
        //NECHA PO SOBE VSECHNO SMAZAT
        for (Node node : prvky) {
            node.setVisible(false);
            node = null;
        }
        buttonOk.setVisible(false);
        return hodnotyZAren;
    }

    public Button getButtonOk() {
        return buttonOk;
    }

    public Node[] getPrvky() {
        return prvky;
    }

    public void setPrvky(Node... prvky) {
        this.prvky = prvky;

    }

}

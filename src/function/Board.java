/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import com.sun.javafx.scene.SceneHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Pocitac
 *
 * MISTO RECTANGLE POUZIVAT CANVAS, ZKRATIT VSECHNY METODY
 */
public class Board {

    private static Scene scene = Function.scene;
    private static Group root = Function.root;

    public static final int SCENE_WIDTH = (int) scene.getWidth();
    public static final int SCENE_HEIGHT = (int) scene.getHeight();
    public static int DISTANCE_FROM_EDGE_X = 320;
    public static int DISTANCE_FROM_EDGE_Y = 500;
    public static final int CENTIMETER = 38; //px
    public static final int CENTIMETER_HALF = 19; //px
    public static final int DISTANCE_FOR_TEXT = DISTANCE_FROM_EDGE_X > SCENE_WIDTH / 2 ? SCENE_WIDTH / 2 - 200 : SCENE_WIDTH / 2 + 50;

    private static Canvas canvas = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);
    public static GraphicsContext draw = canvas.getGraphicsContext2D();

    //Spusti se pri startu programu
    public static void inicialize() {
        root.getChildren().add(canvas);
        generateBoard();
        createAxis();
        generateButtons();
        //TODO nikdy nedodelano
        //setDistanceFromEdges();
    }

    public static void setDistanceFromEdges() {
        TextAreaMine textAreaX = new TextAreaMine("axes X distance", 10, 10, 100, 2);
        TextAreaMine textAreaY = new TextAreaMine("axes X distance", 10, 50, 100, 2);
        ButtonMine btnOk = new ButtonMine("OK", 10, 80);
        btnOk.setOnAction((ActionEvent event) -> {
            btnOk.setVisible(false);
            textAreaX.setVisible(false);
            textAreaY.setVisible(false);
            try {
                DISTANCE_FROM_EDGE_X = Integer.parseInt(textAreaX.getText());
                DISTANCE_FROM_EDGE_Y = Integer.parseInt(textAreaY.getText());
                if (DISTANCE_FROM_EDGE_X > SCENE_WIDTH) {
                    DISTANCE_FROM_EDGE_X = SCENE_WIDTH;
                }
                if (DISTANCE_FROM_EDGE_Y > SCENE_HEIGHT) {
                    DISTANCE_FROM_EDGE_Y = SCENE_HEIGHT;
                }
            } catch (NumberFormatException ex) {
                //nic se nestane

            }
            inicialize();
        });
    }

    public static void generateButtons() {
        TextMine text = new TextMine("Which function do \nyou want to create?", DISTANCE_FOR_TEXT, 50, 40);
        ButtonMine linearniFunkceButton = new ButtonMine("Linear Function", DISTANCE_FOR_TEXT, 150);
        linearniFunkceButton.setOnAction((ActionEvent event) -> {
            text.setVisible(false);
            linearniFunkceButton.setVisible(false);
            funkcePredpis(TypFunkce.LINEAR);
        });
        
        //TODO nedodelano nikdy
        /*
        ButtonMine kvadratickaFunkceButton = new ButtonMine("Quadratic function", DISTANCE_FOR_TEXT + 100, 150);
        kvadratickaFunkceButton.setOnAction((ActionEvent event) -> {
            text.setVisible(false);
            kvadratickaFunkceButton.setVisible(false);
            funkcePredpis(TypFunkce.QUADRATIC);
        }); */
    }

    public static void generateBoard() {
        draw.setFill(Color.GRAY);
        for (int i = SCENE_WIDTH - 1; i > -1; i--) {
            for (int j = 0; j < SCENE_HEIGHT; j++) {
                draw.fillRect(i, j, 1, 1);
            }
        }
    }

    public static void linearniFunkce(int a, int b) {
        draw.setFill(Color.BLACK);
        for (int i = 0; i < SCENE_WIDTH - DISTANCE_FROM_EDGE_X; i++) {
            int y = a * i;
            int bodY = DISTANCE_FROM_EDGE_Y - y - b * CENTIMETER;
            // kontrola pro ukonceni cyklu
            if (bodY >= SCENE_HEIGHT || bodY < 0) {
                break;
            }
            draw.fillRect(DISTANCE_FROM_EDGE_X + i, bodY, 1, 1);
        }
        for (int i = 0; i < DISTANCE_FROM_EDGE_X; i++) {
            int y = a * i;
            int bodY = DISTANCE_FROM_EDGE_Y + y - b * CENTIMETER;
            // kontrola pro ukonceni cyklu
            if (bodY >= SCENE_HEIGHT || bodY < 0) {
                break;
            }
            draw.fillRect(DISTANCE_FROM_EDGE_X - i, bodY, 1, 1);
        }
    }

    //PODLE VSTUPU ZJISTI, O JAKOU FUNKCI SE JEDNA
    public static void rightFunctionChooser(int[] array) {
        //linearni funkce
        if (array.length == 2) {
            linearniFunkce(array[0], array[1]);
        }
    }

    public static void funkcePredpis(TypFunkce typ) {
        //vytvori predpis se vsemi vlastnosmi, predpis si sam vytvori tlacitko ok

        switch (typ) {
            case LINEAR:
                Predpis predpisLinearni = new Predpis(new TextMine("Y = "), new TextAreaMine("a"), new TextMine("x"), new TextMine(" + "), new TextAreaMine("b"));
                break;
            case QUADRATIC:
                Predpis predpisKvadraticky = new Predpis(); //DODELAT
                break;
        }

    }

    public static void createAxis() {
        for (int i = 0; i < SCENE_WIDTH; i++) {//OSA X
            if ((i - DISTANCE_FROM_EDGE_X) % CENTIMETER_HALF == 0) { //nalezen pulcentimetr
                for (int j = 0; j < 3; j++) {
                    draw.setFill(Color.YELLOW);
                    draw.fillRect(i, DISTANCE_FROM_EDGE_Y - 1 + j, 1, 1);
                }
            }
            if ((i - DISTANCE_FROM_EDGE_X) % CENTIMETER == 0) { //nalezen centimetr
                for (int j = 0; j < 9; j++) {
                    draw.setFill(Color.RED);
                    draw.fillRect(i, DISTANCE_FROM_EDGE_Y - 4 + j, 1, 1);
                }
            } else {
                draw.setFill(Color.BLACK);
                draw.fillRect(i, DISTANCE_FROM_EDGE_Y, 1, 1);
            }
        }
        //nastavovani textu pro osu x
        for (int i = DISTANCE_FROM_EDGE_X; i > -1; i--) { //zaporne
            setTextX(i);
        }
        for (int i = DISTANCE_FROM_EDGE_X; i < SCENE_WIDTH; i++) { //kladne
            setTextX(i);
        }

        for (int i = SCENE_HEIGHT - 1; i > -1; i--) {//OSA Y
            if ((i - DISTANCE_FROM_EDGE_Y) % CENTIMETER_HALF == 0) { //nalezen pulcentimetr
                for (int j = 0; j < 3; j++) {
                    draw.setFill(Color.YELLOW);
                    draw.fillRect(DISTANCE_FROM_EDGE_X - 1 + j, i, 1, 1);
                }
            }

            if ((i - DISTANCE_FROM_EDGE_Y) % CENTIMETER == 0) { //nalezen centimetr
                for (int j = 0; j < 9; j++) {
                    draw.setFill(Color.RED);
                    draw.fillRect(DISTANCE_FROM_EDGE_X - 4 + j, i, 1, 1);
                }
            } else {
                draw.setFill(Color.BLACK);
                draw.fillRect(DISTANCE_FROM_EDGE_X, i, 1, 1);
            }
        }
        for (int i = DISTANCE_FROM_EDGE_Y; i > -1; i--) { //zaporne
            setTextY(i);
        }
        for (int i = DISTANCE_FROM_EDGE_Y; i < SCENE_HEIGHT; i++) {
            setTextY(i);
        }
    }

    //podle paramentru i v cyklu projede vsechny body v ose a urci, jestli jim priradi text a jaky
    public static void setTextX(int i) {
        if ((i - DISTANCE_FROM_EDGE_X) % CENTIMETER_HALF == 0) {
            Text text = new Text((i - DISTANCE_FROM_EDGE_X) / CENTIMETER_HALF * 5 + "");
            root.getChildren().add(text);
            text.setFont(Font.font(10));
            text.setTranslateY(DISTANCE_FROM_EDGE_Y + 20);
            text.setTranslateX(i);
        }
    }

    public static void setTextY(int j) {
        if ((j - DISTANCE_FROM_EDGE_Y) % CENTIMETER_HALF == 0) {
            Text text = new Text((j - DISTANCE_FROM_EDGE_Y) / CENTIMETER_HALF * 5 + "");
            root.getChildren().add(text);
            text.setFont(Font.font(10));
            text.setTranslateY(j + 5);
            text.setTranslateX(DISTANCE_FROM_EDGE_X - 25);
        }
    }

}

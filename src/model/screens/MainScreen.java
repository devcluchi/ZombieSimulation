package model.screens;

import control.framework.UIController;
import model.Logic;
import model.entities.Mensch;
import model.entities.TableManager;
import model.entities.Wetter;
import model.framework.GraphicalObject;
import view.framework.DrawTool;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

public class MainScreen extends GraphicalObject {

    BufferedImage image;
    Button[] buttons;
    private TableManager tableManager;
    private Logic logic;
    private Mensch mensch;
    private Wetter wetter;


    public MainScreen(UIController uiController) {

        logic = new Logic();
        image = createNewImage("assets/images/screens/main.png");
        uiController.drawObject(this);
        createButtons(uiController);

    }



    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(image,0,0);
    }

    @Override
    public void update(double dt) {
        updateButtons();
    }


    private void createButtons(UIController uiController) {
        buttons = new Button[2];

            buttons[0] = new Button(1205,25,"assets/images/buttons/mainScreenBasic_"+0+".png","assets/images/buttons/mainScreenSelected_"+0+".png");
            uiController.drawObject(buttons[0]);

            buttons[1] = new Button(1100,75,"assets/images/buttons/mainScreenBasic_"+0+".png","assets/images/buttons/mainScreenSelected_"+0+".png");
            uiController.drawObject(buttons[1]);

    }


    private void updateButtons(){

            if(buttons[0].isHit()){
                logic.resetAllStats();
                System.exit(0);

            }
            if(buttons[1].isHit()){
                simNextDay();
            }
    }


    public void simNextDay(){
        System.out.println("simNextDay");
        logic.resetAllStats();
    }
}

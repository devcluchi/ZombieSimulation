package model.screens;

import control.framework.UIController;
import model.Logic;
import model.entities.Mensch;
import model.entities.TableMaker;
import model.entities.Wetter;
import model.framework.GraphicalObject;
import view.framework.DrawTool;

import java.awt.image.BufferedImage;

public class MainScreen extends GraphicalObject {

    BufferedImage background;
    Button[] buttons;
    Logic logic;

    public MainScreen(UIController uiController) {
        background = createNewImage("assets/images/screens/main.png");
        uiController.drawObject(this);
        createButtons(uiController);
        logic = new Logic();
    }



    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(background,0,0);
        drawStats(drawTool);
    }

    private void drawStats(DrawTool drawTool) {

    }

    @Override
    public void update(double dt) {
        updateButtons();
    }


    private void createButtons(UIController uiController) {
        buttons = new Button[1];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(1205,25,"assets/images/buttons/mainScreenBasic_"+i+".png","assets/images/buttons/mainScreenSelected_"+i+".png");
            uiController.drawObject(buttons[i]);
        }
    }


    private void updateButtons(){
        for (int i = 0; i < buttons.length; i++) {
            if(buttons[i].isHit()){
                logic.resetAllStats();
                System.exit(0);
            }
        }
    }
}

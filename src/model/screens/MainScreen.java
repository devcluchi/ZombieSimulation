package model.screens;

import control.framework.UIController;
import model.Logic;
import model.framework.GraphicalObject;
import view.framework.DrawTool;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainScreen extends GraphicalObject {

    BufferedImage image;
    Button[] buttons;
    private Logic logic;
    private Font font;


    public MainScreen(UIController uiController) {

        logic = new Logic();

        image = createNewImage("assets/images/screens/main.png");
        uiController.drawObject(this);
        createButtons(uiController);
        font = new Font("Comic Sans",Font.BOLD,28);
    }



    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(image,0,0);
        drawTool.setCurrentColor(255,255,255,255);
        drawTool.setFont(font);
        drawTool.drawText(250,80,""+logic.getLebendeMenschen());
        drawTool.drawText(250,130,""+logic.getLebendeZombies());
        drawTool.drawText(950,100,"wasser");
        drawTool.drawText(850,100,"med");
    }

    @Override
    public void update(double dt) {
        updateButtons();
    }


    private void createButtons(UIController uiController) {
        buttons = new Button[8];

        buttons[0] = new Button(1205,25,"assets/images/buttons/closeBasic.png","assets/images/buttons/closeSelected.png");
        uiController.drawObject(buttons[0]);

        buttons[1] = new Button(1100,75,"assets/images/buttons/playBasic.png","assets/images/buttons/playSelected.png");
        uiController.drawObject(buttons[1]);

        buttons[2] = new Button(1000,100,"assets/images/buttons/drinkBasic.png","assets/images/buttons/drinkSelected.png");
        uiController.drawObject(buttons[2]);

        buttons[3] = new Button(900,100,"assets/images/buttons/mediBasic.png","assets/images/buttons/mediSelected.png");
        uiController.drawObject(buttons[3]);

        buttons[4] = new Button(1205,125,"assets/images/buttons/replayBasic.png","assets/images/buttons/replaySelected.png");
        uiController.drawObject(buttons[4]);

        buttons[5] = new Button(800,100,"assets/images/buttons/attackBasic.png","assets/images/buttons/attackSelected.png");
        uiController.drawObject(buttons[5]);

        buttons[6] = new Button(800,200,"assets/images/buttons/attackBasic.png","assets/images/buttons/attackSelected.png");
        uiController.drawObject(buttons[6]);

        buttons[7] = new Button(800,300,"assets/images/buttons/attackBasic.png","assets/images/buttons/attackSelected.png");
        uiController.drawObject(buttons[7]);


    }


    private void updateButtons(){
        if(buttons[0].isHit()){
            System.exit(0);
        }
        if(buttons[1].isHit()){
            simNextDay();
        }
        if (buttons[2].isHit()){
            logic.useWater();
        }
        if (buttons[3].isHit()){
            logic.useMedi();
        }
        if (buttons[4].isHit()){
            logic.resetAllStats();
        }
        if(buttons[5].isHit()) {
            logic.tryToKillZombie();
        }

        if(buttons[6].isHit()) {
            logic.eat();
        }

        if(buttons[7].isHit()) {
            logic.useAnimal();
        }
    }


    public void simNextDay(){
        System.out.println("Hallo");
        logic.tryToFeedHuman();
        logic.humanTryToGetWeapon();
        logic.menschenBeduerfnisse();
        logic.krankWerden();
        logic.animalLife();
        logic.animalFight();
        logic.updateAllInformation();

    }
}

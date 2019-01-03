package model.screens;

import control.framework.UIController;
import model.Logic;
import model.framework.GraphicalObject;
import view.framework.DrawTool;

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
        System.out.println("Hallo");
        logic.updateAllInformation();
        logic.tryToFeedHuman();
    }
}

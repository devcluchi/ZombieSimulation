package model.screens;

import control.framework.UIController;
import model.entities.Mensch;
import model.entities.TableMaker;
import model.entities.Wetter;
import model.framework.GraphicalObject;
import view.framework.DrawTool;

import java.awt.image.BufferedImage;

public class MainScreen extends GraphicalObject {

    BufferedImage image;
    Button[] buttons;
    TableMaker tableMaker;
    private Mensch mensch;
    private Wetter wetter;

    public MainScreen(UIController uiController) {

        tableMaker= new TableMaker();
        mensch = new Mensch();
        wetter = new Wetter();

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
        buttons = new Button[1];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(1205,25,"assets/images/buttons/mainScreenBasic_"+i+".png","assets/images/buttons/mainScreenSelected_"+i+".png");
            uiController.drawObject(buttons[i]);
        }
    }


    private void updateButtons(){
        for (int i = 0; i < buttons.length; i++) {
            if(buttons[i].isHit()){
                System.exit(0);
            }
        }
    }
}

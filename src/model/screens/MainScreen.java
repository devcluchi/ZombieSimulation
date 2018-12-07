package model.screens;

import control.framework.UIController;
import model.entities.TableMaker;
import model.framework.GraphicalObject;
import view.framework.DrawTool;

import java.awt.image.BufferedImage;

public class MainScreen extends GraphicalObject {

    BufferedImage image;
    Button[] buttons;
    TableMaker tableMaker;

    public MainScreen(UIController uiController) {

        tableMaker= new TableMaker();

        image = createNewImage("assets/imagesw/screens/main.png");
        createButtons(uiController);
    }



    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(image,0,0);
    }

    @Override
    public void update(double dt) {
        updateButton();
    }


    private void createButtons(UIController uiController) {
        buttons = new Button[1];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(100,100,"assets/images/buttons/mainScreenBasic_"+i+".png","assets/images/buttons/mainScreenSelected_"+i+".png");
            uiController.drawObject(buttons[i]);
        }
    }


    private void updateButton(){
        for (int i = 0; i < buttons.length; i++) {
            if(buttons[i].isHit()){
                System.out.println("hit it"+i);
            }
        }
    }
}

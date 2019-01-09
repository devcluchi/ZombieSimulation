package model.screens;

import control.framework.UIController;
import model.framework.GraphicalObject;
import view.framework.DrawTool;

import java.awt.image.BufferedImage;

public class NotizenScreen extends GraphicalObject {

    private boolean visible;
    private BufferedImage background;
    private MainScreen mainScreen;

    public NotizenScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        visible = false;
        background = createNewImage("assets/images/screens/main.png");
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(visible){
            //drawTool.drawImage(background,200,100);
            drawTool.drawFilledRectangle(150,100,200,200);
        }
    }

    @Override
    public void update(double dt) {

    }

    public void updateInformations(){
        //mainScreen.getLogic().getTableManager().getStmt().executeQuery();

    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }
}

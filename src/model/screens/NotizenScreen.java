package model.screens;

import control.framework.UIController;
import model.framework.GraphicalObject;
import view.framework.DrawTool;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotizenScreen extends GraphicalObject {

    private boolean visible;
    private BufferedImage background;
    private MainScreen mainScreen;
    private String tiere, toteMenschen, essen, medikamente, waffen, hunger,durst,krank;

    public NotizenScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        visible = false;
        background = createNewImage("assets/images/screens/main.png");
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(visible){
            //drawTool.drawImage(background,200,100);
            drawTool.setCurrentColor(255,55,55,255);
            drawTool.drawFilledRectangle(150,100,500,500);
            drawTool.setCurrentColor(255,255,255,255);
            //drawTool.drawText(250,150,"Tiere: "+tiere);
            drawTool.drawText(200,250,"Durchschnitts Hunger: "+hunger);
            drawTool.drawText(200,350,"Durchschnitts Durst: "+durst);
            drawTool.drawText(200,450,"Durchschnitts Krankheit: "+krank);
            //drawTool.drawText(250,550,"Waffen: "+waffen);

        }
    }

    @Override
    public void update(double dt) {

    }

    public void updateInformations(){
        ResultSet resultSet = null;
        try {
            resultSet = mainScreen.getLogic().getTableManager().getStmt().executeQuery("SELECT Bestand FROM Zom_Nutztiere WHERE nID = 1;");
            resultSet.next();
            tiere = resultSet.getString("Bestand");
            resultSet = mainScreen.getLogic().getTableManager().getStmt().executeQuery("SELECT COUNT(infiziert) FROM Zom_Zombie;");
            resultSet.next();
            toteMenschen = resultSet.getString(1);
            resultSet = mainScreen.getLogic().getTableManager().getStmt().executeQuery("SELECT Vorrat FROM Zom_Essen WHERE eID = 1;");
            resultSet.next();
            essen = resultSet.getString("Vorrat");
            resultSet = mainScreen.getLogic().getTableManager().getStmt().executeQuery("SELECT Vorrat FROM Zom_Medikament WHERE mID = 1;");
            resultSet.next();
            medikamente = resultSet.getString("Vorrat");
            resultSet.next();
            resultSet = mainScreen.getLogic().getTableManager().getStmt().executeQuery("SELECT AVG(hunger) FROM Zom_Menschen WHERE lebt = 1;");
            resultSet.next();
            hunger = resultSet.getString("AVG(hunger)");
            resultSet = mainScreen.getLogic().getTableManager().getStmt().executeQuery("SELECT AVG(durst) FROM Zom_Menschen WHERE lebt = 1;");
            resultSet.next();
            durst = resultSet.getString("AVG(durst)");
            resultSet = mainScreen.getLogic().getTableManager().getStmt().executeQuery("SELECT AVG(krank) FROM Zom_Menschen WHERE lebt = 1;");
            resultSet.next();
            krank = resultSet.getString("AVG(krank)");
            waffen = Integer.toString(mainScreen.getLogic().getWaffenVorrat());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        updateInformations();
    }

    public boolean isVisible() {
        return visible;
    }
}

package control;

import model.Entitäten.Mensch;
import model.Entitäten.TableMaker;
import model.framework.GraphicalObject;
import view.framework.DrawTool;
import view.framework.DrawableObject;

import java.awt.event.MouseEvent;

public class Menu implements DrawableObject {

    private TableMaker tableMaker;
    private Mensch mensch;


    public Menu(){

        tableMaker= new TableMaker();

    }


    public void simNextDay(){

        //Müssen wir noch machen

    }


    @Override
    public void draw(DrawTool drawTool) {

        drawTool.drawFilledRectangle(0,0,1200,1200);
        drawTool.setCurrentColor(100,100,10,255);
        drawTool.drawFilledCircle(400,400,50);

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (e.getX() > 350 && e.getX() < 450 && e.getY() > 350 && e.getY() < 450) {

            System.out.println("Hallo ich bin ein Kreis und wurde gedrückt");

            tableMaker.createTable();
        }

    }
}
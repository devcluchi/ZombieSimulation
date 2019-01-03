package model.screens;

import control.Config;
import model.framework.GraphicalObject;
import view.framework.DrawTool;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by Luis on 04/02/2018.
 */
public class Button extends GraphicalObject {


    //Attribute
    private int status;
    private boolean hit;

    //Referenzen
    private BufferedImage basic, selected;
    private Rectangle2D.Double boundingBox;

    /**
     * Konstruktor
     * Ein Butoon hat folgende Funktionen:
     *      -wechsselt das Bild sobald der Masuzeiger über ihm ist
     *      -bei einem Klick auf den Button wird die "hit"-Variable auf wahr gesetzt
     * @param x die X-Position des Buttons
     * @param y die Y-Koordinate des Buttons
     * @param basicPath der Pfad zu dem "Standard"-Bild des Buttons
     * @param selectedPath der Pfad zu dem Bild, welches gezeichnet wird sobald der Mauszeiger über em Button ist
     */
    public Button(int x, int y, String basicPath, String selectedPath) {
        this.x = x;
        this.y = y;

        basic = createNewImage(basicPath);
        selected = createNewImage(selectedPath);
        if(basic.getWidth() == selected.getWidth() && basic.getHeight() == selected.getHeight()) {
            width = basic.getWidth();
            height = basic.getHeight();
        }else if( Config.INFO_MESSAGES) System.out.println("Bitte füge Bilder mit gleichen Maßen hinzu!");

        status = 0;
        hit = false;

        boundingBox = new Rectangle2D.Double(this.x,this.y,width,height);

    }

    /**
     * Zeichnet je nach Status des Buttons das "Standard"- oder "Ausgewählt"-Bild
     */
    @Override
    public void draw(DrawTool drawTool) {
        if(status == 0){
            drawTool.drawImage(basic,x,y);
        }else if(status == 1){
            drawTool.drawImage(selected,x,y);
        }
    }

    /**
     * Die Variable hit soll durchgängig auf false gesetzt werden, damit wenn sie auf true gesetzt wird, das ganze nur für einen Tick anhält
     */
    @Override
    public void update(double dt) {
        hit=false;
    }

    /**
     *Überprüfung, ob der Mauszeiger sich über dem Button befidet
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if(e.getX()>= boundingBox.getX() && e.getX()<= boundingBox.getX() + boundingBox.getWidth() && e.getY() >= boundingBox.getY() && e.getY() <=  boundingBox.getY() + boundingBox.getHeight()){
            status=1;
        }else{
            status = 0;
        }
    }

    /**
     * Variable hit wird auf true gesetzt bei einem Mausklick im Button
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getX()>= boundingBox.getX() && e.getX()<= boundingBox.getX() + boundingBox.getWidth() && e.getY() >= boundingBox.getY() && e.getY() <=  boundingBox.getY() + boundingBox.getHeight()){
            hit = true;
        }
    }

    /**
     * Wird benutzt um in der Klasse, wo der Button erzeugt wurde, die Reaktion nach einem Klick auf den Button festzulegen
     * @return den Status der Variable hit
     */
    public boolean isHit() {
        return hit;
    }


}

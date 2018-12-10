package model;

import model.entities.Mensch;
import model.entities.TableMaker;
import model.entities.Wetter;

public class Logic {

    TableMaker tableMaker;
    private Mensch mensch;
    private Wetter wetter;

    public Logic() {
        tableMaker= new TableMaker();
        mensch = new Mensch();
        wetter = new Wetter();
    }

    public void resetAllStats(){

    }

}

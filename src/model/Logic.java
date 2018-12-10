package model;

import model.abitur.datenstrukturen.List;
import model.entities.Mensch;
import model.entities.TableMaker;
import model.entities.Wetter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Logic {

    TableMaker tableMaker;
    private List<Mensch> menschen;
    private Wetter wetter;


    public Logic() {
        createEverything();
        //tableMaker= new TableMaker();

        //wetter = new Wetter();
    }

    private void createEverything() {
        createHuman();
    }

    private void createHuman() {
        menschen = new List<>();
        for (int i = 0; i < 30; i++) {
            menschen.append(new Mensch());
        }
    }

    public void resetAllStats(){

        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            Statement stmt = con.createStatement();

            stmt.execute("DELETE FROM `Zom_Menschen` WHERE `Zom_Menschen`.`hilfe` = 0");


        } catch(Exception e){
            e.printStackTrace();
        }


    }

}

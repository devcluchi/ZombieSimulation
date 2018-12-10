package model;

import model.entities.Mensch;
import model.entities.TableMaker;
import model.entities.Wetter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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

package model.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Umwelt {

    public Umwelt(){


    }


    public void generateUmwelt(){

        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            Statement stmt = con.createStatement();


            // Lege ein paar Datensätze in der Tabelle an (primary key wird ausgelassen wg. auto-inkrement => heißt aber man kann Leute auch doppelt anlegen)
            stmt.execute("INSERT INTO Zom_Zombie (Zustand) " +
                    "VALUES ('Normal');");

        } catch(Exception e){
            e.printStackTrace();
        }


    }
}
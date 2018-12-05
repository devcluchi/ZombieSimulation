package model.Entitäten;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Trinken {

    public Trinken(){


    }

    public void makeWater(){

        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            Statement stmt = con.createStatement();



            // Lege eine neue Tabelle (wirft Exception, falls Tabelle schon vorhanden)
            try {
                stmt.execute("CREATE TABLE Zom_Wasserquelle (" +
                        "wID int NOT NULL AUTO_INCREMENT," +
                        "Vorrat int NOT NULL ," +
                        "PRIMARY KEY (zID)" +
                        ");");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }

            // Lege ein paar Datensätze in der Tabelle an (primary key wird ausgelassen wg. auto-inkrement => heißt aber man kann Leute auch doppelt anlegen)
            stmt.execute("INSERT INTO Zom_Wasserquelle (Vorrat) " +
                    "VALUES (10);");

        } catch(Exception e){
            e.printStackTrace();
        }


    }
}
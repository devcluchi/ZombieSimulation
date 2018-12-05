package model.Entitäten;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Waffen {

    public Waffen(){


    }

    public void generateWaffen(){

        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            Statement stmt = con.createStatement();



            // Lege eine neue Tabelle (wirft Exception, falls Tabelle schon vorhanden)
            try {
                stmt.execute("CREATE TABLE Zom_Waffen (" +
                        "wID int NOT NULL AUTO_INCREMENT," +
                        "Bestand int," +
                        "Effektivität int, "+
                        "PRIMARY KEY (zID)" +
                        ");");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }

            // Lege ein paar Datensätze in der Tabelle an (primary key wird ausgelassen wg. auto-inkrement => heißt aber man kann Leute auch doppelt anlegen)
            stmt.execute("INSERT INTO Zom_Waffen (Bestand,Effektivität) " +
                    "VALUES (10,10);");

            ResultSet bestand = stmt.executeQuery("SELECT Bestand FROM Zom_Waffen;");

        } catch(Exception e){
            e.printStackTrace();
        }


        }







    }

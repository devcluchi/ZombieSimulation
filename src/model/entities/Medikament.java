package model.entities;

import java.sql.*;

public class Medikament {

    private int wirksamkeit;

    private int vorrat;

    private Connection con;
    private Statement stmt;


    public Medikament(){

        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
            updateInformations();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void generateMedi(){

        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            Statement stmt = con.createStatement();




            // Lege ein paar Datensätze in der Tabelle an (primary key wird ausgelassen wg. auto-inkrement => heißt aber man kann Leute auch doppelt anlegen)
            stmt.execute("INSERT INTO Zom_Medikament (wirksamkeit,Vorrat) " +
                    "VALUES ("+(int)Math.random()*10+",10);");

        } catch(Exception e){
            e.printStackTrace();
        }


    }



    public void updateInformations() throws SQLException {

        ResultSet results = stmt.executeQuery("SELECT * FROM Zom_Medikament ;");
        results.next();



            wirksamkeit = results.getInt("wirksamkeit");




            vorrat = results.getInt("Vorrat");

        }


    }

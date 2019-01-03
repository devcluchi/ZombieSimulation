package model.entities;

import java.sql.*;

public class Nutztiere {

    private int bestand;

    private int verarbeitungsqualität;

    private Connection con;
    private Statement stmt;


    public Nutztiere(){
        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
            updateInformations();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





    public void updateInformations() throws SQLException {

        ResultSet results = stmt.executeQuery("SELECT * FROM Zom_Nutztiere ;");
        results.next();



            bestand = results.getInt("Bestand");




            verarbeitungsqualität = results.getInt("Verarbeitungsqualität");

        }

    }

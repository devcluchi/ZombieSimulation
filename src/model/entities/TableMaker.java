package model.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TableMaker {

    public TableMaker(){


    }


    public void createTable(){

        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            Statement stmt = con.createStatement();

            try {

                stmt.execute("CREATE TABLE Zom_Menschen (" +
                        "meID int NOT NULL AUTO_INCREMENT," +
                        "krank bit ," +
                        "hunger int ," +
                        "durst int ," +
                        "bewaffnet bit ,"+
                        "untergekommen bit  , "+
                        "lebt bit   ,"+
                        "hilfe bit,"+
                        "PRIMARY KEY (meID)" +
                        ");");
                stmt.execute("CREATE TABLE Zom_Zombie (" +
                        "zID int NOT NULL AUTO_INCREMENT," +
                        "infiziert int NOT NULL ," +
                        "PRIMARY KEY (zID)" +
                        ");");

                stmt.execute("CREATE TABLE Zom_Wetter (" +
                        "weID int NOT NULL AUTO_INCREMENT," +
                        "Zustand varchar (255)," +
                        "PRIMARY KEY (weID)" +
                        ");");

                stmt.execute("CREATE TABLE Zom_Waffen (" +
                        "wID int NOT NULL AUTO_INCREMENT," +
                        "Bestand int," +
                        "Effektivität int, "+
                        "PRIMARY KEY (wID)" +
                        ");");

                stmt.execute("CREATE TABLE Zom_Umwelt (" +
                        "uID int NOT NULL AUTO_INCREMENT," +
                        "Zustand varchar (255)," +
                        "PRIMARY KEY (uID)" +
                        ");");

                stmt.execute("CREATE TABLE Zom_Wasserquelle (" +
                        "waID int NOT NULL AUTO_INCREMENT," +
                        "Vorrat int NOT NULL ," +
                        "PRIMARY KEY (waID)" +
                        ");");

                stmt.execute("CREATE TABLE Zom_Raubtiere (" +
                        "rID int NOT NULL AUTO_INCREMENT," +
                        "Gefrässigkeit int," +
                        "Häufigkeit int,"+
                        "PRIMARY KEY (rID)" +
                        ");");

                stmt.execute("CREATE TABLE Zom_Nutztiere (" +
                        "nID int NOT NULL AUTO_INCREMENT," +
                        "Bestand int," +
                        "Verarbeitungsqualität int,"+
                        "PRIMARY KEY (nID)" +
                        ");");

                stmt.execute("CREATE TABLE Zom_Essen (" +
                        "eID int NOT NULL AUTO_INCREMENT," +
                        "Vorrat int ," +
                        "PRIMARY KEY (eID)" +
                        ");");

                stmt.execute("CREATE TABLE Zom_Medikament (" +
                        "mID int NOT NULL AUTO_INCREMENT," +
                        "wirksamkeit int  ," +
                        "Vorrat int,"+
                        "PRIMARY KEY (mID)" +
                        ");");



            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }

        } catch(Exception e){
            e.printStackTrace();
        }




    }
}


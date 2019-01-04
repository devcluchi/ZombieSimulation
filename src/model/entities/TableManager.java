package model.entities;

import java.sql.*;

public class TableManager {
    private Connection con;
    private Statement stmt;


    public TableManager(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            createAllTable();
            fillAllTable();
        } catch (SQLException e) {
            System.err.println("Fehler beim Anlegen einer Tablle: "+e.getMessage());
        }

    }

    public void fillAllTable(){
        try {
            for (int i = 0; i < 30; i++) {
                stmt.execute("INSERT INTO Zom_Menschen (krank, hunger, durst, bewaffnet, untergekommen, lebt,hilfe) " +
                        "VALUES (0,0,4,0,0,1,0);");
            }
            stmt.execute("INSERT INTO Zom_Zombie(infiziert,lebt) "+
                    "VALUES (0,1);");

            stmt.execute("INSERT INTO Zom_Essen (Vorrat) " +
                    "VALUES (20);");

            stmt.execute("INSERT INTO Zom_Medikament (wirksamkeit,Vorrat) " +
                    "VALUES ("+(int)Math.random()*10+",10);");

            stmt.execute("INSERT INTO Zom_Nutztiere (Bestand, Verarbeitungsqualität) " +
                    "VALUES (10,10);");

            stmt.execute("INSERT INTO Zom_Raubtiere (Gefrässigkeit,Population) " +
                    "VALUES (10,10);");

            stmt.execute("INSERT INTO Zom_Wasserquelle (Vorrat) " +
                    "VALUES (10);");

            stmt.execute("INSERT INTO Zom_Umwelt (Zustand) " +
                    "VALUES ('Normal');");

            stmt.execute("INSERT INTO Zom_Waffen (Bestand, Effektivität) " +
                    "VALUES (10,10);");


            stmt.execute("INSERT INTO Zom_Wetter (Zustand) " +
                    "VALUES ('Sonnig');");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAllTable() throws SQLException {
        createHuman();
        createZombies();
        createWeather();
        createWeapons();
        createEnviroment();
        createWaterspots();
        createPredators();
        createAnimals();
        createFood();
        createMedicines();
    }

    public void dropAllTable() throws SQLException {
        stmt.execute("DROP TABLE Zom_Menschen");
        stmt.execute("DROP TABLE Zom_Zombie");
        stmt.execute("DROP TABLE Zom_Wetter");
        stmt.execute("DROP TABLE Zom_Waffen");
        stmt.execute("DROP TABLE Zom_Umwelt");
        stmt.execute("DROP TABLE Zom_Wasserquelle");
        stmt.execute("DROP TABLE Zom_Raubtiere");
        stmt.execute("DROP TABLE Zom_Nutztiere");
        stmt.execute("DROP TABLE Zom_Essen");
        stmt.execute("DROP TABLE Zom_Medikament");

    }

    public void createHuman() throws SQLException {
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
    }

    public void createZombies() throws SQLException {
        stmt.execute("CREATE TABLE Zom_Zombie (" +
                "zID int NOT NULL AUTO_INCREMENT," +
                "infiziert int," +
                "lebt bit," +
                "PRIMARY KEY (zID)" +
                ");");
    }

    public void createWeather() throws SQLException {
        stmt.execute("CREATE TABLE Zom_Wetter (" +
                "weID int NOT NULL AUTO_INCREMENT," +
                "Zustand varchar (255)," +
                "PRIMARY KEY (weID)" +
                ");");

    }

    public void createWeapons() throws SQLException {
        stmt.execute("CREATE TABLE Zom_Waffen (" +
                "wID int NOT NULL AUTO_INCREMENT," +
                "Bestand int," +
                "Effektivität int, "+
                "PRIMARY KEY (wID)" +
                ");");
    }

    public void createEnviroment() throws SQLException {
        stmt.execute("CREATE TABLE Zom_Umwelt (" +
                "uID int NOT NULL AUTO_INCREMENT," +
                "Zustand varchar (255)," +
                "PRIMARY KEY (uID)" +
                ");");
    }

    public void createWaterspots() throws SQLException {
        stmt.execute("CREATE TABLE Zom_Wasserquelle (" +
                "waID int NOT NULL AUTO_INCREMENT," +
                "Vorrat int NOT NULL ," +
                "PRIMARY KEY (waID)" +
                ");");
    }

    public void createPredators() throws SQLException{
        stmt.execute("CREATE TABLE Zom_Raubtiere (" +
                "rID int NOT NULL AUTO_INCREMENT," +
                "Gefrässigkeit int," +
                "Population int,"+
                "PRIMARY KEY (rID)" +
                ");");
    }

    public void createAnimals() throws SQLException{
        stmt.execute("CREATE TABLE Zom_Nutztiere (" +
                "nID int NOT NULL AUTO_INCREMENT," +
                "Bestand int," +
                "Verarbeitungsqualität int,"+
                "PRIMARY KEY (nID)" +
                ");");
    }

    public void createFood() throws SQLException{
        stmt.execute("CREATE TABLE Zom_Essen (" +
                "eID int NOT NULL AUTO_INCREMENT," +
                "Vorrat int ," +
                "PRIMARY KEY (eID)" +
                ");");

    }

    public void createMedicines() throws SQLException{
        stmt.execute("CREATE TABLE Zom_Medikament (" +
                "mID int NOT NULL AUTO_INCREMENT," +
                "wirksamkeit int  ," +
                "Vorrat int,"+
                "PRIMARY KEY (mID)" +
                ");");
    }

    public Statement getStmt() {
        return stmt;
    }


}


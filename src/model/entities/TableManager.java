package model.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {
    private Connection con;
    private Statement stmt;
    private boolean humanTableFilled,zombieTableFilled,weatherTableFilled,weaponsTableFilled,enviromentTableFilled,waterspotsTableFilled,predatorsTableFilled,animalTableFilled,foodTableFilled,medicinesTableFilled;


    public TableManager(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createAllTable();
        if (testAllTablesFilled()) {
            System.out.println("Die Tabellen sind schön befüllt");
        }else {
            fillAllTable();
        }
    }

    private boolean testAllTablesFilled() {
        if (humanTableFilled && zombieTableFilled && weatherTableFilled && weaponsTableFilled && enviromentTableFilled && waterspotsTableFilled && predatorsTableFilled && animalTableFilled && foodTableFilled && medicinesTableFilled){
            return true;
        }
        return false;
    }


    public void fillAllTable(){
        try {
            for (int i = 0; i < 30; i++) {
                stmt.execute("INSERT INTO Zom_Menschen (krank, hunger, durst, bewaffnet, untergekommen, lebt,hilfe) " +
                        "VALUES (2,0,0,0,0,1,0);");
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
            System.err.println("Fehler beim Befüllen der Tabllen: "+e.getMessage());
        }
    }

    public void createAllTable() {
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

    public void dropAllTable() {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createHuman() {
        try {
            stmt.execute("CREATE TABLE Zom_Menschen (" +
                    "meID int NOT NULL AUTO_INCREMENT," +
                    "krank int ," +
                    "hunger int ," +
                    "durst int ," +
                    "bewaffnet bit ,"+
                    "untergekommen bit  , "+
                    "lebt bit   ,"+
                    "hilfe bit,"+
                    "PRIMARY KEY (meID)" +
                    ");");
        } catch (SQLException e) {
            System.err.println("Fehler beim Anlegen einer Tablle: "+e.getMessage());
            humanTableFilled = true;
        }
    }

    public void createZombies() {
        try {
            stmt.execute("CREATE TABLE Zom_Zombie (" +
                    "zID int NOT NULL AUTO_INCREMENT," +
                    "infiziert int," +
                    "lebt bit," +
                    "PRIMARY KEY (zID)" +
                    ");");
        } catch (SQLException e) {
            System.err.println("Fehler beim Anlegen einer Tablle: "+e.getMessage());
            zombieTableFilled = true;
        }
    }

    public void createWeather() {
        try {
            stmt.execute("CREATE TABLE Zom_Wetter (" +
                    "weID int NOT NULL AUTO_INCREMENT," +
                    "Zustand varchar (255)," +
                    "PRIMARY KEY (weID)" +
                    ");");
        } catch (SQLException e) {
            System.err.println("Fehler beim Anlegen einer Tablle: "+e.getMessage());
            weatherTableFilled = true;
        }

    }

    public void createWeapons() {
        try {
            stmt.execute("CREATE TABLE Zom_Waffen (" +
                    "wID int NOT NULL AUTO_INCREMENT," +
                    "Bestand int," +
                    "Effektivität int, "+
                    "PRIMARY KEY (wID)" +
                    ");");
        } catch (SQLException e) {
            System.err.println("Fehler beim Anlegen einer Tablle: "+e.getMessage());
            weaponsTableFilled = true;
        }
    }

    public void createEnviroment() {
        try {
            stmt.execute("CREATE TABLE Zom_Umwelt (" +
                    "uID int NOT NULL AUTO_INCREMENT," +
                    "Zustand varchar (255)," +
                    "PRIMARY KEY (uID)" +
                    ");");
        } catch (SQLException e) {
            System.err.println("Fehler beim Anlegen einer Tablle: "+e.getMessage());
            enviromentTableFilled = true;
        }
    }

    public void createWaterspots() {
        try {
            stmt.execute("CREATE TABLE Zom_Wasserquelle (" +
                    "waID int NOT NULL AUTO_INCREMENT," +
                    "Vorrat int NOT NULL ," +
                    "PRIMARY KEY (waID)" +
                    ");");
        } catch (SQLException e) {
            System.err.println("Fehler beim Anlegen einer Tablle: "+e.getMessage());
            waterspotsTableFilled = true;
        }
    }

    public void createPredators() {
        try {
            stmt.execute("CREATE TABLE Zom_Raubtiere (" +
                    "rID int NOT NULL AUTO_INCREMENT," +
                    "Gefrässigkeit int," +
                    "Population int,"+
                    "PRIMARY KEY (rID)" +
                    ");");
        } catch (SQLException e) {
            System.err.println("Fehler beim Anlegen einer Tablle: "+e.getMessage());
            predatorsTableFilled = true;
        }
    }

    public void createAnimals() {
        try {
            stmt.execute("CREATE TABLE Zom_Nutztiere (" +
                    "nID int NOT NULL AUTO_INCREMENT," +
                    "Bestand int," +
                    "Verarbeitungsqualität int,"+
                    "PRIMARY KEY (nID)" +
                    ");");
        } catch (SQLException e) {
            System.err.println("Fehler beim Anlegen einer Tablle: "+e.getMessage());
            animalTableFilled = true;
        }
    }

    public void createFood(){
        try {
            stmt.execute("CREATE TABLE Zom_Essen (" +
                    "eID int NOT NULL AUTO_INCREMENT," +
                    "Vorrat int ," +
                    "PRIMARY KEY (eID)" +
                    ");");
        } catch (SQLException e) {
            System.err.println("Fehler beim Anlegen einer Tablle: "+e.getMessage());
            foodTableFilled = true;
        }

    }

    public void createMedicines(){
        try {
            stmt.execute("CREATE TABLE Zom_Medikament (" +
                    "mID int NOT NULL AUTO_INCREMENT," +
                    "wirksamkeit int  ," +
                    "Vorrat int,"+
                    "PRIMARY KEY (mID)" +
                    ");");
        } catch (SQLException e) {
            System.err.println("Fehler beim Anlegen einer Tablle: "+e.getMessage());
            medicinesTableFilled = true;
        }
    }

    public Statement getStmt() {
        return stmt;
    }


}


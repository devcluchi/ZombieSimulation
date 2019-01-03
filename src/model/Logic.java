package model;

import model.abitur.datenstrukturen.List;
import model.entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Logic {

    TableManager tableManager;
    private List<Mensch> menschen;
    private List<Zombie> zombies;
    private Waffen waffen;
    private Trinken trinken;
    private Umwelt umwelt;
    private Wetter wetter;
    private Raubtiere raubtiere;
    private Nutztiere nutztiere;
    private Medikament medikament;
    private Essen essen;

    private int lebendeMenschen;
    private int lebendeZombies;


    public Logic() {
        tableManager= new TableManager();
        menschen = new List<>();
        zombies = new List<>();
        waffen = new Waffen();
        trinken = new Trinken();
        umwelt = new Umwelt();
        wetter = new Wetter();
        raubtiere = new Raubtiere();
        nutztiere = new Nutztiere();
        medikament = new Medikament();
        essen = new Essen();
        System.out.println("Tabellen angelegt und befüllt");
        recieveAllInformation();






        //Test
        menschen.toFirst();
        while (menschen.hasAccess()){
            System.out.println(menschen.getContent().getId()+" - "+menschen.getContent().isLebt());
            menschen.next();
        }


    }

    private void recieveAllInformation(){
        try {
            recieveHumanInformation();
            recieveZombieInformation();
            recieveWaffenInformation();
            recieveTrinkenInformation();
            recieveEssenInformation();
            recieveMedikamenteInformation();
            recieveNutztiereInformation();
            recieveRaubtiereInformation();
            recieveUmweltInformation();
            recieveWetterInformation();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void countZombies() throws SQLException {
        ResultSet countZombies = tableManager.getStmt().executeQuery("SELECT COUNT(zID) FROM Zom_Zombie;");
        countZombies.next();
        lebendeZombies = countZombies.getInt(1);
    }

    private void countHuman() throws SQLException {
        ResultSet countHuman = tableManager.getStmt().executeQuery("SELECT COUNT(meID) FROM Zom_Menschen;");
        countHuman.next();
        lebendeMenschen = countHuman.getInt(1);
    }


    public void resetAllStats() {

        try {
            tableManager.dropAllTable();
            System.out.println("Alle Tabellen gelöscht");
        } catch (SQLException e) {
            System.err.println("Tabellen konnten nicht gelöscht werden: "+e.getMessage());
        }
    }

    private void recieveHumanInformation() throws SQLException {

        ResultSet results = tableManager.getStmt().executeQuery("SELECT * FROM Zom_Menschen;");
        while (results.next()){
            menschen.append(new Mensch(results.getInt("meID")));
        }
    }

    private void recieveZombieInformation() throws SQLException {
        ResultSet results = tableManager.getStmt().executeQuery("SELECT * FROM Zom_Zombie;");
        while (results.next()){
            zombies.append(new Zombie(results.getInt("zID")));
        }
    }

    private void recieveEssenInformation() throws SQLException {

        ResultSet results = tableManager.getStmt().executeQuery("SELECT * FROM Zom_Essen;");

    }

    private void recieveWaffenInformation() throws SQLException {

        ResultSet results = tableManager.getStmt().executeQuery("SELECT * FROM Zom_Waffen;");

    }

    private void recieveWetterInformation() throws SQLException {

        ResultSet results = tableManager.getStmt().executeQuery("SELECT * FROM Zom_Wetter;");

    }

    private void recieveUmweltInformation() throws SQLException {

        ResultSet results = tableManager.getStmt().executeQuery("SELECT * FROM Zom_Umwelt;");

    }

    private void recieveTrinkenInformation() throws SQLException {

        ResultSet results = tableManager.getStmt().executeQuery("SELECT * FROM Zom_Wasserquelle;");

    }

    private void recieveNutztiereInformation() throws SQLException {

        ResultSet results = tableManager.getStmt().executeQuery("SELECT * FROM Zom_Nutztiere;");

    }

    private void recieveRaubtiereInformation() throws SQLException {

        ResultSet results = tableManager.getStmt().executeQuery("SELECT * FROM Zom_Raubtiere;");

    }

    private void recieveMedikamenteInformation() throws SQLException {

        ResultSet results = tableManager.getStmt().executeQuery("SELECT * FROM Zom_Medikament;");

    }


    private TableManager getTableManager() {
        return tableManager;
    }

    public void updateAllInformation() {
        try {
            updateHumanInformation();
            updateZombieInformation();
            updateWaffenInformation();
            updateEssenInformation();
            updateMedikamenteInformation();
            updateNutztiereInformation();
            updateRaubtiereInformation();
            updateTrinkenInformation();
            updateWetterInformation();
            updateUmweltInformation();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateZombieInformation() throws SQLException {
        zombies.toFirst();
        while (zombies.hasAccess()){
            zombies.getContent().updateInformations();
            zombies.next();
        }
        countZombies();

    }

    private void updateHumanInformation() throws SQLException {
        menschen.toFirst();
        while (menschen.hasAccess()){
            menschen.getContent().updateInformations();
            menschen.next();
        }
        countHuman();
    }



    private void updateWetterInformation() throws SQLException {

        wetter.updateInformations();
    }



    private void updateWaffenInformation() throws SQLException {

        waffen.updateInformations();
    }



    private void updateUmweltInformation() throws SQLException {

        umwelt.updateInformations();

    }



    private void updateTrinkenInformation() throws SQLException {

        trinken.updateInformations();
    }


    private void updateRaubtiereInformation() throws SQLException {

        raubtiere.updateInformations();

    }


    private void updateNutztiereInformation() throws SQLException {


        nutztiere.updateInformations();

    }


    private void updateMedikamenteInformation() throws SQLException {

        medikament.updateInformations();

    }


    private void updateEssenInformation() throws SQLException {

        essen.updateInformations();

    }

    public int getLebendeMenschen() {
        try {
            countHuman();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lebendeMenschen;
    }

    public int getLebendeZombies() {
        try {
            countZombies();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lebendeZombies;
    }

    public void tryToFeedHuman() {
        for (int j = 0; j < lebendeZombies; j++) {
            int random = (int) (Math.random()*100+1);
            if(random<20){
                System.out.println(lebendeMenschen);
                int randomHuman = (int) (Math.random()*lebendeMenschen)+1;
                menschen.toFirst();
                for (int i = 1; i <= randomHuman; i++) {
                    menschen.next();
                }
                killHuman(randomHuman+1);
                addZombie();
                /**
                 * Die untere Try-Catch ist nur zum Probieren
                 */
                try {
                    int infizierteMenschen = infizierteMenschen(randomHuman);
                    tableManager.getStmt().execute("UPDATE Zom_Zombie SET infiziert = "+infizierteMenschen+1+", lebt = 1 WHERE Zom_Zombie.zID = 1;");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                updateAllInformation();
            }
        }
    }

    private int infizierteMenschen(int id) throws SQLException {
        ResultSet result= tableManager.getStmt().executeQuery("SELECT infiziert FROM Zom_Zombie WHERE Zom_Zombie.zID = "+id+";");
        result.next();
        return result.getInt(1);

    }

    private void killHuman(int humanID) {
        try {
            tableManager.getStmt().execute("DELETE FROM Zom_Menschen WHERE Zom_Menschen.meID = "+humanID+";");
            menschen.remove();
        } catch (SQLException e) {


        }
    }

    private void addZombie(){
        try {
            tableManager.getStmt().execute("INSERT INTO Zom_Zombie(infiziert,lebt) "+
                    "VALUES (0,1);");
            zombies.append(new Zombie(lebendeZombies+1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void humanTryToGetWeapon(){

        if(waffen.getBestand() > 0){

            try {
                int bestand = waffen.getBestand() - 1;
                tableManager.getStmt().execute("UPDATE Zom_Waffen SET bestand = "+bestand+";");

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }


    }

    public void useWater(){

        if(trinken.getVorrat() > 0){

            try {
                int vorrat = trinken.getVorrat() - 1;
                tableManager.getStmt().execute("UPDATE Zom_Wasserquelle SET Vorrat = "+vorrat+";");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void eat(){

        if(essen.getVorrat() > 0){

            try {
                int vorrat = essen.getVorrat() - 1;
                tableManager.getStmt().execute("UPDATE Zom_Essen SET Vorrat = "+vorrat+";");

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    }

    public void useMedi(){

        if(medikament.getVorrat() > 0){

            try {
                int vorrat = essen.getVorrat() - 1;
                tableManager.getStmt().execute("UPDATE Zom_Medikament SET Vorrat = "+vorrat+";");

            } catch (SQLException e) {
                e.printStackTrace();
            }



        }

    }


}

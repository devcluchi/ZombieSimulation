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




    public Logic() {
        tableManager= new TableManager();
        menschen = new List<>();
        zombies = new List<>();
        /*waffen = new Waffen();
        trinken = new Trinken();
        umwelt = new Umwelt();
        wetter = new Wetter();
        raubtiere = new Raubtiere();
        nutztiere = new Nutztiere();
        medikament = new Medikament();
        essen = new Essen();*/
        System.out.println("Tabellen angelegt und befüllt");
        recieveAllInformation();
        try {
            ResultSet count = tableManager.getStmt().executeQuery("SELECT COUNT(meID) FROM Zom_Menschen;");
            count.next();
            lebendeMenschen = count.getInt(1);
            System.out.println(lebendeMenschen);
        } catch (SQLException e) {
            e.printStackTrace();
        }







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
            /*recieveEssenInformation();
            recieveMedikamenteInformation();
            recieveNutztiereInformation();
            recieveRaubtiereInformation();
            recieveUmweltInformation();
            recieveWaffenInformation();
            recieveWetterInformation();
            recieveTrinkenInformation();*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            /*updateEssenInformation();
            updateMedikamenteInformation();
            updateNutztiereInformation();
            updateRaubtiereInformation();
            updateTrinkenInformation();
            updateWaffenInformation();
            updateWetterInformation();
            updateUmweltInformation();*/
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
    }

    private void updateHumanInformation() throws SQLException {
        menschen.toFirst();
        while (menschen.hasAccess()){
            menschen.getContent().updateInformations();
            menschen.next();
        }
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
        return lebendeMenschen;
    }
}

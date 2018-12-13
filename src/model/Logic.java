package model;

import model.abitur.datenstrukturen.List;
import model.entities.Mensch;
import model.entities.TableManager;
import model.entities.Zombie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Logic {

    TableManager tableManager;
    private List<Mensch> menschen;
    private List<Zombie> zombies;


    public Logic() {
        tableManager= new TableManager();
        menschen = new List<>();
        zombies = new List<>();
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
        ResultSet results = tableManager.getStmt().executeQuery("SELECT * FROM Zom_Zombies;");
        while (results.next()){
            zombies.append(new Zombie(results.getInt("zID")));
        }
    }


    private TableManager getTableManager() {
        return tableManager;
    }

    public void updateAllInformation() {
        try {
            updateHumanInformation();
            updateZombieInformation();
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
}

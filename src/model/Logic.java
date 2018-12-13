package model;

import model.abitur.datenstrukturen.List;
import model.entities.Mensch;
import model.entities.TableManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Logic {

    TableManager tableManager;
    private List<Mensch> menschen;


    public Logic() {
        tableManager= new TableManager();
        menschen = new List<>();
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

    private TableManager getTableManager() {
        return tableManager;
    }

    public void updateAllInformation() {
        try {
            updateHumanInformation();
        } catch (SQLException e) {
            e.printStackTrace();
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

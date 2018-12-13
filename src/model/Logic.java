package model;

import model.abitur.datenstrukturen.List;
import model.entities.Mensch;
import model.entities.TableManager;
import model.entities.Wetter;

import java.sql.SQLException;

public class Logic {

    TableManager tableManager;
    private List<Mensch> menschen;
    private Wetter wetter;


    public Logic() {
        tableManager= new TableManager();
        fillAllTables();
        System.out.println("Tabellen angelegt und befüllt");


        //wetter = new Wetter();
    }

    public void fillAllTables() {
        fillHuman();
    }

    private void fillHuman() {
        menschen = new List<>();
        for (int i = 0; i < 30; i++) {
            menschen.append(new Mensch());
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

    public TableManager getTableManager() {
        return tableManager;
    }
}

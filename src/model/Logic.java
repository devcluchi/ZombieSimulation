package model;

import model.abitur.datenstrukturen.List;
import model.entities.Mensch;
import model.entities.TableManager;
import model.entities.Wetter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Logic {

    TableManager tableManager;
    private List<Mensch> menschen;
    private Wetter wetter;


    public Logic() {
        try {
            tableManager= new TableManager();
            createEverything();
            System.out.println("Tabellen angelegt und befüllt");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //wetter = new Wetter();
    }

    private void createEverything() {
        createHuman();
    }

    private void createHuman() {
        menschen = new List<>();
        for (int i = 0; i < 30; i++) {
            menschen.append(new Mensch());
        }
    }

    public void resetAllStats() {

        try {
            tableManager.dropAllTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

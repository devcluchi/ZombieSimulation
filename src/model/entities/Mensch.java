package model.entities;

import java.sql.*;

public class Mensch {

    private boolean bewaffnet, untergekommen, lebt, hilfe;
    private int krankheit, hunger, durst, id;

    private Connection con;
    private Statement stmt;

    public Mensch(int id){
        this.id = id;
        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
            updateInformations();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void bewaffnen(){

        if(bewaffnet){

            bewaffnet = false;
        }else{
            bewaffnet = true;
        }

    }

    public void MediNutzen(){


    }


    public void RessourcenNutzung(){


    }


    public void NutztiereVerarbeiten(){


    }


    public void UnterkunftSuchen(){



    }



    public boolean isBewaffnet() {
        return bewaffnet;
    }

    public boolean isUntergekommen() {
        return untergekommen;
    }

    public boolean isLebt() {
        return lebt;
    }

    public boolean isHilfe() {
        return hilfe;
    }

    public int getKrankheit() {
        return krankheit;
    }

    public int getHunger() {
        return hunger;
    }

    public int getDurst() {
        return durst;
    }

    public int getId() {
        return id;
    }

    public void updateInformations() throws SQLException {
            ResultSet results = stmt.executeQuery("SELECT * FROM Zom_Menschen WHERE meID="+id+";");
            results.next();

            if(results.getString("untergekommen") == "0"){
                untergekommen=false;
            }else {
                untergekommen= true;
            }
            if(results.getString("lebt") == "0"){
                lebt=false;
            }else {
                lebt= true;
            }
            if(results.getString("hilfe") == "0"){
                hilfe=false;
            }else {
                hilfe= true;
            }
            krankheit = results.getInt("krank");
            hunger = results.getInt("hunger");
            durst = results.getInt("durst");
    }
}

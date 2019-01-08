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
        recieveAllInformation();
    }

    //region Informations-Methoden
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

    private void countZombies() throws SQLException {
        ResultSet countZombies = tableManager.getStmt().executeQuery("SELECT COUNT(zID) FROM Zom_Zombie WHERE lebt = 1;");
        countZombies.next();
        lebendeZombies = countZombies.getInt(1);
    }

    private void countHuman() throws SQLException {
        ResultSet countHuman = tableManager.getStmt().executeQuery("SELECT COUNT(meID) FROM Zom_Menschen WHERE lebt = 1;");
        countHuman.next();
        lebendeMenschen = countHuman.getInt(1);
    }

    //endregion

    //region Update-Methoden
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
    //endregion

    //region Methoden für den Main-Screen
    public void resetAllStats() {
        try {
            tableManager.dropAllTable();
            System.out.println("Alle Tabellen gelöscht");
            while (!menschen.isEmpty()){
                menschen.remove();
                menschen.toFirst();
            }
            while (!zombies.isEmpty()){
                zombies.remove();
                zombies.toFirst();
            }
            tableManager.createAllTable();
            tableManager.fillAllTable();
            recieveAllInformation();
            updateAllInformation();
            countZombies();
            countHuman();
        } catch (SQLException e) {
            System.err.println("Tabellen konnten nicht gelöscht werden: "+e.getMessage());
        }
    }

    public void tryToFeedHuman() {
        for (int j = 0; j < lebendeZombies; j++) {
            int random = (int) (Math.random()*100+1)- lebendeZombies;

            if(random<=20 && lebendeMenschen>0){

                int randomHuman = (int) (Math.random()*lebendeMenschen)+1;

                menschen.toFirst();
                for (int i = 2; i <= randomHuman; i++) {
                    menschen.next();
                }
                if(menschen.getContent().isLebt()) {
                    addZombie();
                }
                killHuman(menschen.getContent().getId());
                try {
                    int infizierteMenschen = getInfizierteMenschenVonZombie(j+1);

                    tableManager.getStmt().execute("UPDATE Zom_Zombie SET infiziert = "+infizierteMenschen+1+", lebt = 1 WHERE Zom_Zombie.zID = 1;");

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    countHuman();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void humanTryToGetWeapon(){

        int random = (int)(Math.random()*11)+1;
        if(random <= 4){
            try {
                int bestand = waffen.getBestand() + 1;
                tableManager.getStmt().execute("UPDATE Zom_Waffen SET bestand = "+bestand+";");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void menschenBeduerfnisse(){
        menschen.toFirst();
        try {
            int durst = menschen.getContent().getDurst() + 1;
            tableManager.getStmt().execute("UPDATE Zom_Menschen SET durst = " + durst + ";");
            int hunger = menschen.getContent().getHunger() + 1;
            tableManager.getStmt().execute("UPDATE Zom_Menschen SET hunger = " + hunger + ";");

            tableManager.getStmt().execute("UPDATE Zom_Menschen SET lebt = 0 WHERE Zom_Menschen.durst >= 5;");
            tableManager.getStmt().execute("UPDATE Zom_Menschen SET lebt = 0 WHERE Zom_Menschen.hunger >= 8;");

            System.out.println("durst " +menschen.getContent().getDurst());
            System.out.println("hunger " +menschen.getContent().getHunger());

            if(hunger >= 8 && durst >= 5 ){

                killHuman(menschen.getContent().getId());

            }

            updateHumanInformation();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        //Hab das jetzt mit der lebt Variable gelöst kannst du ändern wie du es willst oder wir löschen die nicht wie du es vor hattest sondern setzen die Variable
    }

    public void krankWerden(){
        menschen.toFirst();
        int random = (int) (Math.random()*11) + 1;
        if(random == 4){
            try {
                int krank = menschen.getContent().getKrankheit() + 1;
                tableManager.getStmt().execute("UPDATE Zom_Menschen SET krank = " + krank + ";");
                tableManager.getStmt().execute("UPDATE Zom_Menschen SET lebt = 0 WHERE Zom_Menschen.krank >= 3;");
                updateHumanInformation();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(random == 6){
            try {

                int krank = menschen.getContent().getKrankheit() + 2;
                tableManager.getStmt().execute("UPDATE Zom_Menschen SET krank = " + krank + ";");
                tableManager.getStmt().execute("UPDATE Zom_Menschen SET lebt = 0 WHERE Zom_Menschen.krank >= 3;");
                updateHumanInformation();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void useAnimal() {

        if (nutztiere.getBestand() > 0){
            try {
                int bestand = nutztiere.getBestand() - 1;
                tableManager.getStmt().execute("UPDATE Zom_Nutztiere SET Bestand = " + bestand + ";");

                int vorratW = trinken.getVorrat() + nutztiere.getVerarbeitungsqualität() / 3;
                tableManager.getStmt().execute("UPDATE Zom_Wasserquelle SET Vorrat = " + vorratW + ";");

                int vorratE = essen.getVorrat() + nutztiere.getVerarbeitungsqualität()/2;
                tableManager.getStmt().execute("UPDATE Zom_Essen SET Vorrat = "+vorratE+";");
                updateTrinkenInformation();
                updateEssenInformation();
                updateNutztiereInformation();


            } catch (SQLException e) {
                e.printStackTrace();
            }


        }




    }

    public void useWater() {
        if (trinken.getVorrat() > 0) {
            try {
                int vorrat = trinken.getVorrat() - 1;
                tableManager.getStmt().execute("UPDATE Zom_Wasserquelle SET Vorrat = " + vorrat + ";");
                trinken.updateInformations();
                System.out.println("Wasser "+ trinken.getVorrat());

            } catch (SQLException e) {
                e.printStackTrace();
            }
            menschen.toFirst();
            if (menschen.getContent().getDurst() >= 0 && menschen.getContent().isLebt()) {
                try {
                    int durst = menschen.getContent().getDurst() - 1;
                    tableManager.getStmt().execute("UPDATE Zom_Menschen SET durst = " + durst + ";");
                    menschen.getContent().updateInformations();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                menschen.next();
                System.out.println("die" + menschen.getContent().getDurst());
            }
        }
    }

    public void eat(){
        if(essen.getVorrat() > 0){
            try {
                int vorrat = essen.getVorrat() - 1;
                tableManager.getStmt().execute("UPDATE Zom_Essen SET Vorrat = "+vorrat+";");
                essen.updateInformations();
                System.out.println("Essen "+ essen.getVorrat());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        menschen.toFirst();
        if (menschen.getContent().getHunger() >= 0 && menschen.getContent().isLebt()) {
            try {
                int hunger = menschen.getContent().getHunger() - 1;
                tableManager.getStmt().execute("UPDATE Zom_Menschen SET hunger = " + hunger + ";");
                menschen.getContent().updateInformations();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            menschen.next();

        }
    }

    public void useMedi(){
        if(medikament.getVorrat() > 0){
            try {
                int vorrat = medikament.getVorrat() - 1;
                tableManager.getStmt().execute("UPDATE Zom_Medikament SET Vorrat = "+vorrat+";");
                medikament.updateInformations();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        menschen.toFirst();
        if (menschen.getContent().getKrankheit() >= 0 && menschen.getContent().isLebt()) {
            try {
                int krank = menschen.getContent().getKrankheit() - 1;
                tableManager.getStmt().execute("UPDATE Zom_Menschen SET krank = " + krank + ";");
                menschen.getContent().updateInformations();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            menschen.next();

        }
    }

    private void killHuman(int humanID) {
        try {
            tableManager.getStmt().execute("UPDATE Zom_Menschen SET lebt = 0 WHERE Zom_Menschen.meID = "+humanID+" ;");
            updateHumanInformation();
            countHuman();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addHuman() {
        try {
            tableManager.getStmt().execute("INSERT INTO Zom_Menschen (krank, hunger, durst, bewaffnet, untergekommen, lebt,hilfe) " +
                            "VALUES (2,0,0,0,0,1,0);");
            menschen.append(new Mensch(lebendeMenschen+1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void killZombie(int zombieID) {
        try {
            tableManager.getStmt().execute("UPDATE Zom_Zombie SET lebt = 0 WHERE Zom_Zombie.zID = "+zombieID+" ;");
            updateZombieInformation();
            countZombies();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
    //endregion

    //region get-Methoden
    private TableManager getTableManager() {
        return tableManager;
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

    public int getWaffenVorrat(){

       int waffenVorrat = waffen.getBestand();

       return waffenVorrat;

    }

    private int getInfizierteMenschenVonZombie(int id) throws SQLException {
        ResultSet result= tableManager.getStmt().executeQuery("SELECT infiziert FROM Zom_Zombie WHERE Zom_Zombie.zID = "+id+";");
        result.next();
        return result.getInt("infiziert");
    }


    public void animalLife(){

        int life = (int)(Math.random()*11)+1;

        if(life <= 5 ){
            try {

                int population = raubtiere.getPopulation() - 1;
                tableManager.getStmt().execute("UPDATE Zom_Raubtiere SET Population = " + population + ";");
                raubtiere.updateInformations();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{

            try {

                int population = raubtiere.getPopulation() + 1;
                tableManager.getStmt().execute("UPDATE Zom_Raubtiere SET Population = " + population + ";");
                raubtiere.updateInformations();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if(life <= 3){

            try {

                int bestand = nutztiere.getBestand() + life;
                tableManager.getStmt().execute("UPDATE Zom_Nutztiere SET Bestand = " + bestand + ";");
                nutztiere.updateInformations();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    }


    public void animalFight(){

        try {

            if(raubtiere.getPopulation() > 0) {

                int hunger = (int) (Math.random() * 11) + 1;
                tableManager.getStmt().execute("UPDATE Zom_Raubtiere SET Gefrässigkeit = " + hunger + ";");

                int bestand = nutztiere.getBestand() - raubtiere.getGefraessigkeit() / 2;
                System.out.println(nutztiere.getBestand());
                tableManager.getStmt().execute("UPDATE Zom_Nutztiere SET Bestand = " + bestand + ";");

                nutztiere.updateInformations();
                raubtiere.updateInformations();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }





    }

    public void tryToKillZombie(){
        if(waffen.getBestand()>0){
            int random = (int) (Math.random()*100+1);
            if(random<= waffen.getEffektivität()&& lebendeZombies>0) {
                int randomZombie = (int) (Math.random() * lebendeZombies) + 1;
                zombies.toFirst();
                for (int i = 2; i <= randomZombie; i++) {
                    zombies.next();
                }
                killZombie(zombies.getContent().getId());
                System.out.println("Waffen "+ waffen.getBestand());
                try {
                    int waffenBestand = waffen.getBestand()-1;
                    //System.out.println("zombie: "+(j+1));
                    tableManager.getStmt().execute("UPDATE Zom_Waffen SET Bestand = "+waffenBestand+", Effektivität = "+waffen.getEffektivität()+";");
                    updateWaffenInformation();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //endregion
}

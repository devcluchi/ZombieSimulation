package model.entities;

import java.sql.*;

public class Zombie {

    private int id, infiziert;
    private boolean lebt;

    private Connection con;
    private Statement stmt;

    public Zombie(int id){
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

    public void updateInformations() throws SQLException {
        ResultSet results = stmt.executeQuery("SELECT * FROM Zom_Zombie WHERE zID="+id+";");
        results.next();
        if(results.getString("lebt") == "0"){
            lebt=false;
        }else {
            lebt= true;
        }
        infiziert = results.getInt("infiziert");
    }

    public int getId() {
        return id;
    }
}

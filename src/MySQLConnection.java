import java.sql.*;

public class MySQLConnection {

    public static void main(String[] args) {
        // Infos zum Datenbankzugriff
        String dbHost = "localhost";
        String dbPort = "3306";
        String dbName = "testdb";
        String dbUser = "root";
        String dbPasswort = "";
        try
        {   // (1) Verbindung zur Datenbank herstellen
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url = "jdbc:mysql://" + dbHost + "/" + dbName;
            Connection verbindung =
                    DriverManager.getConnection(url, dbUser, dbPasswort);
            System.out.println("Verbindung erfolgreich aufgebaut!");
            // (2) Datenbank abfragen
            Statement stmt = verbindung.createStatement();
            ResultSet daten = stmt.executeQuery
                    ("SELECT * FROM artikel ORDER BY bezeichnung;");
            while (daten.next())
            {
                System.out.println(daten.getString(1) + ", "
                        + daten.getString("ean") + ", "
                        + daten.getInt("lagerbestand")
                        + daten.getDouble("verkaufspreis"));
            }
            stmt.close();
            daten.close();
            // (3) Verbindung schließen
            verbindung.close();
            System.out.println("Verbindung geschlossen!");
        } catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
        }
    }


}

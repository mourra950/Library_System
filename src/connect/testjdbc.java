package connect;
import Classes.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class testjdbc {

    public static void connect(String a) {
        Connection conn = null;
        try {

            String url = "jdbc:sqlite:src/DB/LibraryDB.db";
            conn = DriverManager.getConnection(url);
            Statement s = conn.createStatement();

            //INSERT INTO `sql11418209`.`signup` (`mail`, `Name`, `Surname`, `Password`, `address`,`gender`) VALUES ('"+s1+"','"+s2+"', '"+s3+"', '"+s4+"', '"+s5+"','"+s6+"');"
            //User user = new User("mohamed","mostafa ragab","010002222222");
            // s.executeUpdate("INSERT INTO `main`.`Users`(`Mail`,`Name`,`Password`) VALUES ('omar',NULL,NULL);");
            //"UPDATE `main`.`User` SET `Borrowed Books` = '" + k + "' WHERE (`Name` = '" + Name + "');"
            s.executeUpdate(a);
            conn.close();

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {

    }
}



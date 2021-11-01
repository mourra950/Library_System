package Classes;
import java.sql.*;
import java.time.LocalDate;
public class Payment {

    public Payment(){

    }

    public int DeadLineCounter(User k) throws SQLException{
        int counter =0;
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select * from Date");
        while (rs.next()) {

            if (rs.getString(1).equals(k.getPersonId()) ) {
                String e = rs.getString(4);
                LocalDate DeadLine = LocalDate.parse(e);
                if (DeadLine.isAfter(LocalDate.now())) {
                    counter++;
                }
            }
        }
        c.close();
        return counter;

    }
    public int getBookPrice(String k) throws SQLException{
        int i = 0;
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs=s.executeQuery("select * from Books");
        while(rs.next()){

            if(rs.getString(2).equals(k)){
                i= rs.getInt(7);
                break;

            }
        }
        c.close();
        return i;
    }
    public int getLateFees(User k) throws SQLException{
        int i = DeadLineCounter(k);
        int j = i*20;
        return j;

    }
    public int getFees(User k) throws  SQLException{
        int fees=0;

        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select * from Users");
        while (rs.next()) {

            if (rs.getString(2).equals(k.getPersonId()) ) {
                String e = rs.getString(3);
                int j = getBookPrice(e);
                fees+=j;
            }

        }
        fees= fees+getLateFees(k);
        c.close();
        return fees;

    }



}
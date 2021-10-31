package Classes;
import java.sql.*;
import java.time.LocalDate;


public class User extends Person {

    private String person_id;
    private String Card_Number;
     //books borrowed

    public User(String k,String s) {
        super();
        person_id=k;
        Card_Number=s;

    }
    public User(String k ){
        person_id=k;

    }



    public String getUserName() {
        return super.name;
    }

    public String getPersonId() {
        return person_id;
    }

    public String getCard_Number() {
        return Card_Number;
    }
    public int UserCount() throws SQLException {
        int Counter = 0;
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs=s.executeQuery("select * from Users");
        while(rs.next()){

            if(rs.getString(2).equals(person_id)){
               Counter++;

            }
        }
        c.close();
        return Counter;

    }
}




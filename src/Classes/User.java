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
    public void borrowbook(book b,Library s) throws SQLException {

        if(b.isavailable()==true && b.Bookcount()>0 && UserCount()<5){

            connect.testjdbc.connect("UPDATE `main`.`Users` SET `book_id` = '" + b.getId()+ "' WHERE (`person_id` = '" + getPersonId() + "' AND `book_id` = NULL);");
            int r = b.Bookcount()-1;
            int i = b.BorrowedBooks() +1;
            connect.testjdbc.connect("UPDATE `main`.`Books` SET `Count` = '" +r+ "' WHERE (`id` = '" + b.getId() + "' );");
            connect.testjdbc.connect("UPDATE `main`.`Books` SET `BorrowCount` = '" +i+ "' WHERE (`id` = '" + b.getId() + "' );");
            LocalDate q = LocalDate.now();
            LocalDate w = q.plusDays(5);
            String h = q.toString();
            String j = w.toString();
            connect.testjdbc.connect("INSERT INTO `main`.`Date`(`person_id`,`book_id`,`StartDate`,`EndDate`) VALUES ('"+getPersonId()+",'"+b.getId()+",'"+h+",'"+j+"');");

        }


    }
    public void returnbook(book b,Library s)throws SQLException{
        connect.testjdbc.connect("DELETE FROM `main`.`Users` WHERE (`book_id`='"+b.getId()+"' AND `person_id` ='"+getPersonId()+"');");
        connect.testjdbc.connect("DELETE FROM `main`.`Date` WHERE (`book_id`='"+b.getId()+"' AND `person_id` ='"+getPersonId()+"');");
        int r = b.Bookcount()+1;
        connect.testjdbc.connect("UPDATE `main`.`Books` SET `Count` = '" +r+ "' WHERE (`id` = '" + b.getId() + "' );");

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




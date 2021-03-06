package Classes;

import java.sql.*;
import java.time.LocalDate;


public class Librarian extends Person{
    String person_id;
    public Librarian( String k){
        super();
        person_id= k;

    }



    public void addbook(book b1,Library s) throws SQLException {
        if (b1.isavailable() == true) {

            connect.testjdbc.connect("INSERT INTO `main`.`Books`(`Title`,`Id`,`Author`,`Genre`,`Count`,`BorrowCount`,`Price`) VALUES ('" + b1.getTitle() + ",'" + b1.getId() + ",'" + b1.getAuthor() + ",'" + b1.getGenre() + ",'" + b1.getCount() + "',0,'" + b1.getPrice() + "');");
            //connect.testjdbc.connect("INSERT INTO `main`.`library`(`id`,`name``book_id`) VALUES ('"+s.getId()+",'"+s.getName()+",'"+b1.getId()+");");
        }

//        else
        // connect.testjdbc.connect("INSERT INTO `main`.`library`(`id`,`name``book_id`) VALUES ('"+s.getId()+",'"+s.getName()+",'"+b1.getId()+");");
    }
    public void removebook(book b1, Library z) throws SQLException {
        if (CanBeDeleted(b1)) {
            connect.testjdbc.connect("DELETE FROM `main`.`Books` WHERE (`Id`='" + b1.getId() + "');");
            String url = "jdbc:sqlite:src/DB/LibraryDB.db";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from Books");

                while (rs.next()) {

                    if (rs.getString(3).equals(b1.getId())) {
                        connect.testjdbc.connect("DELETE FROM `main`.`library` WHERE (`book_id`='" + b1.getId() + "');");

                    }
                }
            c.close();

        }
    }


    public  static void adduser(User k){
        connect.testjdbc.connect("INSERT INTO `main`.`person`(`email`,`name`,`password`,`admin`,'id') VALUES ('"+k.Email+"','"+k.name+"','"+k.Password+"','False','"+k.Email+"');");
       // connect.testjdbc.connect("INSERT INTO `main`.`Users`(`Credit card number`,`person_id`,`book_id`) VALUES ('"+k.getCard_Number()+"','"+k.getPersonId()+"',NULL);");



    }
    public void removeuser(User k){
        connect.testjdbc.connect("DELETE FROM `main`.`Users` WHERE (`person_id`='"+k.getPersonId()+"');");
        connect.testjdbc.connect("DELETE FROM `main`.`person` WHERE (`id`='"+k.getPersonId()+"');");

    }
    public void addAdmin(Library L){
        connect.testjdbc.connect("INSERT INTO `main`.`person`(`Mail`,`Name`,`Password`,`Admin`) VALUES ('"+this.name+"','"+this.Email+"','"+this.Password+"','True');");
        //connect.testjdbc.connect("INSERT INTO `main`.`Librarians`(`person_id`,`library_id`) VALUES ('"+person_id+"','"+L.getId()+"');");

    }
    public boolean IsDeadline(User k, book b ) throws SQLException {
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select * from Date");
        while (rs.next()) {

            if (rs.getString(1).equals(k.getPersonId()) && rs.getString(2).equals(b.getId())) {
                String e = rs.getString(4);
                LocalDate DeadLine = LocalDate.parse(e);
                if (DeadLine.isAfter(LocalDate.now())) {
                    return true;
                }
            }
        }
        c.close();
        return false;
    }
    public boolean CanBeDeleted(book b) throws SQLException{

        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select * from Books");
        while (rs.next()) {
            if(rs.getInt(6)==0)
                return true;
        }
        c.close();
        return false;
    }


    public String TopBook()throws SQLException{
        int Total =0;
        String e= "None";
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select * from Book");
        while (rs.next()) {

            if (rs.getInt(6) > Total ) {
                Total = rs.getInt(6);
                e=rs.getString(1);


            }

        }

        c.close();
        return e;


    }


}

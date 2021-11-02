package Classes;

import java.sql.*;
import java.time.LocalDate;

public class Function {
    public void Function(){}

    public void borrowbook(User k,book b,Library s) throws SQLException {

        if(b.isavailable()==true && b.Bookcount()>0 && k.UserCount()<5){

            connect.testjdbc.connect("UPDATE `main`.`Users` SET `book_id` = '" + b.getId()+ "' WHERE (`person_id` = '" + k.getPersonId() + "' AND `book_id` = NULL);");
            int r = b.Bookcount()-1;
            int i = b.BorrowedBooks() +1;
            connect.testjdbc.connect("UPDATE `main`.`Books` SET `Count` = '" +r+ "' WHERE (`id` = '" + b.getId() + "' );");
            connect.testjdbc.connect("UPDATE `main`.`Books` SET `BorrowCount` = '" +i+ "' WHERE (`id` = '" + b.getId() + "' );");
            LocalDate q = LocalDate.now();
            LocalDate w = q.plusDays(5);
            String h = q.toString();
            String j = w.toString();
            connect.testjdbc.connect("INSERT INTO `main`.`Date`(`person_id`,`book_id`,`StartDate`,`EndDate`) VALUES ('"+k.getPersonId()+",'"+b.getId()+",'"+h+",'"+j+"');");

        }


    }
    public void returnbook(User k,book b,Library s)throws SQLException{
        connect.testjdbc.connect("DELETE FROM `main`.`Users` WHERE (`book_id`='"+b.getId()+"' AND `person_id` ='"+k.getPersonId()+"');");
        connect.testjdbc.connect("DELETE FROM `main`.`Date` WHERE (`book_id`='"+b.getId()+"' AND `person_id` ='"+k.getPersonId()+"');");
        int r = b.Bookcount()+1;
        connect.testjdbc.connect("UPDATE `main`.`Books` SET `Count` = '" +r+ "' WHERE (`id` = '" + b.getId() + "' );");

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


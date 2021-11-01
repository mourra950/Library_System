package Classes;

import java.sql.*;


public class Librarian extends Person{
    String person_id;
    public Librarian( String k){
        super();
        person_id= k;

    }



    public void addbook(book b1,Library s) throws SQLException {
        if(b1.isavailable()==true){

            connect.testjdbc.connect("INSERT INTO `main`.`Books`(`Title`,`Id`,`Author`,`Genre`,`Count`,`BorrowCount`) VALUES ('"+b1.getTitle()+",'"+b1.getId()+",'"+b1.getAuthor()+",'"+b1.getGenre()+",'"+b1.getCount()+"',0);");
            connect.testjdbc.connect("INSERT INTO `main`.`library`(`id`,`name``book_id`) VALUES ('"+s.getId()+",'"+s.getName()+",'"+b1.getId()+");");
        }

        else
            connect.testjdbc.connect("INSERT INTO `main`.`library`(`id`,`name``book_id`) VALUES ('"+s.getId()+",'"+s.getName()+",'"+b1.getId()+");");
    }
    public void removebook(book b1, Library z) throws SQLException{
        connect.testjdbc.connect("DELETE FROM `main`.`Books` WHERE (`Id`='"+b1.getId()+"');");
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs=s.executeQuery("select * from Books");
        while(rs.next()){

            if(rs.getString(3).equals(b1.getId())){
                connect.testjdbc.connect("DELETE FROM `main`.`library` WHERE (`book_id`='"+b1.getId()+"');");

            }
        }
        c.close();

    }



    public void adduser(User k){
        connect.testjdbc.connect("INSERT INTO `main`.`person`(`Mail`,`Name`,`Password`,`Admin`) VALUES ('"+k.name+"','"+k.Email+"','"+k.Password+"','False');");
        connect.testjdbc.connect("INSERT INTO `main`.`Users`(`Credit card number`,`person_id`,`book_id`) VALUES ('"+k.getCard_Number()+"','"+k.getPersonId()+"',NULL);");



    }
    public void removeuser(User k){
        connect.testjdbc.connect("DELETE FROM `main`.`Users` WHERE (`person_id`='"+k.getPersonId()+"');");
        connect.testjdbc.connect("DELETE FROM `main`.`person` WHERE (`id`='"+k.getPersonId()+"');");

    }
    public void add(Library L){
        connect.testjdbc.connect("INSERT INTO `main`.`person`(`Mail`,`Name`,`Password`,`Admin`) VALUES ('"+this.name+"','"+this.Email+"','"+this.Password+"','True');");
        connect.testjdbc.connect("INSERT INTO `main`.`Librarians`(`person_id`,`library_id`) VALUES ('"+person_id+"','"+L.getId()+"');");

    }

}

package Classes;

import java.sql.SQLException;

public class Function {
    public void Function(){}

    public void borrowbook(User k,book b,Library s) throws SQLException {

        if(b.isavailable()==true && b.Bookcount()>0){

            connect.testjdbc.connect("UPDATE `main`.`Users` SET `book_id` = '" + b.getId()+ "' WHERE (`person_id` = '" + k.getPersonId() + "' AND `book_id` = NULL);");
            int r = b.Bookcount()-1;
            int i = b.BorrowedBooks() +1;
            connect.testjdbc.connect("UPDATE `main`.`Books` SET `Count` = '" +r+ "' WHERE (`id` = '" + b.getId() + "' );");
            connect.testjdbc.connect("UPDATE `main`.`Books` SET `BorrowCount` = '" +i+ "' WHERE (`id` = '" + b.getId() + "' );");

        }


    }
    public void returnbook(User k,book b,Library s)throws SQLException{
        connect.testjdbc.connect("DELETE FROM `main`.`Users` WHERE (`book_id`='"+b.getId()+"' AND `person_id` ='"+k.getPersonId()+"');");
        int r = b.Bookcount()+1;
        connect.testjdbc.connect("UPDATE `main`.`Books` SET `Count` = '" +r+ "' WHERE (`id` = '" + b.getId() + "' );");

    }
}

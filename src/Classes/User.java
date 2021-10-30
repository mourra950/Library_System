package Classes;
import java.time.LocalDate;


public class User extends Person{


    private int BooksBorrowed;


    public User(){
       super();


        connect.testjdbc.connect("INSERT INTO `main`.`Users`(`Name`,`Address`,`Password`) VALUES ('"+super.name+"','"+super.address+"','"+super.number+"');");

    }

    /* public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
        String s = StartDate.toString();
        connect.testjdbc.connect("UPDATE `main`.`User` SET `StartDate` = '" + s + "' WHERE (`Name` = '" + Name + "');");
    }
    public void setEndDate(LocalDate endDate){

        EndDate=endDate;
        String s1 = StartDate.toString();
        connect.testjdbc.connect("UPDATE `main`.`User` SET `StartDate` = '" + s1 + "' WHERE (`Name` = '" + Name + "');");

    }
    public void setBooksBorrowed(int k){

        BooksBorrowed = k;
        connect.testjdbc.connect("UPDATE `main`.`User` SET `Borrowed Books` = '" + BooksBorrowed + "' WHERE (`Name` = '" + Name + "');");

    } */
    public boolean BorrowedValid(){

        return BooksBorrowed <= 5;
    }


}
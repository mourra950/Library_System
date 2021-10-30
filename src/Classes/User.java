package Classes;
import java.time.LocalDate;


public class User extends Person{


    private int BooksBorrowed;


    public User(){
       super();


        connect.testjdbc.connect("INSERT INTO `main`.`Users`(`Mail`,`Name`,`Password`) VALUES ('"+super.name+"','"+super.address+"','"+super.number+"');");

    }
    public String getUserName(){return super.name;}



    public boolean BorrowedValid(){

        return BooksBorrowed <= 5;
    }


}
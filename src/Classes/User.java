package Classes;
import java.time.LocalDate;


public class User extends Person{

    private String person_id;

    private int BooksBorrowed;


    public User(String k){
       super();
    person_id = k;

        connect.testjdbc.connect("INSERT INTO `main`.`Users`(`Mail`,`Name`,`Password`) VALUES ('"+super.name+"','"+super.Email+"','"+super.Password+"');");
        connect.testjdbc.connect("INSERT INTO `main`.`Users`(`person_id`) VALUES ('"+person_id+"');");

    }
    public String getUserName(){return super.name;}
    public String getPersonId(){return person_id;}



    public boolean BorrowedValid(){

        return BooksBorrowed <= 5;
    }


}
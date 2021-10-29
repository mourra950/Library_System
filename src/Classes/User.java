package Classes;
import java.time.LocalDate;


public class User{

    private String Name;
    private String address;
    private String number;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private LocalDate BorrowDays;
    private int BooksBorrowed;
    private int daysLate;


    public User(String Name, String address, String number){

        this.Name=Name;
        this.address=address;
        this.number=number;
        connect.testjdbc.connect("INSERT INTO `main`.`Users`(`Mail`,`Name`,`Password`) VALUES ('"+Name+"','"+address+"','"+number+"');");

    }

    public void setStartDate(LocalDate startDate) {
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

    }
    public boolean BorrowedValid(){

        return BooksBorrowed <= 5;
    }

    public boolean IsDeadLine() {
        if (LocalDate.now().isAfter(EndDate))
            return true;
        return false;
    }
    /*public double getdaysLate() {
        if (IsDeadLine() == true) {

        }
    }
*/
}
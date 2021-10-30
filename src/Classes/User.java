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


    public void borrow(book b,int duration){
        if (b.getCan_be_checked_out() && b.getIs_available()){
            b.setIs_available(false);
            b.setStartDate(LocalDate.now());
            b.setEndDate(b.getStartDate().plusDays(duration));
            System.out.println("you should return before "+b.getEndDate()+" days");

        }
        else{
            System.out.println("Book cannot be borrowed");
        }
    }
    }



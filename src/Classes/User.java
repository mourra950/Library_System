package Classes;
import java.time.LocalDate;


public class User extends Person{

    private String person_id;
    private String Card_Number;


    private int BooksBorrowed;


    public User(String k,String s){
       super();
    person_id = k;
    Card_Number=s;

        connect.testjdbc.connect("INSERT INTO `main`.`person`(`Mail`,`Name`,`Password`) VALUES ('"+super.name+"','"+super.Email+"','"+super.Password+"');");
        connect.testjdbc.connect("INSERT INTO `main`.`Users`(`person_id`) VALUES ('"+person_id+"');");


    }

    public String getUserName(){return super.name;}
    public String getPersonId(){return person_id;}
    public String getCard_Number(){return Card_Number;}



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



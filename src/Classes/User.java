package Classes;
import java.time.LocalDate;


public class User extends Person{

    private String person_id;
    private int BooksBorrowed;


    public User(String k){
        super();
        person_id = k;
        int BooksBorrowed=0;

        connect.testjdbc.connect("INSERT INTO `main`.`person`(`Mail`,`Name`,`Password`) VALUES ('"+super.name+"','"+super.Email+"','"+super.Password+"');");
        connect.testjdbc.connect("INSERT INTO `main`.`Users`(`person_id`) VALUES ('"+person_id+"');");

    }
    public String getUserName(){return super.name;}
    public String getPersonId(){return person_id;}

    public void setBooksBorrowed(int booksBorrowed) {
        BooksBorrowed = booksBorrowed;
    }
    public int getBooksBorrowed(){
        return BooksBorrowed;
    }

    public boolean BorrowedValid(){
        return BooksBorrowed <= 5;

    }


    public void borrow(book b,int duration){
        if (this.getBooksBorrowed()<5){
            if (b.getCan_be_checked_out() && b.getIs_available()){
                b.setIs_available(false);
                b.setCount(b.getCount()+1);
                this.setBooksBorrowed(this.getBooksBorrowed()+1);
                b.setStartDate(LocalDate.now());
                b.setEndDate(b.getStartDate().plusDays(duration));
                System.out.println("you should return before "+b.getEndDate()+" days");
                connect.testjdbc.connect("");

            }
             else{
                    System.out.println("Book cannot be borrowed");
                }
         }
        else{
            System.out.println("limit reached!");
            }
        }
    }




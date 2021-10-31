package Classes;
import java.time.LocalDate;


public class User extends Person{

    private String person_id;
    private String Card_Number;
    private int counter; //books borrowed
    public User(String k,String s){
        super();
    person_id = k;
    Card_Number=s;
    counter =0;
    }

    public boolean BorrowedValid(){
        if(counter<=5){
            return true;
        }else return false;
    }
    public String getUserName(){return super.name;}
    public String getPersonId(){return person_id;}
    public String getCard_Number(){return Card_Number;}
    public void borrow(Library lib,book b,int duration){
        if (b.getCan_be_checked_out() && b.getIs_available() && BorrowedValid()){
            b.setIs_available(false);
            b.setStartDate(LocalDate.now());
            b.setEndDate(b.getStartDate().plusDays(duration));
            System.out.println("you should return before "+b.getEndDate()+" days");
            counter++;
            int k = b.getCount()-1;
            connect.testjdbc.connect("UPDATE 'main'.'Users' SET('Counter' = '"+counter+"') WHERE('person_id' ='"+person_id+"')");
            connect.testjdbc.connect("UPDATE 'main'.'Books' SET('Count' = '"+k+"') WHERE('Id' ='"+b.getId()+"')");
        }
        else{
            System.out.println("Book cannot be borrowed");
        }
    }
    public void ReturnBook(book b){
        counter--;
        connect.testjdbc.connect("UPDATE 'main'.'Users' SET('Counter' = '"+counter+"') WHERE('person_id' ='"+person_id+"')");
        connect.testjdbc.connect("UPDATE 'main'.'Books' SET('Count' = '"+b.getCount()+"') WHERE('Id' ='"+b.getId()+"')");
    }
    }



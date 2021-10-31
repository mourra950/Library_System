package Classes;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
public class Payment {
    double price;
    int quantity;
    double discount;
    private double latesfees;
    public Payment(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }
    public double discountvalue(int quantity){
        if (quantity>1 && quantity<=3 )
        {
            return (price*quantity)*0.25;
        }
        if (quantity>3 && quantity<=5 )
        {
            return (price*quantity)*0.5;
        }
        return 0;
    }
    public double getfprice(){
        return ((price*quantity)-discountvalue(quantity));
    }
    public void display(){
        System.out.println("your number of borrowed books "+ quantity);
        System.out.println("your discount is "+discountvalue(quantity));
        //System.out.println("the total price is "+(getfprice()+//getLatefees()));
    }
    public double getLatefees(book b){
        long daysBetween=ChronoUnit.DAYS.between(b.getEndDate(), LocalDate.now());
        if (daysBetween<0){
            return 0;
}
        if (daysBetween>=1&&daysBetween<7){
            return getfprice()*0.1;
        }
        if (daysBetween>=7&&daysBetween<14){
            return getfprice()*0.2;
        }
else{
            return getfprice()*0.5;
        }
    }
}
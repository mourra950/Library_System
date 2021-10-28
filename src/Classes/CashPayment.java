package Classes;
public class CashPayment extends Payment
{
    public CashPayment(double price, int quantity) {
        super(price, quantity);
    }
       public void display(){
           System.out.println("your number of borrowed books "+ quantity);
           System.out.println("your discount is "+discount);
           System.out.println("the total price in cash paid is "+((quantity*price)-discount));
       }
   }
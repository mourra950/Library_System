package Classes;

public class Payment {
    double price;
    int quantity;
    double discount;
    double latesfees;
    public Payment(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }
    public double discountvalue(int quantity){
        if (quantity>1 && quantity<=3 )
        {
            return(price*quantity)*0.25;
        }
        if (quantity>3 && quantity<=5 )
        {
            return (price*quantity)*0.5;
        }
        return 0;
    }
    public void setLatefees(book a) {
        if (a.IsDeadLine()) {
            latesfees=getfprice()*0.1;
        }
    }
    public double getfprice(){
        return ((price*quantity)-discount);
    }
    public void display(){
        System.out.println("your number of borrowed books "+ quantity);
        System.out.println("your discount is "+discountvalue(quantity));
        System.out.println("the total price is "+(getfprice()+latesfees));
    }

}
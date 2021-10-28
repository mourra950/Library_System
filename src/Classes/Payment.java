package Classes;

public class Payment {
    double price;
    int quantity;
    double discount;
    public Payment(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }
    public void discountvalue(int quantity){
        if (quantity>1 && quantity<=3 )
        {
            discount=(price*quantity)*0.25;
        }
        if (quantity>3 && quantity<=5 )
        {
            discount=(price*quantity)*0.5;
        }

    }
    public void display(){
        System.out.println("your number of borrowed books "+ quantity);
        System.out.println("your discount is "+discount);
        System.out.println("the total price is "+((quantity*price)-discount));
    }

}
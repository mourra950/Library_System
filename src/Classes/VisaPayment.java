package Classes;
public class VisaPayment extends Payment
{
    private String name, expDate, number;

    public VisaPayment(double price, int quantity, String name, String expDate, String number) {
        super(price, quantity);
        this.name = name;
        this.expDate = expDate;
        this.number = number;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void display() {
        System.out.println("your number of borrowed books "+ quantity);
        System.out.println("your discount is "+discountvalue(quantity));
      System.out.println("The payment of $" + ((getfprice()+latesfees) + " using the card " + getNumber()
                + ",  and expires at "	+ getExpDate() + ", and the owner name: " + getName() + "."));
    }

}
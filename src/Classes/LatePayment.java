package Classes;
public class LatePayment extends Payment{
    private int daysLate;
    private double latefees;

    public LatePayment(double price, int quantity) {
        super(price, quantity);
    }

//    public LatePayment(int daysLate, double latefees) {
//        super();
//        this.daysLate = daysLate;
//        this.latefees = latefees;
//    }

    public int getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(int daysLate) {
        this.daysLate = daysLate;
    }

    public double getLatefees() {
        return latefees;
    }

    public void setLatefees(double latefees) {
        this.latefees = latefees;
    }

}
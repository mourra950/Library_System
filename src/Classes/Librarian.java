package Classes;

public class Librarian extends Person{
    public Librarian(){
        super();
        connect.testjdbc.connect("INSERT INTO `main`.`person`(`Name`,`email`,`Password`) VALUES ('"+super.name+"','"+super.Email+"','"+super.Password+"');");

    }
    public void setStartDate(book b1) {

        String s = b1.getStartDate().toString();
        connect.testjdbc.connect("UPDATE `main`.`Books` SET `StartDate` = '" + s + "' WHERE (`Tittle` = '" + b1.getTitle() + "');");
    }
    public void setEndDate(book b2){

        String s1 = b2.getEndDate().toString();
        connect.testjdbc.connect("UPDATE `main`.`Books` SET `EndDate` = '" + s1 + "' WHERE (`Tittle` = '" + b2.getTitle() + "');");

    }
    public void setBooksBorrowed(User K,int i){
        String s = K.getPersonId();

        connect.testjdbc.connect("UPDATE `main`.`Users` SET `counter` = '" + i + "' WHERE (`person_id` = '" + s + "');");

    }
}

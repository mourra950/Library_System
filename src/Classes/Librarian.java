package Classes;

public class Librarian extends Person{
    public Librarian(){
        super();
        connect.testjdbc.connect("INSERT INTO `main`.`Librarians`(`Name`,`address`,`number`) VALUES ('"+super.name+"','"+super.address+"','"+super.number+"');");

    }
    public void setStartDate(book b1) {

        String s = b1.getStartDate().toString();
        connect.testjdbc.connect("UPDATE `main`.`User` SET `StartDate` = '" + s + "' WHERE (`Tittle` = '" + b1.getTitle() + "');");
    }
    public void setEndDate(book b2){

        String s1 = b2.getEndDate().toString();
        connect.testjdbc.connect("UPDATE `main`.`book` SET `StartDate` = '" + s1 + "' WHERE (`Tittle` = '" + b2.getTitle() + "');");

    }
    public void setBooksBorrowed(User K,int i){
        String s = K.getUserName();

        connect.testjdbc.connect("UPDATE `main`.`people` SET `Borrowed Books` = '" + i + "' WHERE (`Name` = '" + s + "');");

    }
}

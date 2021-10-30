package Classes;

public class Librarian extends Person{
    public Librarian(){
        super();
        connect.testjdbc.connect("INSERT INTO `main`.`Librarians`(`Name`,`address`,`number`) VALUES ('"+super.name+"','"+super.address+"','"+super.number+"');");

    }

    public void setBooksBorrowed(User K,int i){
        String s = K.getUserName();

        connect.testjdbc.connect("UPDATE `main`.`people` SET `Borrowed Books` = '" + i + "' WHERE (`Name` = '" + s + "');");

    }
}

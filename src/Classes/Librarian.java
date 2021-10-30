package Classes;

public class Librarian extends Person{
    public Librarian(){
        super();
        connect.testjdbc.connect("INSERT INTO `main`.`person`(`name`,`id`,`email`,'Password') VALUES ('"+super.name+"','"+super.id+"','"+super.Email+"','"+super.Password+"');");

    }

    public void setBooksBorrowed(User K,int i){
        String s = K.getUserName();

        connect.testjdbc.connect("UPDATE `main`.`people` SET `counter` = '" + i + "' WHERE (`person_id` = '" + K.getPersonId() + "');");

    }
}

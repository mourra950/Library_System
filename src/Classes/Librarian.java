package Classes;

public class Librarian extends Person{
    public Librarian(){
        super();

    }

    public void setBooksBorrowed(User K,int i){
        String s = K.getUserName();

        connect.testjdbc.connect("UPDATE `main`.`Users` SET `counter` = '" + i + "' WHERE (`person_id` = '" + K.getPersonId() + "');");

    }

    public void addbook(book b1){
        connect.testjdbc.connect("INSERT INTO `main`.`Books`(`Title`,`Id`,`Author`,`Genre`,`Count`) VALUES ('"+b1.getTitle()+",'"+b1.getId()+",'"+b1.getAuthor()+",'"+b1.getGenre()+",'"+b1.getCount()+"');");

    }
    public void removebook(book b1){
        connect.testjdbc.connect("DELETE FROM `main`.`Books` WHERE (`Id`='"+b1.getId()+"');");


    }
    public void adduser(User k){
        connect.testjdbc.connect("INSERT INTO `main`.`person`(`Mail`,`Name`,`Password`) VALUES ('"+k.name+"','"+k.Email+"','"+k.Password+"');");
        connect.testjdbc.connect("INSERT INTO `main`.`Users`(`Credit card number`,`person_id`,`book_id`,`counter`) VALUES (NULL,'"+k.getPersonId()+"',NULL,0);");



    }
    public void removeuser(User k){
        connect.testjdbc.connect("DELETE FROM `main`.`Users` WHERE (`person_id`='"+k.getPersonId()+"');");
        connect.testjdbc.connect("DELETE FROM `main`.`person` WHERE (`id`='"+k.getPersonId()+"');");

    }

}

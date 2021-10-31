package Classes;

public class Librarian extends Person{
    String librry_id;
    public Librarian(String k){
        super();
        librry_id = k;
    }

    public void setBooksBorrowed(User K,int i){
        String s = K.getUserName();

        connect.testjdbc.connect("UPDATE `main`.`Users` SET `counter` = '" + i + "' WHERE (`person_id` = '" + K.getPersonId() + "');");

    }

    public void addbook(book b1,Library s){
        connect.testjdbc.connect("INSERT INTO `main`.`Books`(`Title`,`Id`,`Author`,`Genre`,`Count`,`BorrowCount`) VALUES ('"+b1.getTitle()+",'"+b1.getId()+",'"+b1.getAuthor()+",'"+b1.getGenre()+",'"+b1.getCount()+"',0);");
        connect.testjdbc.connect("INSERT INTO `main`.`library`(`id`,`name``book_id`) VALUES ('"+s.getId()+",'"+s.getName()+",'"+b1.getId()+");");
    }
    public void removebook(book b1, Library s){
        connect.testjdbc.connect("DELETE FROM `main`.`Books` WHERE (`Id`='"+b1.getId()+"');");
        connect.testjdbc.connect("DELETE FROM `main`.`library` WHERE (`book_id`='"+b1.getId()+"' AND `id` ='"+b1.getId()+"');");


    }
    public void adduser(User k){
        connect.testjdbc.connect("INSERT INTO `main`.`person`(`Mail`,`Name`,`Password`) VALUES ('"+k.name+"','"+k.Email+"','"+k.Password+"');");
        connect.testjdbc.connect("INSERT INTO `main`.`Users`(`Credit card number`,`person_id`,`book_id`,`counter`) VALUES ('"+k.getCard_Number()+"','"+k.getPersonId()+"',NULL,0);");



    }
    public void removeuser(User k){
        connect.testjdbc.connect("DELETE FROM `main`.`Users` WHERE (`person_id`='"+k.getPersonId()+"');");
        connect.testjdbc.connect("DELETE FROM `main`.`person` WHERE (`id`='"+k.getPersonId()+"');");

    }

}

package Classes;

import java.time.LocalDate;

public class book {
    private String title;
    private int id;
    private String author;
    private String genre;
    private String topic;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private int daily_limit;
    private boolean can_be_checked_out;
    private boolean is_available;//available to bo borrowed


    public book(String title,int id,String author,String genre,String topic,int daily_limit,boolean can_be_checked_out,boolean is_available){
        this.title=title;
        this.id=id;
        this.author=author;
        this.genre=genre;
        this.topic=topic;
        this.daily_limit=daily_limit;
        this.can_be_checked_out=can_be_checked_out;
        this.is_available=is_available;
        connect.testjdbc.connect("INSERT INTO `main`.`Books`(`Title`,`Id`,`Author`,`Genre`,`Topic`) VALUES ('"+title+"','"+id+"','"+author+"','"+genre+"','"+topic+"');");

    }

    public book(String title,int id,String genre,String topic){
        setTitle(title);
        setId(id);
        setGenre(genre);
        setTopic(topic);
    }

    public void borrow(book b){
        if (b.can_be_checked_out && b.is_available){
            is_available=false;
            System.out.println("you should return before "+b.daily_limit+" days");
        }
        else{
            System.out.println("Book cannot be borrowed");
        }
    }
    public void return_book(book b){
        b.is_available=true;
    }
    public LocalDate getStartDate(){return StartDate;}
    public LocalDate getEndDate(){return EndDate;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDaily_limit() {
        return daily_limit;
    }

    public void setDaily_limit(int daily_limit) {
        this.daily_limit = daily_limit;
    }

    public boolean isCan_be_checked_out() {
        return can_be_checked_out;
    }

    public void setCan_be_checked_out(boolean can_be_checked_out) {
        this.can_be_checked_out = can_be_checked_out;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }
    public boolean IsDeadLine() {
        if (LocalDate.now().isAfter(EndDate))
            return true;
        return false;
    }
}

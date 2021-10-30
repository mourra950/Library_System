package Classes;

import java.time.LocalDate;

public class book {
    private String title;
    private int id;
    private String author;
    private String genre;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private int duration;
    private boolean can_be_checked_out;
    private boolean is_available;//available to bo borrowed

    public book(String title, int id, String author, String genre, boolean can_be_checked_out, boolean is_available){
        setTitle(title);
        setId(id);
        setAuthor(author);
        setGenre(genre);
        this.can_be_checked_out=can_be_checked_out;
        this.is_available=is_available;
        connect.testjdbc.connect("INSERT INTO `main`.`Books`(`Title`,`Id`,`Author`,`Genre`) VALUES ('"+title+"','"+id+"','"+author+"','"+genre+"');");

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
    public boolean getCan_be_checked_out(){
        return can_be_checked_out;
    }
    public boolean getIs_available(){
        return is_available;
    }
    public boolean IsDeadLine() {
        if (LocalDate.now().isAfter(EndDate))
            return true;
        return false;
    }

    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }

}

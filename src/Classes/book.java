package Classes;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class book {
    private String title;
    private String id;
    private String author;
    private String genre;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private int BorrowCount;
    private int count;
    private int price;


    public book(String title,String id, String author, String genre,int count,int price){
        this.title=title;
        this.id=id;
        this.author=author;
        this.genre = genre;
        this.count=count;
        this.price=price;
    }
    public book(String id){
        this.id= id;
    }

    public boolean isavailable() throws SQLException {

        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs=s.executeQuery("select * from Books");
        while(rs.next()){

            if(rs.getString(2).equals(id)){
                return true;
            }
        }
        c.close();
        return false;
    }
    public int Bookcount() throws SQLException{
        int k = 0;
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs=s.executeQuery("select * from Books");
        while(rs.next()){

            if(rs.getString(2).equals(id)){
                k =rs.getInt(5);

            }
        }
        c.close();
        return k;
    }
    public int BorrowedBooks() throws SQLException{
        int k = 0;
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet rs=s.executeQuery("select * from Books");
        while(rs.next()){

            if(rs.getString(2).equals(id)){
                k =rs.getInt(6);

            }
        }
        c.close();
        return k;
    }
    
    public int getPrice(){return price;}

    public LocalDate getStartDate(){return StartDate;}
    public LocalDate getEndDate(){return EndDate;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


    public boolean IsDeadLine() {
        return LocalDate.now().isAfter(EndDate);
    }

    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}

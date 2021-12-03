package Classes;

import java.sql.*;
import java.time.LocalDate;

public class book {
    private String title;
    private String lib;
    private String id;
    private String author;
    private String genre;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private int BorrowCount=0;
    private String count;
    private String price;


    public book(String title,String id, String author, String genre,String lib,String count,String price){
        this.title=title;
        this.id=id;
        this.author=author;
        this.genre = genre;
        this.count=count;
        this.price=price;
        this.lib=lib;

    }
    public book(String id){
        this.id= id;
    }

    public book(String title, String id, String author, String genre,String lib) {
        this.title=title;
        this.id=id;
        this.author=author;
        this.genre = genre;
        this.lib =lib;
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
                k =Integer.parseInt( rs.getString(5));

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
                k =Integer.parseInt( rs.getString(6));
            }
        }
        c.close();
        return k;
    }
    public static void most_popular()throws SQLException{
        String url = "jdbc:sqlite:src/DB/LibraryDB.db";
        Connection c = DriverManager.getConnection(url);
        PreparedStatement ps=null;
        ResultSet re=null;

    }



    public LocalDate getStartDate(){return StartDate;}
    public LocalDate getEndDate(){return EndDate;}

    public String getId() {
        return id;
    }

    public String getPrice(){return price;}

    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }

    public String getCount() {
        return count;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setCount(String count) {
        this.count = count;
    }


    public String getLib() {
        return lib;
    }
}

package Classes;

import connect.testjdbc;

import java.sql.*;



public class LibraryCollection {
    public static void AddBook(String title, int id, String author, String genre, String topic){
        Connection con = testjdbc.connect();
        PreparedStatement ps = null;
        try{
            String sql ="INSERT INTO Books(Title,Id,Author,Genre,Topic) Values(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setInt(2, id);
            ps.setString(3, author);
            ps.setString(4,genre);
            ps.setString(5, topic);
            ps.execute();
            System.out.println("Data has been inserted");
        }catch (SQLException e){
            System.out.println(e.toString());
        }
    }
    public static void RemoveBook(int id){
        Connection con = testjdbc.connect();
        PreparedStatement ps = null;
        try{
            String sql = "DELETE FROM Books WHERE Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            System.out.println("Book has been deleted!");
        }catch(Exception e){
            //TODO: handle eception
            System.out.println(e.toString());
        }
    }
    public static void UdateBookTitle(String title,int id){
        Connection con = testjdbc.connect();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE Books SET Title = ? WHERE Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setInt(2, id);
            ps.execute();
            System.out.println("title has been updated");
        }catch(SQLException e){
            //TODO: handle eception
            System.out.println(e.toString());
        }
    }
    public static void UdateBookgGenre(String genre,int id){
        Connection con = testjdbc.connect();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE Books SET Genre = ? WHERE Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, genre);
            ps.setInt(2, id);
            ps.execute();
            System.out.println("genre has been updated");
        }catch(SQLException e){
            //TODO: handle eception
            System.out.println(e.toString());
        }
    }
    public static void UdateBookTopic(String topic,int id){
        Connection con = testjdbc.connect();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE Books SET Topic = ? WHERE Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, topic);
            ps.setInt(2, id);
            ps.execute();
            System.out.println("topic has been updated");
        }catch(SQLException e){
            //TODO: handle eception
            System.out.println(e.toString());
        }
    }
    public static void UdateBookAuthor(String author,int id){
        Connection con = testjdbc.connect();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE Books SET Author = ? WHERE Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, author);
            ps.setInt(2, id);
            ps.execute();
            System.out.println("auhtor has been updated!");
        }catch(SQLException e){
            //TODO: handle eception
            System.out.println(e.toString());
        }
    }
}
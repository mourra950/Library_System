package Classes;

import connect.testjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Person {
    protected String name;
    protected String Email;
    protected String Password;


    public Person(String name, String Email, String Password) {
        this.name = name;
        this.Email = Email;
        this.Password = Password;

    }

    public Person() {

    }

    public void searchId(String id) {
        Connection con = testjdbc.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT Title FROM `main`.`Books` where Id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            String T = rs.getString(1);
            System.out.println(T);
            //  connect.testjdbc.connect("SELECT FROM `main`.`Books` WHERE (`Id`='"+id+"');");
        } catch (SQLException e) {
            System.out.println(e.toString());

        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    public void searchTitle(String title) {
        Connection con = testjdbc.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT Title FROM `main`.`Books` where Title=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, title);
            rs = ps.executeQuery();
            String T = rs.getString(1);
            System.out.println(T);
            //  connect.testjdbc.connect("SELECT FROM `main`.`Books` WHERE (`Id`='"+id+"');");
        } catch (SQLException e) {
            System.out.println(e.toString());

        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    public void searchAuthor(String Author) {
        Connection con = testjdbc.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT Title FROM `main`.`Books` where Title=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, Author);
            rs = ps.executeQuery();
            String T = rs.getString(1);
            System.out.println(T);
            //  connect.testjdbc.connect("SELECT FROM `main`.`Books` WHERE (`Id`='"+id+"');");
        } catch (SQLException e) {
            System.out.println(e.toString());

        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}




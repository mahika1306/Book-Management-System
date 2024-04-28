//package com.bookshop.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/editScreen")
//public class EditScreenServelet extends HttpServlet {
//	private static final String query = "SELECT BOOKNAME,AUTHOR,COPIES,BOOKEDITION,BOOKPRICE FROM DATA where id=?";
//
//    
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        PrintWriter pw = res.getWriter();
//        res.setContentType("text/html");
//        int id = Integer.parseInt(req.getParameter("id"));
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException cnf) {
//            cnf.printStackTrace();
//        }
//
//        try (Connection con = DriverManager.getConnection("jdbc:mysql:///newbook", "root", "tiger");
//             PreparedStatement ps = con.prepareStatement(query);) {
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//            pw.println("<form action='editurl?id=" + id + "' method='post'>");
//            pw.println("<table align='center'>");
//            pw.println("<tr>");
//            pw.println("<td>Book Name</td>");
//            pw.println("<td><input type='text' name='bookName' value='" + rs.getString(1) + "'></td>");
//            pw.println("</tr>");
//            pw.println("<tr>");
//            pw.println("<td>Author</td>");
//            pw.println("<td><input type='text' name='author' value='" + rs.getString(2) + "'></td>");
//            pw.println("</tr>");
//            pw.println("<tr>");
//            pw.println("<td>Copies</td>");
//            pw.println("<td><input type='text' name='copies' value='" + rs.getInt(3) + "'></td>");
//            pw.println("</tr>");
//            pw.println("<tr>");
//            pw.println("<td>Book Edition</td>");
//            pw.println("<td><input type='text' name='bookEdition' value='" + rs.getString(4) + "'></td>");
//            pw.println("</tr>");
//            pw.println("<tr>");
//            pw.println("<td>Book Price</td>");
//            pw.println("<td><input type='text' name='bookPrice' value='" + rs.getFloat(5) + "'></td>");
//            pw.println("</tr>");
//            pw.println("<tr>");
//            pw.println("<td><input type='submit' value='Edit'></td>");
//            pw.println("<td><input type='reset' value='Cancel'></td>");
//            pw.println("</tr>");
//            pw.println("</table>");
//            pw.println("</form>");
//        } catch (SQLException se) {
//            se.printStackTrace();
//            pw.println("<h1>" + se.getMessage() + "</h2>");
//        } catch (Exception e) {
//            e.printStackTrace();
//            pw.println("<h1>" + e.getMessage() + "</h2>");
//        }
//        pw.println("<a href='home.html'>Home</a>");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        doGet(req, res);
//    }
//}
package com.bookshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/editScreen")
public class EditScreenServelet extends HttpServlet {
    private static final String query = "SELECT BOOKNAME,AUTHOR,COPIES,BOOKEDITION,BOOKPRICE FROM DATA where id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql:///newbook", "root", "tiger");
             PreparedStatement ps = con.prepareStatement(query);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            pw.println("<form action='editurl?id=" + id + "' method='post'>");
            pw.println("<table align='center' style='border-collapse: collapse; width: 50%;'>");
            pw.println("<tr>");
            pw.println("<td style='padding: 10px;'>Book Name</td>");
            pw.println("<td><input type='text' name='bookName' value='" + rs.getString(1) + "' style='padding: 5px;'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td style='padding: 10px;'>Author</td>");
            pw.println("<td><input type='text' name='author' value='" + rs.getString(2) + "' style='padding: 5px;'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td style='padding: 10px;'>Copies</td>");
            pw.println("<td><input type='text' name='copies' value='" + rs.getInt(3) + "' style='padding: 5px;'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td style='padding: 10px;'>Book Edition</td>");
            pw.println("<td><input type='text' name='bookEdition' value='" + rs.getString(4) + "' style='padding: 5px;'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td style='padding: 10px;'>Book Price</td>");
            pw.println("<td><input type='text' name='bookPrice' value='" + rs.getFloat(5) + "' style='padding: 5px;'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td><input type='submit' value='Edit' style='padding: 5px; background-color: #4CAF50; color: white; border: none; border-radius: 3px;'></td>");
            pw.println("<td><input type='reset' value='Cancel' style='padding: 5px; background-color: #f44336; color: white; border: none; border-radius: 3px;'></td>");
            pw.println("</tr>");
            pw.println("</table>");
            pw.println("</form>");
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h2>");
        }
        pw.println("<div style='text-align: center; margin-top: 20px;'><a href='home.html' style='text-decoration: none; background-color: #007bff; color: white; padding: 10px 20px; border-radius: 5px;'>Home</a></div>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}


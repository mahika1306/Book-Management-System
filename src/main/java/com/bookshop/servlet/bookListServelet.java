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
//@WebServlet("/bookList")
//public class bookListServelet extends HttpServlet {
//    private static final String query = "SELECT id, bookName, author, copies, bookEdition, bookPrice FROM data";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        // Get PrintWriter
//        PrintWriter pw = res.getWriter();
//        // Set content type
//        res.setContentType("text/html");
//
//        // LOAD JDBC driver
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException cnf) {
//            cnf.printStackTrace();
//        }
//
//        // Generate the connection
//        try (Connection con = DriverManager.getConnection("jdbc:mysql:///newbook", "root", "tiger");
//                PreparedStatement ps = con.prepareStatement(query);) {
//            ResultSet rs = ps.executeQuery();
//            pw.println("<!DOCTYPE html>");
//            pw.println("<html>");
//            pw.println("<head>");
//            pw.println("<style>");
//            // Enhanced CSS styles
//            pw.println("body { font-family: Arial, sans-serif; background-image: url('https://images4.alphacoders.com/132/1326368.png'); background-size: cover;}");
//            pw.println("table { border-collapse: collapse; width: 80%; margin: 50px auto; border: 1px solid #ddd; border-radius: 8px; background-color: white; }");
//            pw.println("th, td { border: 1px solid #dddddd; text-align: left; padding: 12px; }");
//            pw.println("th { background-color: #f2f2f2; }");
//            pw.println("tr:nth-child(even) { background-color: #f8f8f8; }");
//            pw.println("tr:hover { background-color: #f0f0f0; }");
//            pw.println("a.button { text-decoration: none; background-color: #4CAF50; color: white; padding: 10px 20px; display: inline-block; border-radius: 5px; }");
//            pw.println("a.button:hover { background-color: #45a049; }");
//            pw.println(".navbar { overflow: hidden; background-color: #333; }");
//            pw.println(".navbar a { float: left; display: block; color: #f2f2f2; text-align: center; padding: 14px 20px; text-decoration: none; }");
//            pw.println(".navbar a:hover { background-color: #ddd; color: black; }");
//            pw.println("</style>");
//            pw.println("</head>");
//            pw.println("<body>");
//            // Navbar
//            pw.println("<div class='navbar'>");
//            pw.println("<a href='home.html'>Home</a>");
//            pw.println("</div>");
//
//            pw.println("<table>");
//            pw.println("<tr>");
//            pw.println("<th>Book Id</th>");
//            pw.println("<th>Book Name</th>");
//            pw.println("<th>Author</th>");
//            pw.println("<th>Copies</th>");
//            pw.println("<th>Book Edition</th>");
//            pw.println("<th>Book Price</th>");
//            pw.println("<th>Edit</th>");
//            pw.println("<th>Delete</th>");
//            pw.println("</tr>");
//            while (rs.next()) {
//                pw.println("<tr>");
//                pw.println("<td>" + rs.getInt(1) + "</td>");
//                pw.println("<td>" + rs.getString(2) + "</td>");
//                pw.println("<td>" + rs.getString(3) + "</td>");
//                pw.println("<td>" + rs.getInt(4) + "</td>");
//                pw.println("<td>" + rs.getString(5) + "</td>");
//                pw.println("<td>" + rs.getFloat(6) + "</td>");
//                pw.println("<td><a class='button' href='editScreen?id=" + rs.getInt(1) + "'>Edit</a></td>");
//                pw.println("<td><a class='button' href='deleteurl?id=" + rs.getInt(1) + "'>Delete</a></td>");
//                pw.println("</tr>");
//            }
//            pw.println("</table>");
//            pw.println("</body>");
//            pw.println("</html>");
//        } catch (SQLException se) {
//            se.printStackTrace();
//            pw.println("<h1>" + se.getMessage() + "</h1>");
//        } catch (Exception e) {
//            e.printStackTrace();
//            pw.println("<h1>" + e.getMessage() + "</h1>");
//        }
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

@WebServlet("/bookList")
public class bookListServelet extends HttpServlet {
    private static final String query = "SELECT id, bookName, author, copies, bookEdition, bookPrice FROM data";
    private static final String searchQuery = "SELECT id, bookName, author, copies, bookEdition, bookPrice FROM data WHERE bookName LIKE ? OR author LIKE ?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Get PrintWriter
        PrintWriter pw = res.getWriter();
        // Set content type
        res.setContentType("text/html");

        // LOAD JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }

        // Generate the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///newbook", "root", "tiger");
                PreparedStatement ps = con.prepareStatement(query);
        		PreparedStatement searchPs = con.prepareStatement(searchQuery))
        {
        	String searchKeyword = req.getParameter("search");
        	 ResultSet rs;
             if (searchKeyword != null && !searchKeyword.isEmpty()) {
                 searchPs.setString(1, "%" + searchKeyword + "%");
                 searchPs.setString(2, "%" + searchKeyword + "%");
                 rs = searchPs.executeQuery();
             } else {
                 rs = ps.executeQuery();
             }

             pw.println("<!DOCTYPE html>");
             pw.println("<html>");
             pw.println("<head>");
             pw.println("<style>");
             // Enhanced CSS styles
             pw.println("body { font-family: Arial, sans-serif; background-image: url('https://images4.alphacoders.com/132/1326368.png'); background-size: cover;}");
             pw.println("table { border-collapse: collapse; width: 80%; margin: 50px auto; border: 1px solid #ddd; border-radius: 8px; background-color: white; }");
             pw.println("th, td { border: 1px solid #dddddd; text-align: left; padding: 12px; }");
             pw.println("th { background-color: #f2f2f2; }");
             pw.println("tr:nth-child(even) { background-color: #f8f8f8; }");
             pw.println("tr:hover { background-color: #f0f0f0; }");
             pw.println("a.button { text-decoration: none; background-color: #4CAF50; color: white; padding: 10px 20px; display: inline-block; border-radius: 5px; }");
             pw.println("a.button:hover { background-color: #45a049; }");
             pw.println(".navbar { overflow: hidden; background-color: #333; position: relative; }");
             pw.println(".navbar a { float: left; display: block; color: #f2f2f2; text-align: center; padding: 14px 20px; text-decoration: none; }");
             pw.println(".navbar a:hover { background-color: #ddd; color: black; }");
             pw.println(".search-container { float: right; }");
             pw.println(".search-container input[type=text] { padding: 6px; margin-top: 8px; font-size: 17px; border: none; }");
             pw.println(".search-container button { padding: 6px 10px; margin-top: 8px; background: #ddd; font-size: 17px; border: none; cursor: pointer; }");
             pw.println("</style>");
             pw.println("</head>");
             pw.println("<body>");
             // Navbar
             pw.println("<div class='navbar'>");
             pw.println("<a href='home.html'>Home</a>");
             pw.println("<div class='search-container'>");
             pw.println("<form action='bookList' method='GET'>");
             pw.println("<input type='text' name='search' placeholder='Search...'>");
             pw.println("<button type='submit'>Search</button>");
             pw.println("</form>");
             pw.println("</div>");
             pw.println("</div>");
             pw.println("</body>");
             pw.println("</html>");

            pw.println("<table>");
            pw.println("<tr>");
            pw.println("<th>Book Id</th>");
            pw.println("<th>Book Name</th>");
            pw.println("<th>Author</th>");
            pw.println("<th>Copies</th>");
            pw.println("<th>Book Edition</th>");
            pw.println("<th>Book Price</th>");
            pw.println("<th>Edit</th>");
            pw.println("<th>Delete</th>");
            pw.println("</tr>");
            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getInt(1) + "</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getString(3) + "</td>");
                pw.println("<td>" + rs.getInt(4) + "</td>");
                pw.println("<td>" + rs.getString(5) + "</td>");
                pw.println("<td>" + rs.getFloat(6) + "</td>");
                pw.println("<td><a class='button' href='editScreen?id=" + rs.getInt(1) + "'>Edit</a></td>");
                pw.println("<td><a class='button' href='deleteurl?id=" + rs.getInt(1) + "'>Delete</a></td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</body>");
            pw.println("</html>");
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}


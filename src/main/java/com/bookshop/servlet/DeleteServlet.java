//package com.bookshop.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//@WebServlet("/deleteurl")
//public class DeleteServlet extends HttpServlet {
//	private static final String query = "delete from BOOKDATA where id=?";
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        //get PrintWriter
//        PrintWriter pw = res.getWriter();
//        //set content type
//        res.setContentType("text/html");
//        //get the id of record
//        int id = Integer.parseInt(req.getParameter("id"));
//
//        //LOAD jdbc driver
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException cnf) {
//            cnf.printStackTrace();
//        }
//        //generate the connection
//        try (Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "tiger"); 
//        	PreparedStatement ps = con.prepareStatement(query);) {
//            ps.setInt(1, id);
//            int count = ps.executeUpdate();
//            if (count == 1) {
//            	pw.println("<body style='background-image: url(\"book1.jpg\"); background-size: cover;'>");
//
//            	 pw.println("<div style='max-width: 800px; margin: 50px auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);'>");
//                 pw.println("<h2 style='color: #333;'>Record is Deleted Successfully</h2>");
//               
//            } else {
//            	pw.println("<body style='background-image: url(\"book1.jpg\"); background-size: cover;'>");
//
//            	 pw.println("<div style='max-width: 800px; margin: 50px auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);'>");
//                 pw.println("<h2 style='color: red;'>Record is not Deleted Successfully</h2>");
//              
//            }
//        } catch (SQLException se) {
//            se.printStackTrace();
//            pw.println("<h1>" + se.getMessage() + "</h2>");
//        } catch (Exception e) {
//            e.printStackTrace();
//            pw.println("<h1>" + e.getMessage() + "</h2>");
//        }
//        pw.println("<a href='home.html'>Home</a>");
//        pw.println("<br>");
//        pw.println("<a href='bookList'>Book List</a>");
//
//    }
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
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
    private static final String query = "delete from DATA where id=?";

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
            int count = ps.executeUpdate();

            pw.println("<html>");
            pw.println("<head>");
            pw.println("<style>");
            pw.println("body { font-family: Arial, sans-serif; background-image: url('https://images4.alphacoders.com/132/1326368.png'); background-size: cover;}");
            pw.println(".message { max-width: 800px; margin: 50px auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center;}");
            pw.println(".success { color: #333; }");
            pw.println(".error { color: red; }");
            pw.println(".navbar { overflow: hidden; background-color: #333; }");
            pw.println(".navbar a { float: left; display: block; color: #f2f2f2; text-align: center; padding: 14px 20px; text-decoration: none; }");
            pw.println(".navbar a:hover { background-color: #ddd; color: black; }");
            pw.println("</style>");
            pw.println("</head>");
            pw.println("<body>");

            // Navbar
            pw.println("<div class='navbar'>");
            pw.println("<a href='home.html'>Home</a>");
            pw.println("<a href='bookList'>Book List</a>");
            pw.println("</div>");

            pw.println("<div class='message'>");

            if (count == 1) {
                pw.println("<h2 class='success'>Record is Deleted Successfully</h2>");
            } else {
                pw.println("<h2 class='error'>Record is not Deleted Successfully</h2>");
            }

            pw.println("</div>");

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

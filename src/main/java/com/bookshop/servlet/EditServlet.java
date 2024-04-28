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

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
    private static final String QUERY = "UPDATE DATA SET BOOKNAME=?, AUTHOR=?, COPIES=?, BOOKEDITION=?, BOOKPRICE=? WHERE id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        int id = Integer.parseInt(req.getParameter("id"));
        String bookName = req.getParameter("bookName");
        String author = req.getParameter("author");
        int bookCopy = Integer.parseInt(req.getParameter("copies"));
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql:///newbook", "root", "tiger");
             PreparedStatement ps = con.prepareStatement(QUERY);) {
            ps.setString(1, bookName);
            ps.setString(2, author);
            ps.setInt(3, bookCopy);
            ps.setString(4, bookEdition);
            ps.setFloat(5, bookPrice);
            ps.setInt(6, id);
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
            pw.println("<div class='navbar'>");
            pw.println("<a href='home.html'>Home</a>");
            pw.println("<a href='bookList'>Book List</a>");
            pw.println("</div>");
            pw.println("<div class='message'>");

            if (count == 1) {
                pw.println("<html><head><title>Success</title></head><body>");
                pw.println("<h2>Record Edited Successfully</h2>");
            } else {
                pw.println("<html><head><title>Error</title></head><body>");
                pw.println("<h2 style='color: red;'>Failed to Edit Record</h2>");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>Error: " + se.getMessage() + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
        } 
//        finally {
//            pw.println("<a href='home.html'>Home</a><br>");
//            pw.println("<a href='bookList'>Book List</a>");
//            pw.println("</body></html>");
//            pw.close(); // Close PrintWriter
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}

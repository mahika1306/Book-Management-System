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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final String SELECT_QUERY = "SELECT COUNT(*) FROM data WHERE bookName=? AND author=?  AND bookEdition=?";
    private static final String INSERT_QUERY = "INSERT INTO data(bookName, author, copies, bookEdition, bookPrice) VALUES (?, ?, ?, ?, ?)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        String bookName = req.getParameter("bookName");
        String author = req.getParameter("author");
        int bookCopy = Integer.parseInt(req.getParameter("bookCopies"));
        String bookEdition = req.getParameter("bookEdition");
       
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DriverManager.getConnection("jdbc:mysql:///newbook", "root", "tiger");
                 PreparedStatement selectStatement = con.prepareStatement(SELECT_QUERY);
                 PreparedStatement insertStatement = con.prepareStatement(INSERT_QUERY)) {

                // Check if book with the same name, edition, and author already exists
                selectStatement.setString(1, bookName);
                selectStatement.setString(2, author);
                selectStatement.setString(3, bookEdition);
               
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);

//                    pw.println("<html>");
//                    pw.println("<head>");
//                    pw.println("<style>");
//                    // Styling goes here
//                    pw.println("</style>");
//                    pw.println("</head>");
//                    pw.println("<body>");
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

                    if (count > 0) {
                        pw.println("<div class='message error'>Book already exists!</div>");
                    } else {
                        // Book does not exist, proceed with insertion
                        insertStatement.setString(1, bookName);
                        insertStatement.setString(2, author);
                        insertStatement.setInt(3, bookCopy);
                        insertStatement.setString(4, bookEdition);
                        insertStatement.setFloat(5, bookPrice);
                        int insertCount = insertStatement.executeUpdate();
                        if (insertCount == 1) {
                            pw.println("<div class='message success'>Record is registered successfully</div>");
                        } else {
                            pw.println("<div class='message error'>Failed to register record</div>");
                        }
                    }

                    pw.println("</body>");
                    pw.println("</html>");
                }
            }
        } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
            // Log the exception properly instead of printing to response
            e.printStackTrace();
            pw.println("<html><body><h1>Failed to register record due to invalid input</h1></body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}

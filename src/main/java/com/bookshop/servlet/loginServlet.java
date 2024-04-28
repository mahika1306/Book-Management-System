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

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    private static final String query = "SELECT username FROM login WHERE username=? AND password=?";
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        String user = req.getParameter("uname");
        String pass = req.getParameter("pwd");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql:///newbook", "root", "tiger");
                 PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, user);
                ps.setString(2, pass);
                
                ResultSet rs = ps.executeQuery();
                boolean accountFound = rs.next();
                
                pw.println("<html>");
                pw.println("<head>");
                pw.println("<title>Login</title>");
                pw.println("<style>");
                pw.println(".navbar {");
                pw.println("  overflow: hidden;");
                pw.println("  background-color: #333;");
                pw.println("}");
                pw.println(".navbar a {");
                pw.println("  float: left;");
                pw.println("  display: block;");
                pw.println("  color: #f2f2f2;");
                pw.println("  text-align: center;");
                pw.println("  padding: 14px 20px;");
                pw.println("  text-decoration: none;");
                pw.println("}");
                pw.println(".navbar a:hover {");
                pw.println("  background-color: #ddd;");
                pw.println("  color: black;");
                pw.println("}");
                pw.println(".container {");
                pw.println("  max-width: 800px;");
                pw.println("  margin: 50px auto;");
                pw.println("  padding: 40px;");
                pw.println("  background-color: #fff;");
                pw.println("  border-radius: 10px;");
                pw.println("  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
                pw.println("  text-align: center;");
                pw.println("}");
                pw.println(".login-successful {");
                pw.println("  font-size: 24px;");
                pw.println("}");
                pw.println(".welcome {");
                pw.println("  font-size: 28px;");
                pw.println("}");
                pw.println("</style>");
                pw.println("</head>");
                pw.println("<body style='font-family: Arial, sans-serif; background-image: url('https://images4.alphacoders.com/132/1326368.png'); background-size: cover; margin: 0; padding: 0;'>");
                
                // Conditionally include the navbar
                if (accountFound) {
                    pw.println("<div class='navbar'>");
                    pw.println("  <a href='home.html'>Home</a>");
                    pw.println("  <a href='bookList'>Book List</a>");
                    pw.println("</div>");
                }
                
                pw.println("<div class='container'>");
                if (accountFound) {
                    String username = rs.getString("username");
                    pw.println("<h2 class='welcome' style='color: #333;'>Welcome, " + username + "!</h2>");
                    pw.println("<p class='login-successful'>Login Successful</p>");
                    pw.println("<p>Welcome to our Book Management System. You can manage your books, explore new arrivals, and much more.</p>");
                } else {
                    pw.println("<h2 style='color: red;'>Usename or password is incorrect</h2>");
                }
                pw.println("</div>");
                pw.println("</body>");
                pw.println("</html>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h1>");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}

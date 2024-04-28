//package com.bookshop.servlet;
//
//
//
//
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
//
//@WebServlet("/signup")
//public class signupServlet extends HttpServlet {
//    private static final String query = "INSERT INTO login(username, password) VALUES (?,?)";
//
//    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        PrintWriter pw = res.getWriter();
//        res.setContentType("text/html");
//
//        String username = req.getParameter("uname");
//        String password = req.getParameter("pwd");
//      
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            try (Connection con = DriverManager.getConnection("jdbc:mysql:///newbook", "root", "tiger");
//                 PreparedStatement ps = con.prepareStatement(query)) {
//                ps.setString(1, username);
//                ps.setString(2, password);
//              
//
//                int count = ps.executeUpdate();
//
//                pw.println("<html>");
//                pw.println("<head>");
//                pw.println("<title>Signup</title>");
//                pw.println("<style>");
//                pw.println(".container {");
//                pw.println("  max-width: 800px;");
//                pw.println("  margin: 50px auto;");
//                pw.println("  padding: 40px;");
//                pw.println("  background-color: #fff;");
//                pw.println("  border-radius: 10px;");
//                pw.println("  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
//                pw.println("  text-align: center;");
//                pw.println("}");
//                pw.println(".message {");
//                pw.println("  font-size: 24px;");
//                pw.println("  margin-bottom: 20px;");
//                pw.println("}");
//                pw.println("</style>");
//                pw.println("</head>");
//                pw.println("<body>");
//                pw.println("<div class='container'>");
//
//                if (count == 1) {
//                    pw.println("<p class='message'>Signup successful for username: " + username + "</p>");
//                } else {
//                    pw.println("<p class='message' style='color: red;'>Signup failed. Please try again.</p>");
//                }
//
//                pw.println("<a href='login.html'>Go to Login</a>");
//                pw.println("</div>");
//                pw.println("</body>");
//                pw.println("</html>");
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            pw.println("<h1>" + e.getMessage() + "</h1>");
//        }
//    }
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        doGet(req, res);
//    }
//}
//
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
@WebServlet("/signup")
public class signupServlet extends HttpServlet {
    private static final String queryCheck = "SELECT COUNT(*) FROM login WHERE username=?";
    private static final String queryInsert = "INSERT INTO login(username, password) VALUES (?,?)";

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        String username = req.getParameter("uname");
        String password = req.getParameter("pwd");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql:///newbook", "root", "tiger");
                 PreparedStatement psCheck = con.prepareStatement(queryCheck);
                 PreparedStatement psInsert = con.prepareStatement(queryInsert)) {

                // Check if username already exists
                psCheck.setString(1, username);
                ResultSet rs = psCheck.executeQuery();
                rs.next();
                int existingUsers = rs.getInt(1);

                pw.println("<html>");
                pw.println("<head>");
                pw.println("<title>Signup</title>");
                pw.println("<style>");
                pw.println(".container {");
                pw.println("  max-width: 800px;");
                pw.println("  margin: 50px auto;");
                pw.println("  padding: 40px;");
                pw.println("  background-color: #fff;");
                pw.println("  border-radius: 10px;");
                pw.println("  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
                pw.println("  text-align: center;");
                pw.println("}");
                pw.println(".message {");
                pw.println("  font-size: 24px;");
                pw.println("  margin-bottom: 20px;");
                pw.println("}");
                pw.println("</style>");
                pw.println("</head>");
                pw.println("<body>");
                pw.println("<div class='container'>");

                if (existingUsers > 0) {
                    // Username already exists
                    pw.println("<p class='message' style='color: red;'>Username already exists. Please choose a different one.</p>");
                    pw.println("<a href='signup.html'>Go back to Signup</a>");
                } else {
                    // Insert new user
                    psInsert.setString(1, username);
                    psInsert.setString(2, password);
                    int count = psInsert.executeUpdate();
                    if (count == 1) {
                        pw.println("<p class='message'>Signup successful for username: " + username + "</p>");
                    } else {
                        pw.println("<p class='message' style='color: red;'>Signup failed. Please try again.</p>");
                    }
                    pw.println("<a href='login.html'>Go to Login</a>");
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

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Forward GET requests to the doPost method
        doPost(req, res);
    }
}


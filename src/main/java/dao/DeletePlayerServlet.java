package dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeletePlayerServlet extends HttpServlet {


    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/playerregistration_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "oms123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM playerregistration WHERE id = ?")) {

            statement.setInt(1, Integer.parseInt(id));
            statement.executeUpdate();

            response.sendRedirect("master.html");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

}

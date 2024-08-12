package dao;

import model.PlayerRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/viewRecords")
public class ViewRecordsServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/playerregistration_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "oms123";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PlayerRegistration> playerList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a database connection
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Prepare SQL query
            String sql = "SELECT * FROM playerregistration";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                PlayerRegistration p = new PlayerRegistration();
                p.setId(resultSet.getInt("id"));
                p.setFullName(resultSet.getString("fullName"));
                p.setAge(resultSet.getString("age"));
                p.setGender(resultSet.getString("gender"));
                p.setPhoneNo(resultSet.getString("phoneNo"));
                p.setEmail(resultSet.getString("email"));
                p.setCurrentlyTraining(resultSet.getString("currentlyTraining"));
                p.setTraining(resultSet.getString("training"));
                p.setOther(resultSet.getString("other"));
                p.setDate(resultSet.getString("date"));
                p.setAddress(resultSet.getString("address"));
                playerList.add(p);
            }

            // Set the list of players as a request attribute
            request.setAttribute("playerRegistration", playerList);

            // Forward to JSP page
            request.getRequestDispatcher("viewRecords.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            response.sendRedirect("error.jsp");
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

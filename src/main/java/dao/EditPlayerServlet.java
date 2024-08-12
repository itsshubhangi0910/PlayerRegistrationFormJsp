package dao;

import lombok.SneakyThrows;
import model.PlayerRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/editPlayer")
public class EditPlayerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String URL = "jdbc:mysql://localhost:3306/playerregistration_db";
    private static final String USER = "root";
    private static final String PASSWORD = "oms123"; // Update with your database password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure MySQL driver is loaded
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("update".equals(action)) {
            handleEditRequest(request, response);
        } else {
            response.sendRedirect("playerRegistration");
        }
    }

    private void handleEditRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String query = "SELECT * FROM playerregistration WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PlayerRegistration playerRegistration = new PlayerRegistration(
                            rs.getInt("id"),
                            rs.getString("firstName"),
                            rs.getString("age"),
                            rs.getString("gender"),
                            rs.getString("phoneNo"),
                            rs.getString("email"),
                            rs.getString("currentlyTraining"),
                            rs.getString("training"),
                            rs.getString("other"),
                            rs.getString("date"),
                            rs.getString("address")
                    );
                    request.setAttribute("playerRegistration", playerRegistration);
                    request.getRequestDispatcher("/editPlayer.jsp").forward(request, response);
                } else {
                    response.sendRedirect("playerRegistration");
                }
            }
        } catch (SQLException e) {
            handleError(request, response, "Error retrieving player", e);
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "update":
                updatePlayer(request, response);
                break;
            default:
                response.sendRedirect("playerregistration");
                break;
        }
    }

    private void updatePlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String phoneNo = request.getParameter("phoneNo");
        String email = request.getParameter("email");
        String currentlyTraining = request.getParameter("currentlyTraining");
        String training = request.getParameter("training");
        String other = request.getParameter("other");
        String date = request.getParameter("date");
        String address = request.getParameter("address");

        if (fullName == null || age == null || gender == null || phoneNo == null || email == null || currentlyTraining == null ||
                training == null || other == null || date == null || address == null ||
                fullName.isEmpty() || age.isEmpty() || gender.isEmpty() || phoneNo.isEmpty() || email.isEmpty() || currentlyTraining.isEmpty() ||
                training.isEmpty() || other.isEmpty() || date.isEmpty() || address.isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/editPlayer.jsp").forward(request, response);
            return ;
        }

        String query = "UPDATE playerregistration SET fullName = ?, age = ?, gender = ?, phoneNo = ?, email = ?, currentlyTraining = ?, training = ?, other = ?, date = ?, address = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, fullName);
            stmt.setString(2, age);
            stmt.setString(3, gender);
            stmt.setString(4, phoneNo);
            stmt.setString(5, email);
            stmt.setString(6, currentlyTraining);
            stmt.setString(7, training);
            stmt.setString(8, other);
            stmt.setString(9, date);
            stmt.setString(10, address);
            stmt.setInt(11, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("playerregistration");
            } else {
                request.setAttribute("error", "Player not found.");
                request.getRequestDispatcher("/editPlayer.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            handleError(request, response, "Error updating player", e);
        }
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String message, Exception e) throws ServletException, IOException {
        // Log the exception (you might want to use a logging framework here)
        e.printStackTrace();
        // Forward to an error page with a user-friendly message
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}

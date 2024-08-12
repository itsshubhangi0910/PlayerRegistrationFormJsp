package dao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import java.util.Properties;


@WebServlet("/playerrgistration")
public class PlayerRegistrationServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/playerregistration_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "oms123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
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


        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a database connection
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Prepare SQL query
            String sql = "INSERT INTO playerregistration (fullName, age, gender, phoneNo, email, currentlyTraining, training, other, date,address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, fullName);
            statement.setString(2, age);
            statement.setString(3, gender);
            statement.setString(4, phoneNo);
            statement.setString(5, email);
            statement.setString(6, currentlyTraining);
            statement.setString(7, training);
            statement.setString(8, other);
            statement.setString(9, date);
            statement.setString(10, address);


            // Execute the update
            statement.executeUpdate();

            sendEmail(fullName, email);

            // Redirect to success page
            response.sendRedirect("success.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            response.sendRedirect("error.jsp");
        } finally {
            // Close resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle resource closure exceptions
            }
        }
    }
    private void sendEmail(String fullName, String email) {
        // Email configurations
        final String fromEmail = "shubhangi.omsoftware1@gmail.com"; // sender email
        final String fromEmailPassword = "yuxc qdif betv jizt"; // sender email password
        String host = "smtp.gmail.com"; // SMTP server

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // SMTP port

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromEmailPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Enquiry Submission Confirmation");
            message.setText("Dear " + fullName + ",\n\nThank you for your enquiry. We will get back to you soon.\n\nBest Regards,\nYour Team");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

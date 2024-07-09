package by.Belaya.implement;

import by.Belaya.dao.ClientDAO;
import by.Belaya.model.Client;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private static final String INSERT_CLIENT_SQL = "INSERT INTO Client (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    @Override
    public void addClient(Client client) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setString(4, client.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            // Загружаем драйвер
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Устанавливаем соединение
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC драйвер не найден", e);
        }
    }
}
package by.Belaya.service;

import by.Belaya.model.Request;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelService {
    private Connection connection;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public int getClientId(String email, String password) {//ищем номер клиента с таким паролем и логином
        try {
            connection = getConnection();
            String query = "SELECT client_id FROM Client WHERE email = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);//запись вместо ?
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Если есть результат, возвращаем client_id
                        return resultSet.getInt("client_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Обработка ошибок (в реальном проекте лучше использовать логирование)
        }
        // Если пользователя с такими email и password нет, возвращаем -1 или другое значение по умолчанию
        return -1;
    }

    public boolean bookAccommodation(Request request) {//создание запроса на заселение и запись в бд
        String insertQuery = "INSERT INTO request (client_id, manCounter, arrival_date, night, type ) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            // Устанавливаем параметры запроса
            preparedStatement.setInt(1, request.getClientId());
            preparedStatement.setInt(2, request.getManCounter());
            preparedStatement.setDate(3, request.getArrivalDate());
            preparedStatement.setInt(4, request.getNight());
            preparedStatement.setString(5, request.getType());

            // Выполняем запрос
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                // Получаем сгенерированный ключ (если он используется)
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int requestId = generatedKeys.getInt(1);
                        request.setRequestId(requestId);
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    public List<Request> getAllRequests() {//проход по всей бд request и получение всей инфы о каждом запросе
        List<Request> requests = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM request";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        Request request = new Request();
                        request.setRequestId(resultSet.getInt("request_id"));
                        request.setClientId(resultSet.getInt("client_id"));
                        request.setStatus(resultSet.getString("status"));
                        request.setManCounter(resultSet.getInt("manCounter"));
                        request.setArrivalDate(Date.valueOf(resultSet.getDate("arrival_date").toLocalDate()));
                        request.setNight(resultSet.getInt("night"));
                        request.setType(resultSet.getString("type"));

                        requests.add(request);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public List<Request> getAllAcceptedRequests() {// тоже что и сверху, только для принятых
        List<Request> requests = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM requeststatus";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        Request request = new Request();
                        request.setClientId(resultSet.getInt("client_id"));
                        request.setRoomId(resultSet.getInt("room_id"));
                        request.setArrivalDate(Date.valueOf(resultSet.getDate("begin").toLocalDate()));
                        request.setEndDate(Date.valueOf(resultSet.getDate("last_day").toLocalDate()));
                        request.setFinalPrice(resultSet.getDouble("final_price"));
                        requests.add(request);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public Request getRequestById(int requestId) {//поиск заявки по номеру
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM Request WHERE request_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, requestId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Создаем объект Request и заполняем его данными из результата запроса
                        Request request = new Request();
                        request.setRequestId(resultSet.getInt("request_id"));
                        request.setClientId(resultSet.getInt("client_id"));
                        request.setStatus(resultSet.getString("status"));

                        request.setManCounter(resultSet.getInt("manCounter"));
                        request.setArrivalDate(Date.valueOf(resultSet.getDate("arrival_date").toLocalDate()));
                        request.setNight(resultSet.getInt("night"));
                        request.setType(resultSet.getString("type"));

                        return request;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void approveRequest(int requestId) {//подтвердить заявку
        try {
            String sql = "UPDATE request SET status = 'Принято' WHERE request_id = ?";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, requestId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void denyRequest(int requestId) {//отказать в заявке
        try {
            String sql = "UPDATE request SET status = 'Отказано' WHERE request_id = ?";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, requestId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String check(int requestId) {//проверить на возможность заселения
        String sql = "SELECT manCounter, client_id, type, night, arrival_date FROM request WHERE request_id = ?";

        int mans;
        int client_id;
        String type;
        int night;
        Date begin;

        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, requestId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    mans = resultSet.getInt("manCounter");
                    client_id = resultSet.getInt("client_id");
                    type = resultSet.getString("type");
                    night = resultSet.getInt("night");
                    begin = Date.valueOf(resultSet.getDate("arrival_date").toLocalDate());

                    int availableRoomId = getAvailableRoomId(mans, type, begin, night);//поиск подходящего номера комнаты
                    double totalCost = calculateTotalCost(availableRoomId, night);//подсчет суммы
                    if (availableRoomId != -1) {
                        reserveRoom(availableRoomId, client_id, begin, night, totalCost);//бронирование номера
                        return "Комната успешно зянята";
                    } else {
                        return "Нет подходящей комнаты";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Нет подходящей комнаты";
    }

    public int getAvailableRoomId(int mans, String type, Date checkInDate, int nights) {//поиск номера нужной комнаты
        String roomSql = "SELECT room_id FROM room WHERE volume >= ? AND type = ?";
        List<Integer> occupiedRooms = getOccupiedRooms(checkInDate, nights);
        //получение списка всех занятых в этот момент комнат

        try (Connection connection = getConnection();
             PreparedStatement roomStatement = connection.prepareStatement(roomSql)) {

            roomStatement.setInt(1, mans);
            roomStatement.setString(2, type);

            try (ResultSet roomResultSet = roomStatement.executeQuery()) {
                while (roomResultSet.next()) {
                    int roomId = roomResultSet.getInt("room_id");
                    if (!occupiedRooms.contains(roomId)) {
                        return roomId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Возвращаем -1, если нет доступных комнат
    }

    //получение списка всех занятых в этот момент комнат
    public List<Integer> getOccupiedRooms(Date checkInDate, int nights) {
        String sql = "SELECT DISTINCT room_id FROM requeststatus WHERE begin <= ? AND last_day >= ?";

        List<Integer> occupiedRooms = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDate(1, checkInDate);
            statement.setDate(2, Date.valueOf(checkInDate.toLocalDate().plusDays(nights)));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    occupiedRooms.add(resultSet.getInt("room_id"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return occupiedRooms;
    }

    public void reserveRoom(int roomId, int clientId, Date checkInDate, int nights, double totalCost) {//юронь комнаты
        String insertSql = "INSERT INTO requeststatus (room_id, client_id, begin, last_day, final_price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {

            // Вычисляем дату окончания пребывания
            LocalDate endDate = checkInDate.toLocalDate().plusDays(nights);

            insertStatement.setInt(1, roomId);
            insertStatement.setInt(2, clientId);
            insertStatement.setDate(3, Date.valueOf(checkInDate.toLocalDate()));
            insertStatement.setDate(4, Date.valueOf(endDate));
            insertStatement.setDouble(5, totalCost);

            // Выполняем SQL-запрос для вставки новой записи
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double calculateTotalCost(int roomId, int nights) {//подсчте стоимости
        String selectSql = "SELECT night_price FROM room WHERE room_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {

            selectStatement.setInt(1, roomId);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    double pricePerNight = resultSet.getDouble("night_price");
                    return pricePerNight * nights;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0; // В случае ошибки возвращаем 0.0
    }
}
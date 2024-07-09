package by.Belaya.controller;

import by.Belaya.model.Request;
import by.Belaya.service.HotelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    private HotelService hotelService = new HotelService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Request> allAcceptedRequests = hotelService.getAllAcceptedRequests();

        request.setAttribute("allAcceptedRequests", allAcceptedRequests);

        List<Request> allBadRequests = hotelService.getAllRequests();

        request.setAttribute("allBadRequests", allBadRequests);

        request.getRequestDispatcher("views/requestRoom.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Получение данных из запроса(с сайта)
        int people = Integer.parseInt(request.getParameter("people"));
        LocalDate checkInDate = LocalDate.parse(request.getParameter("checkInDate"));
        int nights = Integer.parseInt(request.getParameter("nights"));
        String apartmentType = request.getParameter("apartmentType");

        HttpSession session = request.getSession();
        int clientId = (int) session.getAttribute("clientId");//запоминание клиент айди

        // Создание объекта запроса на проживание
        Request accommodationRequest = new Request();
        accommodationRequest.setManCounter(people);
        accommodationRequest.setArrivalDate(Date.valueOf(checkInDate));
        accommodationRequest.setNight(nights);
        accommodationRequest.setType(apartmentType);
        accommodationRequest.setClientId(clientId);

        // Добавление записи в базу данных
        boolean success = hotelService.bookAccommodation(accommodationRequest);

        // Обработка результата
        if (success) {
            // Запись успешно добавлена
            response.sendRedirect("views/request-success.jsp");
        } else {
            response.sendRedirect("/error");
        }
    }
}
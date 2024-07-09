package by.Belaya.controller;

import by.Belaya.model.Request;
import by.Belaya.service.HotelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/approveRequest")
public class ApproveServlet extends HttpServlet {
    private HotelService hotelService = new HotelService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestId = request.getParameter("requestId");

        if (requestId != null && !requestId.isEmpty()) {
            // Вызов метоa сервиса, который изменяет статус заявки на "accepted"
            hotelService.approveRequest(Integer.parseInt(requestId));

            List<Request> allRequests = hotelService.getAllRequests();//создание списка всех запросов
            request.setAttribute("allRequests", allRequests);//отправка их на код страницы с названиеm allRequests
            request.getRequestDispatcher("views/adminPage.jsp").forward(request, response);
        } else {
            // Обработка ошибки, если не удалось получить идентификатор заявки
            response.sendRedirect("/error");
        }
    }
}

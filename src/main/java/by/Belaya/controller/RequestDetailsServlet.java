
package by.Belaya.controller;

import by.Belaya.model.Request;
import by.Belaya.service.HotelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requestDetails")
public class RequestDetailsServlet extends HttpServlet {
    private HotelService hotelService = new HotelService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем идентификатор запроса из параметра запроса
        String requestId = request.getParameter("requestId");

        if (requestId != null && !requestId.isEmpty()) {
            // Получаем данные запроса по идентификатору
            Request requestDetails = hotelService.getRequestById(Integer.parseInt(requestId));

            if (requestDetails != null) {
                // Передаем данные запроса на страницу деталей
                request.setAttribute("requestDetails", requestDetails);
                request.getRequestDispatcher("views/requestDetails.jsp").forward(request, response);
                return;
            }
        }

        // Если что-то пошло не так, перенаправляем на страницу ошибки
        response.sendRedirect("/error");
    }
}

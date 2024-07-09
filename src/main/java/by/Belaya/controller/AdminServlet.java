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

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private HotelService hotelService = new HotelService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Request> allRequests = hotelService.getAllRequests();//создание списка всех запросов
        request.setAttribute("allRequests", allRequests);//отправка их на код страницы с названиеm allRequests
        request.getRequestDispatcher("views/adminPage.jsp").forward(request, response);
    }
}

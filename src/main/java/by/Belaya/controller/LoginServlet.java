package by.Belaya.controller;

import by.Belaya.service.HotelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private HotelService hotelService = new HotelService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            //берем параметры со страницы
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        //проверям нажатие кнопки
        if ("Entrance".equals(action)) {
            int clientId = hotelService.getClientId(username, password);

            if ("admin".equals(username) && "987".equals(password)) {
                response.sendRedirect("/admin");
            } else if (clientId != -1) {

                // Сохраняем clientId в сессии
                HttpSession session = request.getSession();
                session.setAttribute("clientId", clientId);

                response.sendRedirect("/client");
            } else {
                request.setAttribute("errorMessage", "Неверный логин или пароль");
                request.getRequestDispatcher("views/main.jsp").forward(request, response);
            }
        } else if ("Registration".equals(action)) {
            response.sendRedirect("/registration");
        } else {
            response.sendRedirect("/error");
        }
    }
}

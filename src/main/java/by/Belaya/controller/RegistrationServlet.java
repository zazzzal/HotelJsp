package by.Belaya.controller;

import by.Belaya.dao.ClientDAO;
import by.Belaya.implement.ClientDAOImpl;
import by.Belaya.model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Перенаправление управления на JSP-страницу
        req.getRequestDispatcher("views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //берем со странички текст
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // создание объекта Client с полученными данными
        Client client = new Client(firstName, lastName, email, password);

        // использование вашего DAO для регистрации
        ClientDAO clientDAO = new ClientDAOImpl();
        clientDAO.addClient(client);

        // дополнительные действия, например, перенаправление на другую страницу
        response.sendRedirect("views/registration-success.jsp");
    }
}

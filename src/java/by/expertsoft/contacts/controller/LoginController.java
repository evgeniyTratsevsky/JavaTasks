package by.expertsoft.contacts.controller;

import by.expertsoft.contacts.classes.Constants;
import by.expertsoft.contacts.dao.DAOFactory;
import by.expertsoft.contacts.dao.UserDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        ServletContext application = getServletConfig().getServletContext();

        if (application.getAttribute(Constants.SESSION_LOGIN) == null) {
            String login = request.getParameter(Constants.USER_LOGIN);
            String password = request.getParameter(Constants.USER_PASSWORD);

            if (login != null && password != null) {
                UserDAO userDAO = (UserDAO) DAOFactory.getDAO(Constants.USER_DAO);
                int userID = userDAO.findUserID(login, password);
                if (userID != -1) {
                    String fio = userDAO.getFIO(userID);
                    application.setAttribute(Constants.SESSION_LOGIN, "Сеанс: " + login);
                    request.setAttribute(Constants.LOGIN_SUCCESS, fio + ", ваши данные подтверждены!");
                    forward(Constants.CONTACTS_CONTROLLER, request, response);
                } else {
                    request.setAttribute(Constants.LOGIN_SUCCESS, Constants.BadLoginEntry);
                    forward(Constants.LOGIN_PAGE, request, response);
                }

            }
        } else {
            request.setAttribute(Constants.LOGIN_SUCCESS, "ВАШ СЕАНС УЖЕ ОТКРЫТ !");
            forward(Constants.LOGIN_PAGE, request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void forward(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

}

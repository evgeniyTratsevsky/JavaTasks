package by.expertsoft.contacts.controller;

import by.expertsoft.contacts.classes.Constants;
import by.expertsoft.contacts.classes.User;
import by.expertsoft.contacts.dao.DAOFactory;
import by.expertsoft.contacts.dao.UserDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter(Constants.USER_NAME);
        String surName = request.getParameter(Constants.USER_SURNAME);
        String login = request.getParameter(Constants.USER_LOGIN);
        String password = request.getParameter(Constants.USER_PASSWORD);
        String passwordConfirm = request.getParameter(Constants.USER_PASSWORD_CONFIRM);
        String email = request.getParameter(Constants.USER_EMAIL);
        String phoneNumber = request.getParameter(Constants.USER_PHONE_NUMBER);
        String btnRegText=request.getParameter(Constants.BTN_REG);

        UserDAO userDAO = (UserDAO) DAOFactory.getDAO(Constants.USER_DAO);
        User user = new User();
        if (!name.equals("") && !surName.equals("") && !login.equals("") && !password.equals("")
                && !passwordConfirm.equals("") && !email.equals("") && !phoneNumber.equals("")) {
            String checkStr = userDAO.checkUser(login, password, passwordConfirm, email, phoneNumber);
            if (checkStr.equals("") || btnRegText.equals("Edit"/*Constants.BTN_EDIT_TEXT*/)) {
                user = new User(name, surName, login, password, email, phoneNumber);
                userDAO.addUser(user);
                request.setCharacterEncoding("utf-8");
                request.setAttribute(Constants.USER_LOGIN, login);                
                request.setAttribute(Constants.REG_SUCCESS, name + " " + surName + ", ваши данные успешно добавлены ! ");
            } else {
                request.setAttribute(Constants.REG_ENTRY_ERROR, user.parseString(checkStr));
            }
        } else {
            request.setAttribute(Constants.REG_ENTRY_ERROR, Constants.REG_EMPTY_ENTRY_ERROR);
        }

        forward(Constants.REG_PAGE, request, response);
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
    }// </editor-fold>

    protected void forward(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

}

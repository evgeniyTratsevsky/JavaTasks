package by.expertsoft.contacts.controller;

import by.expertsoft.contacts.classes.Constants;
import by.expertsoft.contacts.classes.ReadCSV;
import by.expertsoft.contacts.classes.User;
import by.expertsoft.contacts.dao.DAOFactory;
import by.expertsoft.contacts.dao.UserDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");   
        UserDAO userDAO = (UserDAO) DAOFactory.getDAO(Constants.USER_DAO);
      
        if (request.getParameter(Constants.PAGE) != null){
            int numPage=Integer.parseInt(request.getParameter(Constants.PAGE));            
            request.setAttribute(Constants.USERS, userDAO.getUsers(numPage));
            forward(Constants.CONTACTS_PAGE, request, response);
        }
        else if (request.getParameter(Constants.PARAM) != null || request.getAttribute(Constants.LOGIN_SUCCESS) != null) {
            request.setAttribute(Constants.USERS, userDAO.getUsers(0));
            forward(Constants.CONTACTS_PAGE, request, response);
        } else if (request.getAttribute(Constants.FILE_LOCATION) != null) {
            handlerContacts(request, response);
        } else if (request.getParameter(Constants.ACTION) != null) {
            String action = request.getParameter(Constants.ACTION);
            if (action.equals(Constants.BTN_ADD_CLICK)) {
                request.setAttribute(Constants.ADD_EDIT_TEXT, "Добавление нового контакта");
            } else if (action.equals(Constants.BTN_EDIT_CLICK)) {
                if (request.getParameter(Constants.RADIO) != null) {
                    String radio = request.getParameter(Constants.RADIO);
                    int userID = Integer.parseInt(radio);
                    User user = userDAO.findUser(userID);
                    request.setAttribute(Constants.USER, user);
                    request.setAttribute(Constants.ADD_EDIT_TEXT, "Редактирование контакта");
                } else {
                    request.setAttribute(Constants.RADIO_ERROR, "Не выбран контакт для редактирования!");
                    forward(Constants.CONTACTS_PAGE, request, response);
                }
            }
            forward(Constants.REG_PAGE, request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void handlerContacts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = (UserDAO) DAOFactory.getDAO(Constants.USER_DAO);
        String fileLocation = (String) request.getAttribute(Constants.FILE_LOCATION);
        List<User> contacts = (new ReadCSV().readCSV(fileLocation));
        if (!contacts.isEmpty()) {
            User user = null;
            int count = contacts.size();
            for (int i = 0; i < count; i++) {
                userDAO.addUser(contacts.get(i));
            }
            request.setAttribute(Constants.UPLOAD_SUCCESS, "Файл успешно импортирован !");
            request.setAttribute(Constants.USERS, userDAO.getUsers(0));
        } else {
            request.setAttribute(Constants.UPLOAD_SUCCESS, "Ошибка импорта файла !");
        }
        forward(Constants.CONTACTS_PAGE, request, response);
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

package by.expertsoft.contacts.classes;

import by.expertsoft.contacts.dao.UserDAO;

public class Constants {

    public static final String MAIN_PAGE = "/main.jsp";
    public static final String LOGIN_PAGE = "/login.jsp";
    public static final String REG_PAGE = "/registration.jsp";
    public static final String CONTACTS_PAGE = "/contacts.jsp";
    public static final String IMPORT_PAGE = "/import.jsp";
    public static final String CONTACTS_CONTROLLER = "/ContactsController";

    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surName";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_PASSWORD_CONFIRM = "passwordConfirm";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE_NUMBER = "phoneNumber";
    
    public static final String USER = "user";
    public static final String USERS = "users";    

    public static final String RESULT = "result";
    public static final String BadLoginEntry = "Вы ввели неправильные данные!";
    
    public static final String LOGIN_SUCCESS = "loginSuccess";

    public static final String FILE_ERROR = "fileError";
    public static final String FILE_MESSAGE_ERROR = "Неправильно выбран файл !";
    public static final String REG_ENTRY_ERROR = "regError";    
    public static final String REG_EMPTY_ENTRY_ERROR = "Все поля должны быть заполнены!";

    public static final String USER_DAO = UserDAO.class.getName();
    public static String REG_SUCCESS="regSuccess";
    public static String FILE_NAME="fileName";
    public static String FILE_LOCATION="fileLocation";
    public static String TEMP_DIRFILE="tempDirPath";
    
    public static String CONTACTS_LIST="list";
    public static String RADIO="radio";
    public static String RADIO_ERROR="radioError";
    public static String PARAM="param";
    public static String ADD_EDIT_TEXT="addEditText";    
    
    public static String ACTION="action";
    public static String BTN_REG="buttonReg";
    public static String BTN_ADD_CLICK="Добавить контакт";
    public static String BTN_EDIT_CLICK="Редактировать";
    public static String BTN_EDIT_TEXT="Редактировать";
    public static String BTN_TEXT_APPLY="Применить";
    public static String SESSION_LOGIN="sessionLogin";
    
    public static String UPLOAD_SUCCESS="uploadSuccess";
    public static String PAGE="page";
}

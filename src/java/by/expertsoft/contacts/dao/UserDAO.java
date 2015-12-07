package by.expertsoft.contacts.dao;

import by.expertsoft.contacts.classes.User;
import java.util.ArrayList;

public interface UserDAO extends DAO {
    //public User findUser(String login,String password);
    public User findUser(int id);//
    //public int findUserID(String login);
    public int findUserID(String login,String password); //
    public String checkUser(String login, String password, String passwordConfirm, String email, String phoneNumber);
    public boolean addUser  (User user);
    public String getFIO(int userID);
    public ArrayList<User> getUsers(int numPage);
}

package by.expertsoft.contacts.implDAO;

import by.expertsoft.contacts.classes.Constants;
import by.expertsoft.contacts.classes.User;
import by.expertsoft.contacts.dao.UserDAO;
import by.expertsoft.contacts.db.ConnectDB;
import java.sql.*;
import java.util.ArrayList;

public class UserImplDAO implements UserDAO {

    private Statement statement;
    private CallableStatement cstmt;
    private Connection connection;
    private ResultSet resultSet;
    private ArrayList<User> users;
    private int noOfRecords;

    User user;

    public synchronized boolean addUser(User user) {

        boolean b = false;
        try {
            connection = ConnectDB.getConnection();
            cstmt = connection.prepareCall("{call dbo.addUser(?, ?, ?, ?, ?, ?)}");
            cstmt.setString(Constants.USER_NAME, user.getName());
            cstmt.setString(Constants.USER_SURNAME, user.getSurName());
            cstmt.setString(Constants.USER_LOGIN, user.getLogin());
            cstmt.setString(Constants.USER_PASSWORD, user.getPassword());
            cstmt.setString(Constants.USER_EMAIL, user.getEmail());
            cstmt.setString(Constants.USER_PHONE_NUMBER, user.getPhoneNumber());
            if (cstmt.execute()) {
                b = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cstmt.close();
                connection.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return b;
    }

    public User findUser(int userID) {
        try {
            connection = ConnectDB.getConnection();
            statement = connection.createStatement();
            if (statement.execute("Select * from Users where id=" + userID)) {
                resultSet = statement.getResultSet();
                user = new User();
                while (resultSet.next()) {
                    user.setId(userID);
                    user.setName(resultSet.getString(Constants.USER_NAME));
                    user.setSurName(resultSet.getString(Constants.USER_SURNAME));
                    user.setLogin(resultSet.getString(Constants.USER_LOGIN));
                    user.setPassword(resultSet.getString(Constants.USER_PASSWORD));
                    user.setEmail(resultSet.getString(Constants.USER_EMAIL));
                    user.setPhoneNumber(resultSet.getString(Constants.USER_PHONE_NUMBER));
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return user;
    }

    public int findUserID(String login, String password) {
        int userID = 0;
        try {
            connection = ConnectDB.getConnection();
            cstmt = connection.prepareCall("{call dbo.checkUser(?, ?)}");
            cstmt.setString(Constants.USER_LOGIN, login);
            cstmt.setString(Constants.USER_PASSWORD, password);
            if (cstmt.execute()) {
                resultSet = cstmt.getResultSet();
                if (resultSet.next()) {
                    userID = Integer.parseInt(resultSet.getString(Constants.USER_ID));
                }
            } else {
                userID = -1;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cstmt.close();
                connection.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return userID;
    }

    public String getFIO(int userID) {
        String fio = "";
        try {
            connection = ConnectDB.getConnection();
            statement = connection.createStatement();
            if (statement.execute("Select name,surName from Users where id=" + userID)) {
                resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    fio = resultSet.getString(Constants.USER_NAME) + " " + resultSet.getString(Constants.USER_SURNAME);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return fio;
    }

    public String checkUser(String login, String password, String passwordConfirm, String email, String phoneNumber) {
        String res = "";
        try {
            connection = ConnectDB.getConnection();
            cstmt = connection.prepareCall("{call dbo.checkUserEntry(?, ?, ?, ?, ?)}");
            cstmt.setString(Constants.USER_LOGIN, login);
            cstmt.setString(Constants.USER_PASSWORD, password);
            cstmt.setString(Constants.USER_PASSWORD_CONFIRM, passwordConfirm);
            cstmt.setString(Constants.USER_EMAIL, email);
            cstmt.setString(Constants.USER_PHONE_NUMBER, phoneNumber);
            if (cstmt.execute()) {
                resultSet = cstmt.getResultSet();
                if (resultSet.next()) {
                    res = resultSet.getString("result");
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return res;
    }

    public ArrayList<User> getUsers(int numPage) {
        users = new ArrayList<User>();
        int countLines = 10;
        int startInd;
        if (numPage <= 1) {
            startInd = 0;
        } else {
            startInd = numPage * countLines - 9;
        }

        try {
            connection = ConnectDB.getConnection();
            statement = connection.createStatement();            
            if (statement.execute("Select top " + countLines + " * from Users where id>=" + startInd)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    user = new User();
                    user.setId(Integer.parseInt(resultSet.getString(Constants.USER_ID)));
                    user.setName(resultSet.getString(Constants.USER_NAME));
                    user.setSurName(resultSet.getString(Constants.USER_SURNAME));
                    user.setLogin(resultSet.getString(Constants.USER_LOGIN));
                    user.setPassword(resultSet.getString(Constants.USER_PASSWORD));
                    user.setEmail(resultSet.getString(Constants.USER_EMAIL));
                    user.setPhoneNumber(resultSet.getString(Constants.USER_PHONE_NUMBER));
                    users.add(user);
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

}

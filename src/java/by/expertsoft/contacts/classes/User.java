package by.expertsoft.contacts.classes;

public class User {

    int id;
    String name;
    String surName;
    String login;
    String password;
    String email;
    String phoneNumber;

    public User() {
    }

    public User(int id, String name, String surName, String login, String password, String email, String phoneNumber) {
        this.id=id;
        this.name = name;
        this.surName = surName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(User user) {
        this.name = user.getName();
        this.surName = user.getSurName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
    
    public User(String name, String surName, String login, String password, String email, String phoneNumber) {
        this.name = name;
        this.surName = surName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSurName() {
        return surName;
    }

    public String parseString(String str) {
        char oldc,newc = '\n';
        for (int i = 0; i < str.length(); i++) {
            oldc = str.charAt(i);
            if (oldc == '.') {
                str=str.replace(oldc, newc);
            }
        }
        return str;
    }

}

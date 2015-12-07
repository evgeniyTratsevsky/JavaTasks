package by.expertsoft.contacts.classes;

public class Contact {

    int id;
    String name;
    String surName;
    String login;
    String phoneNumber;

    public Contact() {
    }

    public Contact(int id, String name, String surName, String login, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.login = login;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getLogin() {
        return login;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

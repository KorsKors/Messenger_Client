package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by ������ on 20.01.2016.
 */
public class User {
    private Integer ID;
    private String login;
    private StringProperty flogin;
    private String password;
    private String IP;
    private String Command;


    public void setCommand(String Command) {
        this.Command = Command;
    }

    public String getCommand() {
        return Command;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getIP() {
        return IP;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public StringProperty firstNameProperty() {
        return flogin;
    }

    public User(String firstName) {
        this.flogin = new SimpleStringProperty (firstName);
    }
}
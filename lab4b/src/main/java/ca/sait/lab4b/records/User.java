package ca.sait.lab4b.records;

import java.io.Serializable;

/**
 *
 * @author Kevin
 */
public class User implements Serializable {
    private String userName;
    private String password;
    
    public User() {
    }
    
    public User(String username, String password) {
        
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}

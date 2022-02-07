package ca.sait.lab4b.services;

import ca.sait.lab4b.records.User;

/**
 *
 * @author Kevin
 */
public class AccountService {
    public User login(String userName, String password) {
        if (userName.equals("abe") && password.equals("password")) {
            return new User(userName, null);
        } else if (userName.equals("barb") && password.equals("password")) {
            return new User(userName, null);
        } else {
            return null;
        }
    }
}

package services;

import dataaccess.UserDB;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.UUID;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import models.User;

public class AccountService {
    
    public User login(String email, String password) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
public static void sendMail(String to, String subject, String body, boolean bodyIsHTML) throws MessagingException, NamingException {
        Context env = (Context) new InitialContext().lookup("java:comp/env");
        final String username = (String) env.lookup("webmail-username");
        final String password = (String) env.lookup("webmail-password");
        Properties props = new Properties();
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
       
        Session session;
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                }
        );
        
       
        
        session.setDebug(true);
        // create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html;charset=UTF-8");
            message.saveChanges();
        } else {
            message.setText(body);
        }
        // address the message
        Address fromAddress = new InternetAddress(username);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        // send the message
//        Transport transport = session.getTransport();
//        transport.connect(username, password);
//        transport.sendMessage(message, message.getAllRecipients());
//        transport.close();
           Transport.send(message);
//transport.connect (smtp_host, smtp_port, smtp_username, smtp_password);

    }
    
    
    
    
    public boolean changePassword(String uuid, String password) {
UserDB userDB = new UserDB();
try {
User user = userDB.getByUUID(uuid);
user.setPassword(password);
user.setResetPasswordUuid(null);
userDB.update(user);
return true;
} catch (Exception ex) {
return false;
}
}
    
    
    
    
}

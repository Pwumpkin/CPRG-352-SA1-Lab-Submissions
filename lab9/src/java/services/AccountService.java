package services;

import dataaccess.UserDB;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    
    public void resetPassword(String email, String path, String strUrl) throws NamingException, IOException {
        String uuid = UUID.randomUUID().toString();
        String link = strUrl + "?uuid=" + uuid;
        
        String emailBody = "";
        
        String sender = "cprg352programmer@gmail.com";
        String recipient = email;
        
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
       
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
         
            // Set Subject: header field
            message.setSubject("Resest NotesKeepr Password");

            // Send the actual HTML message, as big as you like
            //message.setContent("<h1>This is actual message</h1>", "text/html" );
            message.setContent(emailBody, "text/html" );
         
            
            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        } 
            
        
        
        
        
    }
}

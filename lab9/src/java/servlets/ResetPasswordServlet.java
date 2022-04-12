package servlets;

import dataaccess.UserDB;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;



public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        String queryString = request.getQueryString();
        Object trashUUID = request.getAttribute("trashUUID");
 
        if(queryString != null && queryString.startsWith("uuid=")){
            UserDB userdb = new UserDB();
            String strUUID = queryString.replace("uuid=", "");
            
            if(userdb.getByUUID(strUUID) != null) {
                request.setAttribute("trashUUID", strUUID);
                getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        }
        

        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //reset the password funcitonality
        AccountService as = new AccountService();
        
        String email = request.getParameter("theiremail"); 
        String trashUUID = request.getParameter("trashUUID");
        
        if(email != null && trashUUID == null){
            String uuid = UUID.randomUUID().toString();
            String strUrl = request.getRequestURL().toString();
            String link = strUrl + "?uuid=" + uuid;
            
            //store uuid in database
            UserDB usrDB = new UserDB();
            User usr = usrDB.get(email);
            
            
            try {
            usr.setResetPasswordUuid(uuid);
            usrDB.update(usr);
            
            URL bodyTemplateURL = this.getClass().getResource("/services/resetpassword.html");
            
            URLConnection connection = bodyTemplateURL.openConnection();
            BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    connection.getInputStream()));

            StringBuilder resp = new StringBuilder();
     
    
            for(String line = ""; line != null;line=in.readLine()){
                if(line.indexOf("firstname")!= -1){
                    line = line.replace("firstname", usr.getFirstName());
                }
                if (line.indexOf("lastname")!= -1) {
                    line = line.replace("lastname", usr.getLastName());  
                } 
                if (line.indexOf("linkAnchor")!= -1) {
                    line = line.replace("linkAnchor", link);  
                } 
                if (line.indexOf("linktext")!= -1) {
                    line = line.replace("linktext", link);
                }
                resp.append(line);
                resp.append("\n");
            }
            
            
            String emailBody = resp.toString();
            in.close();
          
            
            try {
                AccountService.sendMail(usr.getEmail(), "Password Reset", emailBody, true);
            } catch (MessagingException ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            } catch (Exception ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
           getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response); 
            
        } else if(trashUUID != null) {
            String newPassword = request.getParameter("newpass");
            if(newPassword != null){
                UserDB userdb = new UserDB();
                User user = userdb.getByUUID(trashUUID);
                user.setPassword(newPassword);
                user.setResetPasswordUuid(null);
                try {
                    userdb.update(user);
                } catch (Exception ex) {
                    Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            } 
        }
        
        

        
        
    }
}




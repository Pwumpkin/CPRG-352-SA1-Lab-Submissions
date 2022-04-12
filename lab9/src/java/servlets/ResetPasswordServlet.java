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
        //redirect to the Reset jsp
        String url = request.getRequestURL().toString();
        HttpSession session = request.getSession();
        
        String seshUUID = "";
        try{
        seshUUID = session.getAttribute("uuid").toString();
        } catch (NullPointerException dne){
            seshUUID = "";
        }
        
        //uuid is filled in but new password isn't filled in
        if(seshUUID != null && !(seshUUID.equals("")) && !(request.getParameter("newPass").length()>1) ) {
          getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
          return;
      
        //uuid is filled in && new password filled in
        } else if (seshUUID != null && !(seshUUID.equals("")) && (request.getParameter("newPass").length()>1)) {
            AccountService as = new AccountService();
            as.changePassword(seshUUID, seshUUID);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //reset the password funcitonality
        AccountService as = new AccountService();
        
        String email = request.getParameter("theiremail"); 
        String uuid = UUID.randomUUID().toString();
        String strUri = request.getRequestURI();
        strUri = request.getRequestURL() + request.getRequestURI();
        String link = strUri + "?uuid=" + uuid;
            
            //store uuid in database
        UserDB usrDB = new UserDB();
        User usr = usrDB.get(email);
       
        if(usr==null){
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        }
        
        
        try {
            usr.setResetPasswordUuid(uuid);
            usrDB.update(usr);
        } catch (Exception ex) {
            Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        
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
        
            
        request.setAttribute("message","Recovery Email Successfully Sent!!!");
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }
}




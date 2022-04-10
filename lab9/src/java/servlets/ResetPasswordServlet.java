package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        String email = request.getParameter("their-email");
        String strUri = request.getRequestURI().toString();
        
        try {
            as.resetPassword(email, "asdf", strUri);
            request.setAttribute("message","Recovery Email Successfully Sent!!!");
           
        } catch (NamingException ex) {
            Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }


}

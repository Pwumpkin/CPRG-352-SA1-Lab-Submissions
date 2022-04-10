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
        session.setAttribute("url", url);
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
            System.out.println("Email sent successfully.");
        } catch (NamingException ex) {
            Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}

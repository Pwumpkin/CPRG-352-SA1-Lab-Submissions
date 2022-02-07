package ca.sait.lab4b.servlets;

import ca.sait.lab4b.records.User;
import ca.sait.lab4b.services.AccountService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kevin
 */
public class LoginServlet extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        if(session.getAttribute("username") != null) {
            String query = request.getQueryString();
            
        
            if (query != null && query.contains("logout")) {
                session.invalidate();
                
                request.setAttribute("message", "You are logged out.");
            } else {
                response.sendRedirect("home");
                return;
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        

        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
  
        
        if(userName.isEmpty() || userName == null || password.isEmpty() || password == null) {
            request.setAttribute("message", "Missing 1 or more login fields.");
        } else {
            AccountService account = new AccountService();
            
            User user = account.login(userName, password);
            
            if (user != null) {
                request.getSession().setAttribute("username", userName);
                
                response.sendRedirect("home");
                return;
            } else {
                request.setAttribute("username", userName);
                request.setAttribute("message", "Sorry, the credentials that you entered don't match any of our records! Please try again.");
                
                
            }
        }
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);      
    }

}

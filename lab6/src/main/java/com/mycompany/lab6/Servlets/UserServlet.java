
package com.mycompany.lab6.Servlets;


import com.mycompany.lab6.models.Role;
import com.mycompany.lab6.models.User;
import com.mycompany.lab6.services.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kevin
 */
public class UserServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        UserService service = new UserService();
        
        try {
            List<User> users = service.getAll();
            
            request.setAttribute("users", users);
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            
        } catch (Exception ex) {
            
        }
        
        
        

        

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
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");
        
        if (action != null && action.equals("add")) {
            
            
            String email = request.getParameter("new-user-email");
            //user active by default
            String firstName = request.getParameter("new-user-firstname");
            String lastName = request.getParameter("new-user-lastname");
            String password = request.getParameter("new-user-password");
            
            
            int id = Integer.parseInt(request.getParameter("new-user-id"));
            String roleName = request.getParameter("new-user-email");
            Role role = new Role(id,roleName);

     
            User user = new User(email, true , firstName, lastName, password, role);
            
            ArrayList<User> users = (ArrayList<User>)session.getAttribute("users");
            
            users.add(user);
            
            session.setAttribute("users", users);
            
        } else if (action != null && action.equals("delete")) {    
            ArrayList<User> users = (ArrayList<User>)session.getAttribute("users");
            String email = request.getParameter("user.email");
            String firstName = request.getParameter("user.firstName");
            String lastName = request.getParameter("user.lastName");
            User user = null;
            for(User u : users) {
                if ( email.equals(u.getEmail()) &&  firstName.equals(u.getFirstName()) && lastName.equals( u.getLastName())) {
                    user = u;
                }
            }
            users.remove(user);
            session.setAttribute("items", users);
            
        } else {
            
            ArrayList<User> users = (ArrayList<User>)session.getAttribute("users");
            String email = request.getParameter("user.email");
            String firstName = request.getParameter("user.firstName");
            String lastName = request.getParameter("user.lastName");
            User user = null;
            for(User u : users) {
                if ( email.equals(u.getEmail()) &&  firstName.equals(u.getFirstName()) && lastName.equals( u.getLastName())) {
                    user = u;
                }
            }
            users.remove(user);
            
            String newEmail = request.getParameter(lastName);
            boolean newActive = request.getParameter(lastName);
            String newFirstName = request.getParameter(lastName);
            String newLastName = request.getParameter(lastName);
            String newPassword = request.getParameter(lastName);
         
            int id = Integer.parseInt(request.getParameter("edit-user-id"));
            String roleName = request.getParameter("edit-user-email");
            Role role = new Role(id,roleName);
            
            user = new User(newEmail,newActive,newFirstName,newLastName,newPassword);
            
            users.add(user);
            session.setAttribute("items", users);
            
            
            
            
            
            
            String name = request.getParameter("name");
            
            ArrayList<String> items = new ArrayList<>();
            
            session.setAttribute("name", name);
            session.setAttribute("items", items);
        
            
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

  

}

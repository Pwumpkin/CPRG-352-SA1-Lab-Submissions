
package ca.sait.calculators.servlets;

import java.lang.Double;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Kevin
 */
@WebServlet(name = "ArithmeticCalculatorServlet", urlPatterns = {"/ArithmeticCalculatorServlet"})
public class ArithmeticCalculatorServlet extends HttpServlet {
      
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
        
        request.setAttribute("output", "---");
        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp").forward(request, response);

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
        
        try {
            int nFirst = Integer.parseInt(request.getParameter("operand1"));
            int nSecond = Integer.parseInt(request.getParameter("operand2"));
            String operation = request.getParameter("operation");
            
            String result = "";
            try{
                switch(operation) {
                    case "add":
                        result = Integer.toString(nFirst + nSecond);
                        break;
                    case "subtract":
                        result = Integer.toString(nFirst - nSecond);
                        break;
                    case "multiply":
                        result = Integer.toString (nFirst * nSecond);
                        break;
                    case "divide":
                        result = Integer.toString(nFirst / nSecond);
                        break;
                    default:
                        result = "---";
                        break;
                }
                request.setAttribute(operation, this);
                request.setAttribute("operand1", nFirst);
                request.setAttribute("operand2", nSecond);
                request.setAttribute("output", result);
            } catch(Exception ex) {
                
            }
            
        } catch (Exception ex) {
            if(!request.getParameter("operand1").equals("")) {    
                request.setAttribute("operand1", request.getParameter("operand1"));
            }
            if(!request.getParameter("operand2").equals("")) {
                request.setAttribute("operand2", request.getParameter("operand2"));
            }
            request.setAttribute("output", "invalid");
        }    

           

        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp").forward(request, response);

    }

}

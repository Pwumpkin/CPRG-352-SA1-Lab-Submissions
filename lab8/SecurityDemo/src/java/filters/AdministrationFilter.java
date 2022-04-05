package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
    	
    	HttpServletRequest validationRequest = (HttpServletRequest) request;
    	HttpServletResponse validationResponse = (HttpServletResponse) response;
    	HttpSession session = validationRequest.getSession();
    	
    	//initializes to 0
    	int userRole = 0;
    	userRole = (int)session.getAttribute("roleId");
    	
    	//initializes to null
    	String userEmail = null;
    	userEmail = (String)session.getAttribute("email");
    	
    	//role and email contain valid values
    	if(userEmail != null && !userEmail.isEmpty() && !userEmail.isBlank() && userRole != 0) {
    		//is administrator
    		if(userRole == 1) {
    			chain.doFilter(request,response);
    		//valid but isn't administrator
    		} else {
    			validationResponse.sendRedirect("/notes");
    		}
    	} else {
    		validationResponse.sendRedirect("/login");
    	}
    	
   
    	
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {
       
    }
}

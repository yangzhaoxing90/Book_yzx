package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Book.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		User user = (User)session.getAttribute("user");
		
		String path = req.getRequestURI();
		if(path.indexOf("/login.jsp")>-1) {
			chain.doFilter(request, response);
			return;
		}
		if(path.indexOf("/Login")>-1) {
			chain.doFilter(request, response);
			return;
		}
		if(path.indexOf("/style.css")>-1) {
			chain.doFilter(request, response);
			return;
		}
		if(path.indexOf("/register.jsp")>-1) {
			chain.doFilter(request, response);
			return;
		}
		if(path.indexOf("/images")>-1) {
			chain.doFilter(request, response);
			return;
		}
		if(path.indexOf("/register")>-1) {
			chain.doFilter(request, response);
			return;
		}
		if(user!=null) {
			chain.doFilter(request, response);
		}else {
			res.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

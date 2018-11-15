package servletpac;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Book.Literature;
import Book.User;

import service.Literatureservice;
import service.Userservice;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Userservice userservice = new Userservice(); 
		ArrayList<User> userList = (ArrayList<User>) userservice.findAllList();
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		
		boolean flag = false;
	
		for(User user: userList) {
			if(userName.equals(user.getUserName())&&passWord.equals(user.getPassWord())) {
				session.setAttribute("user", user);
				flag = true;
				break;
			}
		}
		
		if(flag) {
//			response.sendRedirect("index.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}else {
			boolean flag1 = false;
			for(User user: userList) {
				if(userName.equals(user.getUserName())) {
					flag1 = true;
					break;
				}		
			}
			if(flag1) {
				request.setAttribute("error1", "密码错误");
//				request.setAttribute("us", userName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("error", "当前用户不存在！");
//				request.setAttribute("us", userName);
//				response.sendRedirect("index.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doPost(request, response);
	}
}

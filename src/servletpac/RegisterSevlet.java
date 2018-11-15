package servletpac;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Book.User;
import service.Userservice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class RegisterSevlet
 */
@WebServlet("/RegisterSevlet")
public class RegisterSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Userservice userservice = new Userservice(); 
		ArrayList<User> userList = (ArrayList<User>) userservice.findAllList();
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String passWord2 = request.getParameter("rePassWord");
		String email = request.getParameter("email");
		
		boolean flag = false;
		
		for(User user: userList) {
			if(userName.equals(user.getUserName())) {
//				session.setAttribute("user", user);
				flag = true;
				break;
			}
		}
		
		if(flag) {
			request.setAttribute("problem1", "当前用户已存在！");
			session.setAttribute("uns", userName);
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request, response);
		}else {
			if(userName.length() < 6) {
				request.setAttribute("problem1", "用户名过短，至少6位！");
				session.setAttribute("uns", userName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
				dispatcher.forward(request, response);
			}else if(passWord.length() < 6) {
				request.setAttribute("problem2", "密码过于简单，至少6位！");
				session.setAttribute("uns", userName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
				dispatcher.forward(request, response);
			}else if(!passWord.equals(passWord2)) {
				request.setAttribute("problem3", "两次密码输入不一致！");
				session.setAttribute("uns", userName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
				dispatcher.forward(request, response);
			}else {
				Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
				Matcher m = p.matcher(email);
				boolean b = m.matches();
				if(!b) {
					request.setAttribute("problem4", "邮箱输入不正确！");
					session.setAttribute("em", email);
					RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
					dispatcher.forward(request, response);
				}else {
					User userr = new User();
					userr.setUserName(userName);
					userr.setEmailAdress(email);
					userr.setPassWord(passWord);
					userList.add(userr);
					userservice.addUser(userr);
//					context.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("register_success.jsp");
					dispatcher.forward(request, response);
				}
			}	
		}
	}

}

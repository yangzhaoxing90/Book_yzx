package servletpac;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Book.Literature;
import service.Literatureservice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		ServletConfig config = this.getServletConfig();
		ServletContext context = config.getServletContext();
		Literatureservice literatureservice = new Literatureservice(); 
		ArrayList<Literature> literatureList = (ArrayList<Literature>)literatureservice.findAllList();
		ArrayList<Literature> litList = new ArrayList<Literature>();
		
		String keywords = request.getParameter("keywords");
//		System.out.println(keywords);
		String keyword = "";
		Pattern p = Pattern.compile(".*"+keywords+".*");
		for(Literature litra  : literatureList) {
			Matcher m = p.matcher(litra.getName());
			boolean b = m.matches();
			if(b) {
				keyword = litra.getName();
				Literature literature = literatureservice.findLiterature(keyword);
				if(literature != null) {
					litList.add(literature);
				}
			}
		}
		context.setAttribute("litList", litList);
		response.sendRedirect("search_result.jsp");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

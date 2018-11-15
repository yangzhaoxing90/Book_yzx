package servletpac;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Book.Literature;
import Book.User;
import service.Literatureservice;



/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletConfig config = this.getServletConfig();
		ServletContext context = config.getServletContext();
		
		Literature newbook = new Literature();
		Literatureservice literatureservice = new Literatureservice();
		ArrayList<Literature> literatureList = (ArrayList<Literature>) literatureservice.findAllList();
		
		// 1. 创建工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2. 创建FileUpload对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 3. 判断是否是上传表单
		boolean b = upload.isMultipartContent(request);
		
		if(!b) {

		// 不是文件上传
		request.getRequestDispatcher("/addBook.jsp").forward(request, response);
		    return;
		}
		// 是文件上传表单
		// 4. 解析request，获得FileItem项
		List<FileItem> fileitems = null;
		try {
			fileitems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 5. 遍历集合
		for(FileItem item : fileitems) {
		    // 判断是不是普通字段
		if(item.isFormField()) {
			
		    String name = item.getFieldName();
		    String value = item.getString(); 
		    // 手工的转换了
		    value = new String(value.getBytes("iso-8859-1"),"utf-8");
//		    System.out.println(name + "=" + value);
			switch (name) {
			case "bookName":
				newbook.setName(value);
				break;
			case "bookCount":
				newbook.setNumber(Integer.parseInt(value));
				break;
			case "bookPrice":
				newbook.setPrice(Double.parseDouble(value));
				break;
			default :
				break;
			}

		} else {

		    // 文件上传字段
		    // 获得文件名
		    String filename = item.getName();
		    if(filename == "") {
		    	filename = (String)context.getAttribute("litImgpath");
		    }
//		    System.out.println(filename);
		    filename = filename.substring(filename.lastIndexOf("/")+1);
		    // 创建文件
		    String dir = "E:\\eclipse\\workspace\\Book_yzx\\WebContent\\upload\\" ;
		    String fileAdress = dir + filename;
		    newbook.setImgpath("upload/"+filename);
//		    System.out.println("upload/"+filename);
		    File file = new File(fileAdress);
		    file.createNewFile();
		    // 获得流，读取数据写入文件

		    InputStream in = item.getInputStream();
		    FileOutputStream fos = new FileOutputStream(file);
		    
		    int len;
		    byte[] buffer = new byte[1024];

		    while((len=in.read(buffer))>0)

		       fos.write(buffer,0,len);
		    
		    fos.close();
		    in.close();
		    item.delete();    // 删除临时文件
			}
		}
		
		Iterator<Literature> Iit = literatureList.iterator();
		while(Iit.hasNext()){
			Literature litera = Iit.next();
			if(litera.getName().equals(newbook.getName())){
				newbook.setNumber(litera.getNumber() + newbook.getNumber());
				Iit.remove();
				literatureservice.deleteLiterature(litera.getName());
			}
		}

		literatureservice.addLiterature(newbook);
		response.sendRedirect("index.jsp");
	}
}

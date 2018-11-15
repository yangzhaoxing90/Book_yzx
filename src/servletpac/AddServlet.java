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
		
		// 1. ����������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2. ����FileUpload����
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 3. �ж��Ƿ����ϴ���
		boolean b = upload.isMultipartContent(request);
		
		if(!b) {

		// �����ļ��ϴ�
		request.getRequestDispatcher("/addBook.jsp").forward(request, response);
		    return;
		}
		// ���ļ��ϴ���
		// 4. ����request�����FileItem��
		List<FileItem> fileitems = null;
		try {
			fileitems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 5. ��������
		for(FileItem item : fileitems) {
		    // �ж��ǲ�����ͨ�ֶ�
		if(item.isFormField()) {
			
		    String name = item.getFieldName();
		    String value = item.getString(); 
		    // �ֹ���ת����
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

		    // �ļ��ϴ��ֶ�
		    // ����ļ���
		    String filename = item.getName();
		    if(filename == "") {
		    	filename = (String)context.getAttribute("litImgpath");
		    }
//		    System.out.println(filename);
		    filename = filename.substring(filename.lastIndexOf("/")+1);
		    // �����ļ�
		    String dir = "E:\\eclipse\\workspace\\Book_yzx\\WebContent\\upload\\" ;
		    String fileAdress = dir + filename;
		    newbook.setImgpath("upload/"+filename);
//		    System.out.println("upload/"+filename);
		    File file = new File(fileAdress);
		    file.createNewFile();
		    // ���������ȡ����д���ļ�

		    InputStream in = item.getInputStream();
		    FileOutputStream fos = new FileOutputStream(file);
		    
		    int len;
		    byte[] buffer = new byte[1024];

		    while((len=in.read(buffer))>0)

		       fos.write(buffer,0,len);
		    
		    fos.close();
		    in.close();
		    item.delete();    // ɾ����ʱ�ļ�
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

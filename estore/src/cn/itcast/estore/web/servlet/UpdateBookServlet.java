package cn.itcast.estore.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.estore.service.BookService;
import cn.itcast.estore.vo.Book;
/**
 * 修改图书的Servlet
 * @author 
 *
 */
public class UpdateBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 修改图书的图片:(文件上传) 删除原有图片.
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
		try {
			List<FileItem> list = fileUpload.parseRequest(request);
			Map<String,String> map = new HashMap<String,String>();
			String fileName = null;
			String path = null;
			BookService bookService = new BookService();
			for (FileItem fileItem : list) {
				if(fileItem.isFormField()){
					// 普通项:
					String name = fileItem.getFieldName();
					String value = fileItem.getString("UTF-8");
					map.put(name, value);
					System.out.println(map);
				}else{
					// 文件项:
					fileName = fileItem.getName();
					if(fileName != null && !"".equals(fileName)){
						// 获得路径 ：
						path = this.getServletContext().getRealPath("/book_img");
						// 说明有上传图片:
						// 上传新图片:
						InputStream is = fileItem.getInputStream();
						OutputStream os = new FileOutputStream(path+"\\"+fileName);
						byte[] b = new byte[1024];
						int len  = 0;
						while((len = is.read(b))!=-1){
							os.write(b, 0, len);
						}
						is.close();
						os.close();
					}
				}
			}
			// 删除原有图片:
			String oldPath = map.get("image");
			System.out.println(oldPath);
			String oldFileName = oldPath.substring(oldPath.lastIndexOf("/")+1);
			System.out.println(oldFileName);
			File file = new File(path+"\\"+oldFileName);
			file.delete();
			// 将map中数据封装到图书对象中:
			Book book = new Book();
			BeanUtils.populate(book, map);
			book.setImage("book_img/"+fileName);
			bookService.update(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/adminBookServlet?method=findAll").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

package cn.itcast.estore.web.servlet;

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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//import com.sun.org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.estore.service.BookService;
import cn.itcast.estore.utils.UUIDUitls;
import cn.itcast.estore.vo.Book;

/**
 * 添加图书的Servlet
 * 
 * @author jiangtao
 * 
 */
public class AddBookServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 完成文件上传:
		// 创建磁盘文件项工厂:
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 创建核心解析类:
		ServletFileUpload fileUpload = new ServletFileUpload(
				diskFileItemFactory);
		// 创建一个Map集合用于存放普通项的数据:
		Map<String, String> map = new HashMap<String, String>();
		// 解析requst
		try {
			List<FileItem> list = fileUpload.parseRequest(req);
			String fileName = null;
			for (FileItem fileItem : list) {
				// 判断文件项是否是普通项:
				if (fileItem.isFormField()) {
					// 普通项
					String name = fileItem.getFieldName(); // bname price
					String value = fileItem.getString("UTF-8");// 这辈子获得 21.2
					map.put(name, value);
					// System.out.println(map);
				} else {
					// 文件上传项:
					// 获得文件名:
					fileName = fileItem.getName();
					// 获得文件内容:
					InputStream is = fileItem.getInputStream();
					// 获得上传磁盘绝对路径:
					String path = this.getServletContext().getRealPath(
							"/book_img");
					OutputStream os = new FileOutputStream(path + "\\"
							+ fileName);
					byte[] b = new byte[1024];
					int len = 0;
					while ((len = is.read(b)) != -1) {
						os.write(b, 0, len);
					}
					is.close();
					os.close();
				}
			}
			// 插入数据库
			Book book = new Book();
			BeanUtils.populate(book, map);
			book.setBid(UUIDUitls.getUUID());
			book.setImage("book_img/"+fileName);
			book.setIsdel(0);
			// 数据库记录插入:
			BookService bookService = new BookService();
			bookService.save(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/adminBookServlet?method=findAll").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}

package cn.itcast.estore.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.service.BookService;
import cn.itcast.estore.utils.BaseServlet;
import cn.itcast.estore.vo.Book;
import cn.itcast.estore.vo.PageBean;
/**
 * 图书模块的Servlet
 * @author 
 *
 */
public class BookServlet extends BaseServlet {

	/**
	 * 根据图书ID查询图书的方法:
	 */
	public String findByBid(HttpServletRequest req,HttpServletResponse resp){
		/**
		 * 接收bid
		 * 调用业务层方法
		 * 页面跳转
		 */
		String bid = req.getParameter("bid");
		BookService bookService = new BookService();
		Book book = bookService.findByBid(bid);
		req.setAttribute("book", book);
		return "/jsps/book/desc.jsp";
	}
	
	/**
	 * 根据分类ID查询所有图书的方法:
	 * @param req
	 * @param resp
	 * @return
	 */
	public String findByCid(HttpServletRequest req,HttpServletResponse resp){
		/**
		 * 接收cid
		 * 调用业务层
		 * 页面跳转
		 */
		String cid = req.getParameter("cid");
		BookService bookService = new BookService();
		List<Book> list = bookService.findByCid(cid);
		req.setAttribute("list", list);
		return "/jsps/book/list.jsp";
	}
	
	/**
	 * 查询所有图书带分页的方法：
	 * @param req
	 * @param resp
	 * @return
	 */
	public String findByPage(HttpServletRequest req,HttpServletResponse resp){
		
		/**
		 * 1.接收参数
		 * 2.调用业务层代码
		 * 3.页面跳转
		 */
		// 接收参数
		int currPage = Integer.parseInt(req.getParameter("currPage"));
		// 调用业务层的代码
		BookService bookService = new BookService();
		PageBean<Book> pageBean = bookService.findByPage(currPage);
		// 保存到域中页面跳转:
		req.setAttribute("pageBean", pageBean);
		return "/jsps/book/book_list.jsp";
	}
}

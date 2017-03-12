package cn.itcast.estore.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.service.BookService;
import cn.itcast.estore.service.CategoryService;
import cn.itcast.estore.utils.BaseServlet;
import cn.itcast.estore.vo.Book;
import cn.itcast.estore.vo.Category;

public class AdminBookServlet extends BaseServlet {

	/**
	 * 查询所有图书的执行的方法:
	 */
	public String findAll(HttpServletRequest req,HttpServletResponse resp){
		BookService bookService = new BookService();
		List<Book> list = bookService.findAll();
		req.setAttribute("list", list);
		return "/adminjsps/admin/book/list.jsp";
	}
	
	/**
	 * 跳转到添加页面的方法:
	 */
	public String saveUI(HttpServletRequest req,HttpServletResponse resp){
		// 查询所有分类:
		CategoryService categoryService = new CategoryService();
		List<Category> list = categoryService.findAll();
		req.setAttribute("list", list);
		return "/adminjsps/admin/book/add.jsp";
	}
	
	/**
	 * 跳转到修改页面的方法;
	 */
	public String updateUI(HttpServletRequest req,HttpServletResponse resp){
		// 接收图书的ID
		String bid = req.getParameter("bid");
		// 根据ID查询图书 ：
		BookService bookService = new BookService();
		Book book = bookService.findByBid(bid);
		// 查询所有分类
		CategoryService categoryService = new CategoryService();
		List<Category> list = categoryService.findAll();
		req.setAttribute("book", book);
		req.setAttribute("list", list);
		return "/adminjsps/admin/book/desc.jsp";
	}
	
	/**
	 * 图书下架的方法:
	 */
	public String pushDown(HttpServletRequest req,HttpServletResponse resp){
		String bid = req.getParameter("bid");
		BookService bookService = new BookService();
		Book book = bookService.findByBid(bid);
		book.setIsdel(1);
		bookService.update(book);
		return findAll(req,resp);
	}
	
	/**
	 * 跳转到上架页面:
	 */
	public String pushUpUI(HttpServletRequest req,HttpServletResponse resp){
		BookService bookService = new BookService();
		List<Book> list = bookService.findByIsDel();
		req.setAttribute("list", list);
		return "/adminjsps/admin/book/book_list.jsp";
	}
	
	/**
	 * 上架图图的方法
	 */
	public String pushUp(HttpServletRequest req,HttpServletResponse resp){
		// 接收bid
		String bid = req.getParameter("bid");
		BookService bookService = new BookService();
		Book book = bookService.findByBid(bid);
		book.setIsdel(0);
		bookService.update(book);
		return findAll(req,resp);
	}
}

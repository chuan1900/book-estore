package cn.itcast.estore.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.service.BookService;
import cn.itcast.estore.utils.BaseServlet;
import cn.itcast.estore.vo.Cart;
import cn.itcast.estore.vo.CartItem;
/**
 * 购物模块的Servlet
 * @author 
 *
 */
public class CartServlet extends BaseServlet {

	/**
	 * 获得购物车的方法
	 */
	public Cart getCart(HttpServletRequest req){
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			req.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	/**
	 * 将购物项添加到购物车的方法:
	 * @param req
	 * @param resp
	 * @return
	 */
	public String addCart(HttpServletRequest req,HttpServletResponse resp){
		/**
		 * 1.接收参数:
		 * 2.调用Cart中方法.
		 * 3.页面跳转:
		 */
		// 1.接收参数:
		String bid = req.getParameter("bid");
		int count = Integer.parseInt(req.getParameter("count"));
		// 2.调用Cart中addCart方法.
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		BookService bookService = new BookService();
		cartItem.setBook(bookService.findByBid(bid));
		Cart cart = getCart(req);
		cart.addCart(cartItem);
				
		return "/jsps/cart/list.jsp";
	}
	
	/**
	 * 清空购物车的方法
	 * @param req
	 * @param resp
	 * @return
	 */
	public String clearCart(HttpServletRequest req,HttpServletResponse resp){
		// 1.获得Cart对象.
		Cart cart = getCart(req);
		// 2.调用Cart中的clearCart方法
		cart.clearCart();
		// 3.页面跳转
		return "/jsps/cart/list.jsp";
	}
	
	/**
	 * 移除购物项方法
	 * @param req
	 * @param resp
	 * @return
	 */
	public String removeCart(HttpServletRequest req,HttpServletResponse resp){
		// 1.接收bid
		String bid = req.getParameter("bid");
		// 2.调用Cart中的removeCart的方法.
		Cart cart = getCart(req);
		cart.removeCart(bid);
		// 3.页面跳转
		return "/jsps/cart/list.jsp";
	}
}

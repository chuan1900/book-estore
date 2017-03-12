package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.service.OrderService;
import cn.itcast.estore.utils.BaseServlet;
import cn.itcast.estore.utils.PaymentUtil;
import cn.itcast.estore.utils.UUIDUitls;
import cn.itcast.estore.vo.Cart;
import cn.itcast.estore.vo.CartItem;
import cn.itcast.estore.vo.Order;
import cn.itcast.estore.vo.OrderItem;
import cn.itcast.estore.vo.User;

public class OrderServlet extends BaseServlet {
	/**
	 * 付款成功后的执行的方法
	 */
	public String callBack(HttpServletRequest req, HttpServletResponse resp){
		String oid = req.getParameter("r6_Order");
		String r3_Amt = req.getParameter("r3_Amt");
		
		// 修改订单状态:
		OrderService orderService = new OrderService();
		Order order = orderService.findByOid(oid);
		order.setState(2);
		orderService.update(order);
		
		// 显示信息:
		req.setAttribute("msg", "亲!付款成功!订单编号:"+oid+" 付款金额:"+r3_Amt);
		return "/jsps/msg.jsp";
	}
	
	/**
	 * 为订单付款的方法
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException 
	 */
	public String payOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 1.接收参数:
		String oid = req.getParameter("oid");
		String address = req.getParameter("address");
		String pd_FrpId = req.getParameter("pd_FrpId");
		// 2.修改订单的地址:
		OrderService orderService = new OrderService();
		Order order = orderService.findByOid(oid);
		order.setAddress(address);
		orderService.update(order);
		// 3.拼装易宝的数据:
		String p0_Cmd = "Buy";
		String p1_MerId = "10001126856";
		String p2_Order = oid;
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://192.168.14.66:8080/estore/orderServlet?method=callBack";
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
		
		// 向易宝重定向.
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		resp.sendRedirect(sb.toString());
		
		return null;
	}

	/**
	 * 根据订单ID查询订单的方法:
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	public String findByOid(HttpServletRequest req, HttpServletResponse resp) {
		// 1.接收订单ID
		String oid = req.getParameter("oid");
		// 2.调用业务层代码
		OrderService orderService = new OrderService();
		Order order = orderService.findByOid(oid);
		// 3.页面跳转
		req.setAttribute("order", order);
		return "/jsps/order/desc.jsp";
	}

	/**
	 * 根据用户的ID查询订单:我的订单
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	public String findByUid(HttpServletRequest req, HttpServletResponse resp) {
		// 获得用户的信息从session获得
		User user = (User) req.getSession().getAttribute("existUser");
		// 调用业务层代码
		OrderService orderService = new OrderService();
		List<Order> list = orderService.findByUid(user.getUid());
		// 页面跳转
		req.setAttribute("list", list);
		return "/jsps/order/list.jsp";
	}

	/**
	 * 生成定的执行的方法
	 */
	public String saveOrder(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * 1.封装Order对象 2.调用业务层代码. 3.页面跳转
		 */
		// 封装Order对象.
		Order order = new Order();
		order.setOid(UUIDUitls.getUUID());
		order.setOrdertime(new Date());
		order.setState(1); // 1:未付款.
		order.setAddress(null);
		// 获得购物车:
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if (cart == null) {
			req.setAttribute("msg", "您还没有购物！");
			return "/jsps/msg.jsp";
		}
		order.setTotal(cart.getTotal()); // 订单的总计 从购物中获得.
		// 获得登录的用户的信息
		User user = (User) req.getSession().getAttribute("existUser");
		if (user == null) {
			req.setAttribute("msg", "您还没有登录！不能生成订单!");
			return "/jsps/user/login.jsp";
		}
		order.setUser(user);

		// 获得每个购物项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setBook(cartItem.getBook());
			orderItem.setItemid(UUIDUitls.getUUID());
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}

		// 插入到数据库:
		OrderService orderService = new OrderService();
		orderService.save(order);

		// 清空购物车:
		cart.clearCart();

		// 吧订单信息存入到域中.
		req.setAttribute("order", order);
		return "/jsps/order/desc.jsp";
	}
	
	/**
	 * 修改订单状态：确认收货链接
	 */
	public String update(HttpServletRequest req, HttpServletResponse resp){
		// 接收oid:
		String oid = req.getParameter("oid");
		OrderService orderService= new OrderService();
		Order order= orderService.findByOid(oid);
		order.setState(4);
		orderService.update(order);
		return findByUid(req,resp);
	}
}

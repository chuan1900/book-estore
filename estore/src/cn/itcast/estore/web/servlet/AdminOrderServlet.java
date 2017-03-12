package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import cn.itcast.estore.service.OrderService;
import cn.itcast.estore.utils.BaseServlet;
import cn.itcast.estore.vo.Order;
import cn.itcast.estore.vo.OrderItem;
/**
 * 后台订单管理的Servlet 
 * @author
 *
 */
public class AdminOrderServlet extends BaseServlet  {
	
	/**
	 * 后台的订单的查询
	 * @param req
	 * @param resp
	 * @return
	 */
	public String findAll(HttpServletRequest req,HttpServletResponse resp){
		String value = req.getParameter("state");
		OrderService orderService = new OrderService();
		List<Order> list = null;
		if(value  == null){
			// 查询所有
			list = orderService.findAll();
		}else{
			// 按状态查询
			int state = Integer.parseInt(value);
			list = orderService.findByState(state);
		}
		req.setAttribute("list", list);
		return "/adminjsps/admin/order/list.jsp";
	}
	
	public String update(HttpServletRequest req,HttpServletResponse resp){
		String oid = req.getParameter("oid");
		OrderService orderService = new OrderService();
		Order order = orderService.findByOid(oid);
		order.setState(3);
		orderService.update(order);
		return findAll(req,resp);
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public String showDetail(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// 接收oid:
		String oid = req.getParameter("oid");
		OrderService orderService = new OrderService();
		List<OrderItem> list = orderService.findOrderItem(oid);
		// 将list集合转换成JSON:JSONLib.
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"itemid","order"});
		JSONArray json = JSONArray.fromObject(list,config);
		System.out.println(json.toString());
		resp.getWriter().println(json.toString());
		return null;
	}
}

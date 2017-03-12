package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.estore.service.UserService;
import cn.itcast.estore.utils.BaseServlet;
import cn.itcast.estore.vo.User;
/**
 * 前台用户模块的Servlet
 * @author 
 *
 */
public class UserServlet extends BaseServlet {
	
	/**
	 * 用户退出的方法
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException 
	 */
	public String logout(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		/**
		 * 1.销毁session
		 * 2.页面跳转
		 */
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath()+"/jsps/main.jsp");
		return null;
	}
	/**
	 * 用户登录的执行的方法
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException 
	 */
	public String login(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		/**
		 * 1.接收数据
		 * 2.封装数据
		 * 3.调用Service
		 * 4.页面跳转
		 */
		// 接收数据
		Map<String,String[]> map = req.getParameterMap();
		// 封装数据
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			// 调用Service
			UserService userService = new UserService();
			User existUser = userService.login(user);
			// 判断:
			if(existUser == null){
				// 没有查询到用户
				req.setAttribute("msg", "Username or Password is wrong!");
				return "/jsps/user/login.jsp";
			}else{
				// 查询
				req.getSession().setAttribute("existUser", existUser);
				resp.sendRedirect(req.getContextPath()+"/jsps/main.jsp");
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 用户激活的方法:
	 * @param req
	 * @param resp
	 * @return
	 */
	public String active(HttpServletRequest req,HttpServletResponse resp){
		/**
		 * 1.接收激活码
		 * 2.根据激活码查询用户
		 * 3.能够查询到该用户,修改用户的状态.
		 * 4.页面跳转.
		 */
		// 1.接收激活码.
		String code = req.getParameter("code");
		// 2.查询数据
		UserService userService = new UserService();
		User user = userService.findByCode(code);
		// 3.判断:
		if(user == null){
			// 没有查询到该用户.
			req.setAttribute("msg", "激活码不正确!");
		}else{
			// 已经查询到.
			// 修改用户的状态.
			user.setState(1);
			user.setCode(null);
			userService.update(user);
			req.setAttribute("msg", "激活成功!请去登录!");
		}
		return "/jsps/msg.jsp";
	}
	
	/**
	 * 异步校验用户名的执行的方法
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException 
	 */
	public String checkUsername(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		/**
		 * 1.接收用户名
		 * 2.调用业务层
		 * 3.判断显示数据
		 */
		// 1.接收数据
		String username = req.getParameter("username");
		// 2.调用业务层
		UserService userService = new UserService();
		User existUser = userService.findByUsername(username);
		// 3.判断是否查询到该用户.
		if(existUser == null){
			// 用户名可以使用
			resp.getWriter().println(1);
		}else{
			// 用户名已经被使用了
			resp.getWriter().println(2);
		}
		return null;
	}

	/**
	 * 用户注册的执行的方法:
	 * @param req
	 * @param resp
	 * @return
	 */
	public String regist(HttpServletRequest req,HttpServletResponse resp){
		/**
		 * 1.接收数据.
		 * 2.封装数据.
		 * 3.调用业务层.
		 * 4.页面跳转.
		 */
		// 1.接收数据.
		Map<String,String[]> map = req.getParameterMap();
		// 2.封装数据.
		User user  = new User();
		try {
			BeanUtils.populate(user, map);
			// 3.调用业务层的代码:
			UserService userService = new UserService();
			userService.regist(user);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		req.setAttribute("msg", "You have registered successfully!");
		return "/jsps/msg.jsp";
	}
}

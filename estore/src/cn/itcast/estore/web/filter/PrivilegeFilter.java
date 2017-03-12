package cn.itcast.estore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.itcast.estore.vo.User;

/**
 * 权限过滤器:
 * 	* 没有登录，不能访问购物车和订单的模块.
 * @author 
 *
 */
public class PrivilegeFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/**
		 * 判断session中是否有登录用户的信息.
		 * 	* 如果有：放行.
		 * 	* 如果没有:跳转到其他额页面 .
		 */
		HttpServletRequest req = (HttpServletRequest) request;
		User existUser = (User) req.getSession().getAttribute("existUser");
		if(existUser == null){
			// 没有登录过
			req.setAttribute("msg", "您还没有登录！没有权限访问！");
			req.getRequestDispatcher("/jsps/user/login.jsp").forward(req, response);
		}else{
			// 已经登录过
			chain.doFilter(req, response);
		}
	}

	public void destroy() {
		
	}

}

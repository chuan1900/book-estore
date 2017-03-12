package cn.itcast.estore.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用的Servlet的编写
 * @author 
 *
 */
public class BaseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// http://localhost:8080/estore/customerServlet?method=add
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		// 接收参数
		String methodName = req.getParameter("method");
		// 反射:
		Class clazz = this.getClass();
		try {
			Method method = clazz.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			if(method == null){
				// 没有这个方法:
				resp.getWriter().println("没有该方法...");
				return ;
			}else{
				String result = (String) method.invoke(this, req,resp);
				if(result != null){
					req.getRequestDispatcher(result).forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

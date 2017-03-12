package cn.itcast.estore.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.service.CategoryService;
import cn.itcast.estore.utils.BaseServlet;
import cn.itcast.estore.vo.Category;
/**
 * 分类模块的Servlet
 * @author 
 *
 */
public class CategoryServlet extends BaseServlet {
	
	/**
	 * 查询所有分类的执行的方法.
	 * @param req
	 * @param resp
	 * @return
	 */
	public String findAll(HttpServletRequest req,HttpServletResponse resp){
		// 创建业务层类
		CategoryService categoryService = new CategoryService();
		// 调用业务层代码
		List<Category> list = categoryService.findAll();
		req.setAttribute("list", list);
		return "/jsps/left.jsp";
	}
}

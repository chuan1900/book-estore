package cn.itcast.estore.web.servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.estore.service.CategoryService;
import cn.itcast.estore.utils.BaseServlet;
import cn.itcast.estore.utils.UUIDUitls;
import cn.itcast.estore.vo.Category;

public class AdminCategoryServlet extends BaseServlet {

	/**
	 * 后台查询所有分类的方法
	 */
	public String findAll(HttpServletRequest req,HttpServletResponse resp){
		// 查询所有分类:
		CategoryService categoryService = new CategoryService();
		List<Category> list = categoryService.findAll();
		req.setAttribute("list", list);
		return "/adminjsps/admin/category/list.jsp";
	}
	
	/**
	 * 添加分类的执行的方法:
	 */
	public String save(HttpServletRequest req,HttpServletResponse resp){
		// 1.接收参数:
		String cname = req.getParameter("cname");
		Category category =new Category();
		category.setCid(UUIDUitls.getUUID());
		category.setCname(cname);
		// 2.调用 Service 插入数据
		CategoryService categoryService = new CategoryService();
		categoryService.save(category);
		// 3.列表页面
		return findAll(req,resp);
	}
	
	/**
	 * 根据分类的ID查询分类的方法:
	 */
	public String findByCid(HttpServletRequest req,HttpServletResponse resp){
		// 1.接收cid
		String cid = req.getParameter("cid");
		// 2.根据cid查询:
		CategoryService categoryService = new CategoryService();
		Category category = categoryService.findByCid(cid);
		// 3.页面跳转:
		req.setAttribute("category", category);
		return "/adminjsps/admin/category/mod.jsp";
	}
	
	/**
	 * 修改分类的方法:
	 */
	public String update(HttpServletRequest req,HttpServletResponse resp){
		// 接收数据:
		Map<String,String[]> map = req.getParameterMap();
		// 封装数据：
		Category category = new Category();
		try {
			BeanUtils.populate(category, map);
			// 调用业务层完成修改:
			CategoryService categoryService = new CategoryService();
			categoryService.update(category);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return findAll(req,resp);
	}
	
	/**
	 * 删除分类的方法:
	 */
	public String delete(HttpServletRequest req,HttpServletResponse resp){
		// 1.接收cid:
		String cid = req.getParameter("cid");
		// 2.删除分类:
		CategoryService categoryService = new CategoryService();
		categoryService.delete(cid);
		return findAll(req, resp);
	}
}

package cn.itcast.estore.service;

import java.util.List;

import cn.itcast.estore.dao.CategoryDao;
import cn.itcast.estore.vo.Category;

/**
 * 分类管理业务层的代码
 * @author 
 *
 */
public class CategoryService {
	
	/**
	 * 业务层的查询所有的方法
	 * @return
	 */
	public List<Category> findAll() {
		CategoryDao categoryDao = new CategoryDao();
		return categoryDao.findAll();
	}

	/**
	 * 业务层保存分类的方法
	 * @param category
	 */
	public void save(Category category) {
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.save(category);
	}

	/**
	 * 业务层根据cid查询分类的方法
	 * @param cid
	 * @return
	 */
	public Category findByCid(String cid) {
		CategoryDao categoryDao = new CategoryDao();
		return categoryDao.findByCid(cid);
	}

	/**
	 * 业务层修改分类的方法
	 * @param category
	 */
	public void update(Category category) {
		CategoryDao categoryDao = new CategoryDao();		
		categoryDao.update(category);
	}

	/**
	 * 业务层删除分类的方法
	 * @param cid
	 */
	public void delete(String cid) {
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.delete(cid);
	}

}

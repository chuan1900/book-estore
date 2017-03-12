package cn.itcast.estore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.estore.utils.JDBCUtils;
import cn.itcast.estore.vo.Category;

/**
 * 分类管理的DAO的代码
 * @author 
 *
 */
public class CategoryDao {

	/**
	 * DAO中查询所有分类的方法:
	 * @return
	 */
	public List<Category> findAll() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from category";
		List<Category> list;
		try {
			list = queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

	/**
	 * DAO中的保存分类的方法
	 * @param category
	 */
	public void save(Category category) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into category values (?,?)";
		try {
			queryRunner.update(sql, category.getCid(),category.getCname());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * DAO中根据cid查询分类的方法
	 * @param cid
	 * @return
	 */
	public Category findByCid(String cid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from category where cid = ?";
		Category category ;
		try {
			category= queryRunner.query(sql, new BeanHandler<Category>(Category.class),cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return category;
	}

	/**
	 * 修改分类的方法
	 * @param category
	 */
	public void update(Category category) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update category set cname = ? where cid = ?";
		try {
			queryRunner.update(sql, category.getCname(),category.getCid());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * DAO中删除分类的方法
	 * @param cid
	 */
	public void delete(String cid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update book set cid = null where cid = ?";
		try {
			queryRunner.update(sql, cid);
			sql = "delete from category where cid = ?";
			queryRunner.update(sql, cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}

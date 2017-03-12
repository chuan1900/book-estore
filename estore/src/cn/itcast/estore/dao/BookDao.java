package cn.itcast.estore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.estore.utils.JDBCUtils;
import cn.itcast.estore.vo.Book;

/**
 * 图书模块DAO的代码
 * 
 * @author 
 * 
 */
public class BookDao {

	/**
	 * DAO中统计图书个数的方法
	 * 
	 * @return
	 */
	public int findCount() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from book where isdel = 0";
		Long num;
		try {
			num = (Long) queryRunner.query(sql, new ScalarHandler());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return num.intValue();
	}

	/**
	 * DAO中分页查询的方法
	 * 
	 * @param begin
	 * @param pageSize
	 * @return
	 */
	public List<Book> findByPage(int begin, int pageSize) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from book where isdel = 0 limit ?,?";
		List<Book> list;
		try {
			list = queryRunner.query(sql,
					new BeanListHandler<Book>(Book.class), begin, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

	/**
	 * DAO中根据CID查询图书的方法
	 * 
	 * @param cid
	 * @return
	 */
	public List<Book> findByCid(String cid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from book where cid = ? and isdel = 0";
		List<Book> list;
		try {
			list = queryRunner.query(sql,
					new BeanListHandler<Book>(Book.class), cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

	/**
	 * DAO中根据BID查询图书的方法
	 * 
	 * @param bid
	 * @return
	 */
	public Book findByBid(String bid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from book where bid = ?";
		Book book;
		try {
			book = queryRunner.query(sql, new BeanHandler<Book>(Book.class),
					bid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return book;
	}

	/**
	 * 查询所有图书的方法
	 * 
	 * @return
	 */
	public List<Book> findAll() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from book where isdel = 0";
		List<Book> list;
		try {
			list = queryRunner
					.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

	/**
	 * DAO中保存图书的方法
	 * 
	 * @param book
	 */
	public void save(Book book) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into book values (?,?,?,?,?,?,?)";
		Object[] params = { book.getBid(), book.getBname(), book.getPrice(),
				book.getAuthor(), book.getImage(), book.getCid(),
				book.getIsdel() };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * DAO中修改图书的方法
	 * @param book
	 */
	public void update(Book book) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql= "update book set bname = ?,price=?,author=?,image=?,cid=?,isdel=? where bid = ?";
		Object[] params = {  book.getBname(), book.getPrice(),
				book.getAuthor(), book.getImage(), book.getCid(),
				book.getIsdel(),book.getBid() };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * DAO中的根据状态查询图书
	 * @return
	 */
	public List<Book> findByIsDel() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from book where isdel = ?";
		List<Book> list;
		try {
			list = queryRunner.query(sql, new BeanListHandler<Book>(Book.class), 1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

}

package cn.itcast.estore.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.itcast.estore.utils.JDBCUtils;
import cn.itcast.estore.vo.Book;
import cn.itcast.estore.vo.Order;
import cn.itcast.estore.vo.OrderItem;

public class OrderDao {

	/**
	 * DAO中生成订单的方法
	 * 
	 * @param conn
	 * @param order
	 */
	public void save(Connection conn, Order order) {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "insert into orders values (?,?,?,?,?,?)";
		Object[] params = { order.getOid(), order.getTotal(),
				order.getOrdertime(), order.getState(), order.getAddress(),
				order.getUser().getUid() };
		try {
			queryRunner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * DAO中生成订单项的方法
	 * 
	 * @param conn
	 * @param orderItem
	 */
	public void save(Connection conn, OrderItem orderItem) {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "insert into orderItem values (?,?,?,?,?)";
		Object[] params = { orderItem.getItemid(), orderItem.getCount(),
				orderItem.getSubtotal(), orderItem.getBook().getBid(),
				orderItem.getOrder().getOid() };
		try {
			queryRunner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	/**
	 * DAO中的根据用户的ID查询订单的方法
	 * 
	 * @param uid
	 * @return
	 */
	public List<Order> findByUid(String uid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where uid = ? order by ordertime desc";
		// 查询到了所有的订单,但是每个订单中没有其订单项.
		List<Order> list;
		try {
			list = queryRunner.query(sql, new BeanListHandler<Order>(
					Order.class), uid);
			// 查询订单中的订单项.
			sql = "SELECT * FROM orderitem o,book b WHERE o.bid = b.bid AND o.oid = ?";
			for (Order order : list) {
				List<Map<String, Object>> oList = queryRunner.query(sql,
						new MapListHandler(), order.getOid());
				for (Map<String, Object> map : oList) {

					// 数据封装:
					Book book = new Book();
					BeanUtils.populate(book, map);

					OrderItem orderItem = new OrderItem();
					BeanUtils.populate(orderItem, map);
					orderItem.setBook(book);

					order.getOrderItems().add(orderItem);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

	/**
	 * DAO中根据OID查询订单的方法
	 * 
	 * @param oid
	 * @return
	 */
	public Order findByOid(String oid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where oid = ?";
		Order order;
		try {
			order = queryRunner.query(sql, new BeanHandler<Order>(Order.class),
					oid);
			sql = "SELECT * FROM orderitem o,book b WHERE o.bid = b.bid AND o.oid = ?";
			List<Map<String, Object>> oList = queryRunner.query(sql,
					new MapListHandler(), oid);
			for (Map<String, Object> map : oList) {
				Book book = new Book();
				BeanUtils.populate(book, map);

				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map);
				orderItem.setBook(book);

				order.getOrderItems().add(orderItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return order;
	}

	/**
	 * DAO中的修改订单的方法
	 * 
	 * @param order
	 */
	public void update(Order order) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update orders set total=?,ordertime=?,state=?,address=? where oid = ?";
		Object[] params = { order.getTotal(), order.getOrdertime(),
				order.getState(), order.getAddress(), order.getOid() };
		try {
			queryRunner.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * DAO中查询所有订单的方法
	 * @return
	 */
	public List<Order> findAll() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders order by ordertime desc";
		List<Order> list;
		try {
			list = queryRunner.query(sql, new BeanListHandler<Order>(Order.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}
	// 查询订单的时候查询了订单项
	/*public List<Order> findAll() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders order by ordertime desc";
		List<Order> list;
		try {
			list = queryRunner.query(sql, new BeanListHandler<Order>(Order.class));
			sql = "SELECT * FROM orderitem o,book b WHERE o.bid = b.bid AND o.oid = ?";
			for (Order order : list) {
				List<Map<String,Object>> oList = queryRunner.query(sql, new MapListHandler(), order.getOid());
				for (Map<String, Object> map : oList) {
					Book book = new Book();
					BeanUtils.populate(book, map);

					OrderItem orderItem = new OrderItem();
					BeanUtils.populate(orderItem, map);
					orderItem.setBook(book);

					order.getOrderItems().add(orderItem);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}*/

	/**
	 * 根据状态查询订单
	 * @param state
	 * @return
	 */
	public List<Order> findByState(int state) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where state = ? order by ordertime desc";
		List<Order> list;
		try {
			list = queryRunner.query(sql, new BeanListHandler<Order>(Order.class),state);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}
	// 按状态查询:查询订单的时候，查询订单项
	/*public List<Order> findByState(int state) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where state = ? order by ordertime desc";
		List<Order> list;
		try {
			list = queryRunner.query(sql, new BeanListHandler<Order>(Order.class),state);
			sql = "SELECT * FROM orderitem o,book b WHERE o.bid = b.bid AND o.oid = ?";
			for (Order order : list) {
				List<Map<String,Object>> oList = queryRunner.query(sql, new MapListHandler(), order.getOid());
				for (Map<String, Object> map : oList) {
					Book book = new Book();
					BeanUtils.populate(book, map);

					OrderItem orderItem = new OrderItem();
					BeanUtils.populate(orderItem, map);
					orderItem.setBook(book);

					order.getOrderItems().add(orderItem);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}*/

	public List<OrderItem> findOrderItem(String oid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT * FROM orderitem o,book b WHERE o.bid = b.bid AND o.oid = ?";
		List<OrderItem> list = new ArrayList<OrderItem>();
		try {
			List<Map<String,Object>> oList = queryRunner.query(sql, new MapListHandler(), oid);
			for (Map<String, Object> map : oList) {
				Book book = new Book();
				BeanUtils.populate(book, map);

				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map);
				orderItem.setBook(book);
				
				list.add(orderItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

}

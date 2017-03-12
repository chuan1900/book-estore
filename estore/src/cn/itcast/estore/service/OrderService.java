package cn.itcast.estore.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import cn.itcast.estore.dao.OrderDao;
import cn.itcast.estore.utils.JDBCUtils;
import cn.itcast.estore.vo.Order;
import cn.itcast.estore.vo.OrderItem;
/**
 * 业务层的订单模块的代码
 * @author 
 *
 */
public class OrderService {

	/**
	 * 业务层生成订单的方法
	 * @param order
	 */
	public void save(Order order) {
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			OrderDao orderDao = new OrderDao();
			// 向订单表插入记录
			orderDao.save(conn,order);
			// 向订单项表插入记录
			for (OrderItem orderItem : order.getOrderItems()) {
				orderDao.save(conn,orderItem);
			}
			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			e.printStackTrace();
			
		}
		
		
	}

	/**
	 * 业务层根据用户的ID查询订单
	 * @param uid
	 * @return
	 */
	public List<Order> findByUid(String uid) {
		OrderDao orderDao = new OrderDao();
		return orderDao.findByUid(uid);
	}

	/**
	 * 业务层根据订单ID查询订单的方法
	 * @param oid
	 * @return
	 */
	public Order findByOid(String oid) {
		OrderDao orderDao = new OrderDao();
		return orderDao.findByOid(oid);
	}

	/**
	 * 业务层修改订单的方法
	 * @param order
	 */
	public void update(Order order) {
		OrderDao orderDao = new OrderDao();
		orderDao.update(order);
	}

	/**
	 * 业务层查询所有订单的方法
	 * @return
	 */
	public List<Order> findAll() {
		OrderDao orderDao = new OrderDao();
		return orderDao.findAll();
	}

	/**
	 * 业务层按状态查询的方法
	 * @param state
	 * @return
	 */
	public List<Order> findByState(int state) {
		OrderDao orderDao = new OrderDao();
		return orderDao.findByState(state);
	}

	/**
	 * 业务层根据订单ID查询订单项的方法
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOrderItem(String oid) {
		OrderDao orderDao = new OrderDao();
		
		return orderDao.findOrderItem(oid);
	}

}

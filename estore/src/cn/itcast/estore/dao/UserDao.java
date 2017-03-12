package cn.itcast.estore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.estore.utils.JDBCUtils;
import cn.itcast.estore.vo.User;

/**
 * 用户模块的DAO的代码
 * 
 * @author 
 * 
 */
public class UserDao {

	/**
	 * DAO中的保存用户的方法
	 * 
	 * @param user
	 */
	public void save(User user) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user values (?,?,?,?,?,?)";
		Object[] params = { user.getUid(), user.getUsername(),
				user.getPassword(), user.getEmail(), user.getState(),
				user.getCode() };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	
	}

	/**
	 * DAO中的根据用户名查询用户额方法
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where username = ?";
		User user;
		try {
			user = queryRunner.query(sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return user;
	}

	/**
	 * DAO中根据激活码查询用户的方法:
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where code = ?";
		User user;
		try {
			user = queryRunner.query(sql, new BeanHandler<User>(User.class), code);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return user;
	}

	/**
	 * DAO中修改用户的方法.
	 * @param user
	 */
	public void update(User user) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set username = ?,password =? ,email =? ,state = ?,code = ? where uid = ?";
		Object[] params = { user.getUsername(),
				user.getPassword(), user.getEmail(), user.getState(),
				user.getCode(),user.getUid() };
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 根据用户名和密码查询用户的方法
	 * @param user
	 * @return
	 */
	public User findByUsernameAndPassword(User user) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ? and state = ?";
		User existUser;
		try {
			existUser = queryRunner.query(sql, new BeanHandler<User>(User.class), user.getUsername(),user.getPassword(),1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return existUser;
	}

}

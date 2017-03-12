package cn.itcast.estore.service;

import cn.itcast.estore.dao.UserDao;
//import cn.itcast.estore.utils.MailUtils;
import cn.itcast.estore.utils.UUIDUitls;
import cn.itcast.estore.vo.User;
/**
 * 用户模块业务层的类.
 * @author 
 *
 */
public class UserService {

	/**
	 * 业务层的注册的方法
	 * @param user
	 */
	public void regist(User user) {
		// 插入数据库
		UserDao userDao = new UserDao();
		// 补全user的数据:
		user.setUid(UUIDUitls.getUUID());
		//!!!change state==1
		user.setState(1);
		//user.setState(0);
		String code = UUIDUitls.getUUID()+UUIDUitls.getUUID();
		user.setCode(code);
		userDao.save(user);
		//!!!change delete sendmail
		// 发送邮件
		//MailUtils.sendMail(user.getEmail(), code);
	}

	/**
	 * 业务层根据用户名查询的方法
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		UserDao userDao = new UserDao();
		return userDao.findByUsername(username);
	}

	/**
	 * 业务层根据激活码查询用户的方法
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		UserDao userDao = new UserDao();
		return userDao.findByCode(code);
	}

	/**
	 * 业务层修改用户的方法
	 * @param user
	 */
	public void update(User user) {
		UserDao userDao = new UserDao();
		userDao.update(user);
	}

	/**
	 * 业务层登录的方法
	 * @param user
	 * @return
	 */
	public User login(User user) {
		UserDao userDao = new UserDao();
		return userDao.findByUsernameAndPassword(user);
	}

}

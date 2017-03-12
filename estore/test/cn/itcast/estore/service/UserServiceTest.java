package cn.itcast.estore.service;

import static org.junit.Assert.*;
import cn.itcast.estore.dao.UserDao;
//import cn.itcast.estore.utils.MailUtils;
import cn.itcast.estore.utils.UUIDUitls;
import cn.itcast.estore.vo.User;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {
	UserService userservice;
	UserDao userdao;

	@Before
	public void setUp() throws Exception {
		userservice=new UserService();
		userdao=new UserDao();
	}

	@Test
	public void testRegist() {
		User user=new User();
		user.setUsername("servicetest");
		user.setPassword("test");
		user.setEmail("servicetest@gamil.com");
		userservice.regist(user);
		User user1=new User();
		user1=userdao.findByUsername("servicetest");
		Assert.assertEquals(user1.getPassword(), "test");
	}



}

package cn.itcast.estore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.estore.utils.JDBCUtils;
import cn.itcast.estore.vo.User;
import junit.framework.Assert;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {
	UserDao userdao;

	@Before
	public void setUp() throws Exception {
		userdao=new UserDao();
	}

	@Test
	public void testSave() {
		User user=new User();
		user.setUsername("testu");
		user.setUid("ss13n3hi32b5n6k2h5b6m2h5b3m29r7u");
		user.setCode("2d13n3hi32b5n6k2h5b6m2h5b3m29r7u");
		user.setEmail("testuser@gmail.com");
		user.setState(1);
		user.setPassword("test");
		userdao.save(user);
		Assert.assertEquals(user.getUsername(), "testu");
		Assert.assertEquals(user.getUid(), "ss13n3hi32b5n6k2h5b6m2h5b3m29r7u");
		Assert.assertEquals(user.getCode(), "2d13n3hi32b5n6k2h5b6m2h5b3m29r7u");
		Assert.assertEquals(user.getPassword(), "test");
		//DELETE FROM `bookstore`.`user` WHERE `uid`='ss13n3hi32b5n6k2h5b6m2h5b3m29r7u';
	}

	@Test
	public void testFindByUsername() {
		User user=userdao.findByUsername("zhen");
		String password=user.getPassword();
		Assert.assertEquals(password, "123");
	}

	@Test
	public void testFindByCode() {
		User user=userdao.findByCode("072a23c22a0e44ff815a2630e2345e3844a4f0a365f94d11b0fe292ba28c05ab");
		String username=user.getUsername();
		Assert.assertEquals(username, "meng");
	}



	@Test
	public void testFindByUsernameAndPassword() {
		User user=new User();
		user.setUsername("zhen");
		//user.setUid("ss13n3hi32b5n6k2h5b6m2h5b3m29r7u");
		//user.setCode("2d13n3hi32b5n6k2h5b6m2h5b3m29r7u");
		//user.setEmail("testuser@gmail.com");
		user.setState(1);
		user.setPassword("123");
		User user1=userdao.findByUsernameAndPassword(user);
		Assert.assertEquals(user1.getEmail(), "123@345.cn");
	}

}

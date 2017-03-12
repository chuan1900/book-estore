package cn.itcast.estore.dao;

import static org.junit.Assert.*;
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

import org.junit.Before;
import org.junit.Test;

public class OrderDaoTest {
	
	OrderDao orderdao;

	@Before
	public void setUp() throws Exception {
		orderdao=new OrderDao();
	}

	@Test
	public void testSave() {
		
		
	}

}

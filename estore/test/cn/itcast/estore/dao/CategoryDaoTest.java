package cn.itcast.estore.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.estore.utils.JDBCUtils;
import cn.itcast.estore.vo.Category;
import junit.framework.Assert;

public class CategoryDaoTest {
	CategoryDao categorydao;

	@Before
	public void setUp() throws Exception {
		categorydao=new CategoryDao();
	}

	@Test
	public void testFindAll() {
		List<Category> list;
		list=categorydao.findAll();
		Assert.assertEquals(list.get(3).getCid(), "c1");
		
	}

	@Test
	public void testFindByCid() {
		Category category=new Category();
		category=categorydao.findByCid("c1");
		Assert.assertEquals(category.getCname(), "Java SE");
	}

}

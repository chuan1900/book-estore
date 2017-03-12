package cn.itcast.estore.dao;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.estore.utils.JDBCUtils;
import cn.itcast.estore.vo.Book;
import junit.framework.Assert;

public class BookDaoTest {
	BookDao bookdao;

	@Before
	public void setUp() throws Exception {
		bookdao=new BookDao();
	}

	@Test
	public void testFindCount() {
		int booknum;
		booknum=bookdao.findCount();
		Assert.assertEquals(booknum, 11);
	}

	@Test
	public void testFindByPage() {
		List<Book> list;
		list=bookdao.findByPage(0,8);
		String bid=list.get(0).getBid();
		Assert.assertEquals(bid,"5652c363f7d44ca5aac42d861697faec");
	}

	@Test
	public void testFindByCid() {
		List<Book> list;
		list=bookdao.findByCid("c3");
		Assert.assertEquals(list.get(0).getBid(),"b9");
		Assert.assertEquals(list.get(1).getBid(),"bc57600bc7f94dcda62165947f60c23f");
	}

	@Test
	public void testFindByBid() {
		Book book=new Book();
		book=bookdao.findByBid("b1");
		Assert.assertEquals(book.getPrice(),75.60);
	}

	@Test
	public void testFindAll() {
		List<Book> list;
		list=bookdao.findAll();
		Assert.assertEquals(list.size(),11);
		
	}

	
	@Test
	public void testFindByIsDel() {
		List<Book> list;
		list=bookdao.findByIsDel();
		Assert.assertEquals(list.size(),2);
	}

}

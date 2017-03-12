package cn.itcast.estore.service;

import static org.junit.Assert.*;
import java.util.List;

import cn.itcast.estore.dao.BookDao;
import cn.itcast.estore.vo.Book;
import cn.itcast.estore.vo.PageBean;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class BookServiceTest {
	BookService bookservice;

	@Before
	public void setUp() throws Exception {
		bookservice=new BookService();
	}

	@Test
	public void testFindByPage() {
		PageBean<Book> pageBean = new PageBean<Book>();
		pageBean=bookservice.findByPage(0);
		Assert.assertEquals(pageBean.getPageSize(),8);
	}

	

}


package cn.itcast.estore.service;

import java.util.List;

import cn.itcast.estore.dao.BookDao;
import cn.itcast.estore.vo.Book;
import cn.itcast.estore.vo.PageBean;

/**
 * 图书模块业务层代码
 * @author 
 *
 */
public class BookService {

	/**
	 * 业务层分页查询图书的方法
	 * @param currPage
	 * @return
	 */
	public PageBean<Book> findByPage(int currPage) {
		PageBean<Book> pageBean = new PageBean<Book>();
		BookDao bookDao = new BookDao();
		// 设置当前页数:
		pageBean.setCurrPage(currPage);
		// 设置 没有显示记录:
		int pageSize = 8;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		int totalCount = bookDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		
		/*if(totalCount % pageSize == 0){
			totalPage = totalCount / pageSize;
		}else{
			totalPage = totalCount / pageSize + 1;
		}*/
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		int totalPage = num.intValue();
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合;
		int begin = (currPage - 1) * pageSize;
		List<Book> list = bookDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 业务层根据CID查询图书的方法
	 * @param cid
	 * @return
	 */
	public List<Book> findByCid(String cid) {
		BookDao bookDao = new BookDao();
		return bookDao.findByCid(cid);
	}

	/**
	 * 业务层根据BID查询图书的方法
	 * @param bid
	 * @return
	 */
	public Book findByBid(String bid) {
		BookDao bookDao = new BookDao();
		return bookDao.findByBid(bid);
	}

	/**
	 * 业务层查询所有图书的方法
	 * @return
	 */
	public List<Book> findAll() {
		BookDao bookDao = new BookDao();
		return bookDao.findAll();
	}

	/**
	 * 业务层添加图书的方法
	 * @param book
	 */
	public void save(Book book) {
		BookDao bookDao = new BookDao();
		bookDao.save(book);
	}

	/**
	 * 业务层修改图书的方法
	 * @param book
	 */
	public void update(Book book) {
		BookDao bookDao = new BookDao();
		bookDao.update(book);
	}

	/**
	 * 业务层查询已经下架的图书
	 * @return
	 */
	public List<Book> findByIsDel() {
		BookDao bookDao = new BookDao();
		return bookDao.findByIsDel();
	}

}




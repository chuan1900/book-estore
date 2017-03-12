package cn.itcast.estore.vo;

import java.util.List;

/**
 * 分页封装数据的类:
 * @author 
 *
 */
public class PageBean<T> {
	private int currPage; 	// 当前页数
	private int pageSize;	// 每页显示的记录
	private int totalCount; // 总记录数.
	private int totalPage;	// 总页数.
	private List<T> list; // 每页显示数据的集合
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}

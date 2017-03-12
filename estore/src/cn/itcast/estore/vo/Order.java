package cn.itcast.estore.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单的实体
 * @author 
 *
 */
public class Order {
	private String oid;
	private double total;
	private Date ordertime;
	private int state;	// 1:未付款	2:已经付款，卖家没发货     3:卖家已经发货,买家还没有确认收货    4:买家确认收货,订单结束
	private String address;
	private User user;
	
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();// 订单中的订单项

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
}

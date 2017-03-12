package cn.itcast.estore.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车的实体
 * @author 
 *
 */
public class Cart {
	// 购物项的集合 Key是图书的ID  Value是购物项
	private Map<String,CartItem> map = new LinkedHashMap<String, CartItem>();
	// 总计
	private double total;
	
	/*public Map<String, CartItem> getMap() {
		return map;
	}*/
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	public double getTotal() {
		return total;
	}
	
	/**
	 * 将购物项添加到购物车
	 */
	public void addCart(CartItem cartItem){
		// 判断购物车中是否存在该购物项.
		String bid = cartItem.getBook().getBid();
		if(map.containsKey(bid)){
			// 购物车中已经存在该购物项
			CartItem _cartItem = map.get(bid);// 获得购物车中原来的购物项.
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			// 购物车中不存在该购物项
			map.put(bid, cartItem);
		}
		total += cartItem.getSubtotal();
	}
	
	/**
	 * 从购物车中移除购物项
	 */
	public void removeCart(String bid){
		// 从map中移除购物项
		CartItem cartItem = map.remove(bid);
		// 总计 = 总计 - 移除的小计
		total -= cartItem.getSubtotal();
	}
	
	/**
	 * 清空购物车
	 */
	public void clearCart(){
		map.clear();
		total = 0;
	}
}

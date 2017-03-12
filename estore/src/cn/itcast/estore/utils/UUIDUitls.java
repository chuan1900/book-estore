package cn.itcast.estore.utils;

import java.util.UUID;

/**
 * 随机字符串工具类
 * @author 
 *
 */
public class UUIDUitls {

	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}

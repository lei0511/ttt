package com.th.ox.cleaver.activiti.util;

import org.apache.logging.log4j.util.Strings;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 对象操作
 */
@Slf4j
public class CommonUtil {

	/**
	 * 根据同级别最大的path获取下一个path值
	 *
	 * @param parentPath
	 *            父path
	 * @param maxPath
	 *            同级别最大的path
	 */
	public static String getPathByMaxPath(String parentPath, String maxPath) {
		String newPath = "";
		if (Strings.isBlank(maxPath)) {
			newPath = parentPath + "0001";
		} else {
			int endValue = Integer.parseInt(maxPath.substring(maxPath.length() - 4)) + 1;// 获取同级别最大path后四位的值，并加1
			String path = String.format("%04d", endValue);// 补足四位（其中0表示补零而不是补空格，4表示至少4位
															// ）
			newPath = parentPath + path;
		}
		return newPath;

	}

	/**
	 * URL转向
	 */
	public static void forward(ServletRequest request, ServletResponse response, String url) {
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException | IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 将浮点数统一格式化为2为小数
	 *
	 * @param val
	 *            要格式化的值
	 */
	public static float formatFloat(float val) {
		return formatFloat(val, 2);
	}

	/**
	 * 将浮点数统一格式化为n未小数 ZColin
	 *
	 * @param val
	 *            要格式化的值
	 * @param digit
	 *            要保留的小数位数
	 */
	public static float formatFloat(float val, int digit) {
		float param = (float) (Math.pow(10, digit));
		return (float) (Math.round(val * param)) / param;
	}

	/**
	 * 将List<String>集合 转化为String 如{"aaa","bbb"} To 'aaa','bbb'
	 * 
	 * @param strlist
	 * @return
	 */
	public static String convertListToString(List<String> strlist) {
		StringBuffer sb = new StringBuffer();
		if (CollectionUtils.isNotEmpty(strlist)) {
			for (int i = 0; i < strlist.size(); i++) {
				if (i == 0) {
					sb.append("'").append(strlist.get(i)).append("'");
				} else {
					sb.append(",").append("'").append(strlist.get(i)).append("'");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 将数字转为字符串，方便导出excel
	 * 
	 * @param number
	 * @return
	 */
	public static String changeNumberToString(Object number) {
		return number == null ? "" : String.valueOf(number);
	}

	/**
	 * 给定一个字符串，给字符串中大写字母前加“_”,(用于java命名与数据库命名转换,例如将createTime转化为create_Time)
	 * 
	 * @param str
	 * @return
	 */
	public static String toUnderlineAndLowerCase(String str) {
		String ss = "";
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] >= 'A' && charArray[i] <= 'Z') {
				ss += "_" + charArray[i];
			} else {
				ss += charArray[i];
			}
		}
		return ss;
	}

	/**
	 * 查询排序定义
	 * 
	 * @param prop
	 *            排序字段名
	 * @param order
	 *            排序方式
	 * @param defaultProp
	 *            默认排序字段名
	 * @param defaultOrder
	 *            默认排序方式
	 * @return
	 */
	public static String getOrderBy(String prop, String order, String defaultProp, String defaultOrder) {
		if (Strings.isBlank(prop)) {
			prop = defaultProp;
		} else {
			prop = toUnderlineAndLowerCase(prop).toLowerCase();
		}

		if (Strings.isBlank(order)){
			order = defaultOrder;
		} else {
			switch (order) {
			case "ascending":
				order = "asc";
				break;
			case "descending":
				order = "desc";
				break;
			default:
				order = defaultOrder;
			}
		}
		return prop + " " + order;
	}
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID32(){
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}

	public static void main(String[] args){
		for(int i=0;i<9;i++){
			System.out.println(getUUID32());
		}

	}


}

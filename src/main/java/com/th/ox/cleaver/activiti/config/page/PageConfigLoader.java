package com.th.ox.cleaver.activiti.config.page;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;

/*
 * 在Controllor获取的请求中缺失部分Page分页信息时，通过此辅助类，可以帮助参数进行初始化
 * -----------------------------------------------------
 * 此方法较为鸡肋，回头如果用处不大，会酌情删掉
 * -----------------------------------------------------
 */
public class PageConfigLoader {

	private static final int DefaultCurrent = 1;
	private static final int DefaultSize = 10;

	/**
	 * 获取分页参数
	 *
	 * @param json
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Page getPageParam(JSONObject json) {
		int current = json.getIntValue("current");
		int size = json.getIntValue("size");
		if (current == 0)
			current = DefaultCurrent;
		if (size == 0)
			size = DefaultSize;
		return new Page(current, size);
	}

}

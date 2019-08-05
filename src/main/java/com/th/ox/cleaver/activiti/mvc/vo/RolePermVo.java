package com.th.ox.cleaver.activiti.mvc.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 用于存储角色对应权限的值--修改角色权限使用
 * @author wt
 *
 */
@SuppressWarnings("serial")
public class RolePermVo implements Serializable {

	private String rid;
	private List<String> pid = new ArrayList<>();

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public List<String> getPid() {
		return pid;
	}

	public void setPid(List<String> pid) {
		this.pid = pid;
	}

}

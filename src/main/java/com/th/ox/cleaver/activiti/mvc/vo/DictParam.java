package com.th.ox.cleaver.activiti.mvc.vo;

import java.io.Serializable;

/**
 * 字典参数对象
 */
@SuppressWarnings("serial")
public class DictParam implements Serializable {

	private String id;
	private String name;

	public DictParam() {

	}

	public DictParam(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

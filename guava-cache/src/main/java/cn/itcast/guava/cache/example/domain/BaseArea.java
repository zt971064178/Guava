package cn.itcast.guava.cache.example.domain;

import java.io.Serializable;

public class BaseArea implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;  
	private int parentCode;  
	private String name;  
	private int code;  
	private String pinyin; 
	private int type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentCode() {
		return parentCode;
	}

	public void setParentCode(int parentCode) {
		this.parentCode = parentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "{id:"+id+",name:"+name+"}";
	}
}

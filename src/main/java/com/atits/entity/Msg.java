package com.atits.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回的类
 * 
 * @author lfy
 * 
 */
@ApiModel(value="返回信息类",description="通过JSON的对象")
public class Msg {
	//状态码   100-成功    200-失败
	@ApiModelProperty(value = "状态码")
	private int code;
	//提示信息
	@ApiModelProperty(value = "提示信息")
	private String msg;

	
	//用户要返回给浏览器的数据
	@ApiModelProperty(value = "返回的数据")
	private Map<String, Object> data = new HashMap<String, Object>();

	public static Msg success(){
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("处理成功！");
		return result;
	}
	
	public static Msg fail(Exception e){
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg(e.getMessage());
		return result;
	}
	public static Msg fail(){
		Msg result = new Msg();
		result.setCode(200);
		return result;
	}

	public Msg add(String key, Object value){
		this.getData().put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
}

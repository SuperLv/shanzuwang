package com.shanzuwang.bean.req.bill.esignature.component;

import lombok.Data;

/**
 * @description 坐标 javabean
 * @author 宫清
 * @date 2019年7月14日 下午2:37:18
 * @since JDK1.7
 */
@Data
public class Pos {
	//页码
	private int page;
	
	//坐标X,左下角为原点，
	private float x;
	
	//坐标Y，左下角为原点
	private float y;
	
	public Pos(int page, float x, float y) {
		this.page = page;
		this.x = x;
		this.y = y;
	}
	
	public Pos() {
	}


	
}

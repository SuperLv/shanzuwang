package com.shanzuwang.bean.req.bill.esignature.component;

import lombok.Data;

/**
 * @description 组件样式 javabean
 * @author 宫清
 * @date 2019年7月14日 下午2:39:45
 * @since JDK1.7
 */
@Data
public class Style {

	// 组件宽度
	private float width;

	// 组件高度
	private float height;

	// 填充字体,默认 1，1-宋体，2-新宋体，3-微软雅黑，4-黑体，5-楷体
	private Integer font;

	// 填充字体大小,默认 12
	private Float fontSize;

	// 字体颜色，默认#000000 黑色
	private String textColor;

	public Style(float width, float height, Integer font, Float fontSize, String textColor) {
		this.width = width;
		this.height = height;
		this.font = font;
		this.fontSize = fontSize;
		this.textColor = textColor;
	}

	public Style() {
	}


}

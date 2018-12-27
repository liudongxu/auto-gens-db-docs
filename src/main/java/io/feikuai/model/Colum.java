package io.feikuai.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 表列信息
 * @author liudo
 * @date 2018/12/27
 */
@Data
public class Colum implements Serializable {


	private static final long serialVersionUID = -455006062954840695L;

	/**
	 * 列名
	 */
	private String name;
	/**
	 * 列注释
	 */
	private String comment;
	/**
	 * 列主外键、索引
	 */
	private String mk;
	/**
	 * 类型
	 */
	private String type;

	/**
	 * 获取位置
	 * @param pos
	 * @return
	 */
	public String getPos(int pos) {
		if (pos == 0) {
			return this.name;
		}
		if (pos == 1) {
			return this.type;
		}
		if (pos == 2) {
			return this.comment;
		}
		String m;
		if (this.mk.equals("PRI")) {
			m = "主键";
		} else {
			m = this.mk;
		}
		return m;
	}

	/**
	 * 获取列信息
	 * @param pos
	 * @return
	 */
	public static String getColum(int pos) {
		if (pos == 0) {
			return "字段";
		}
		if (pos == 1) {
			return "数据类型";
		}
		if (pos == 2) {
			return "注释";
		}
		return "类型";
	}
}

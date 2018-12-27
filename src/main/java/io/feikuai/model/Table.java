package io.feikuai.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 数据库表信息
 * @author liudo
 * @date 2018/12/27
 */
@Data
public class Table implements Serializable {


	private static final long serialVersionUID = 3020920288246124367L;
	/**
	 * 表名称
	 */
	private String name;
	/**
	 * 表注释
	 */
	private String comment;
	/**
	 * 表类型
	 */
	private String tableType;
}

package io.feikuai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import io.feikuai.model.Colum;
import io.feikuai.model.Table;

/**
  * 数据库接口
  * @author liudo
  * @date 2018/12/26
  */
@Repository("gensDAO")
@Mapper
public interface GensDAO {
	/**
	 * 获取当前数据库的所有表
	 * @return
	 */
	@Select("SHOW TABLES")
	List<String> selectTables();

	/**
	 * 获取当前数据库
	 * @return
	 */
	@Select("select database()")
	String selectDatabase();

	/**
	 * 获取表的详细信息
	 * @param dbName
	 * @param tableName
	 */
	@Select("select table_name as name,table_comment as comment, table_type as type FROM information_schema.tables "
			+ " where table_schema = #{dbName} and table_name =#{tableName} ")
	Table selectTableDetail(@Param("dbName") String dbName, @Param("tableName") String tableName);

	/**
	 * 查询表的列数据
	 * @param dbName
	 * @param tableName
	 * @return
	 */
	@Select("select  column_name as name, column_comment as comment ,column_key as mk,column_type as type  from information_schema.columns where "
			+ "table_schema = #{dbName}  and table_name = #{tableName}")
	List<Colum> selectColums(@Param("dbName") String dbName, @Param("tableName") String tableName);
}

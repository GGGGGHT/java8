package com.ggggght.learningjava8.generate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface IdTestInnodbDao {

	int deleteByPrimaryKey(Integer id);

	int insert(IdTestInnodb record);

	int insertSelective(IdTestInnodb record);

	@Select("select * from id_test_innodb where id = #{id}")
	IdTestInnodb selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(IdTestInnodb record);

	int updateByPrimaryKey(IdTestInnodb record);

}
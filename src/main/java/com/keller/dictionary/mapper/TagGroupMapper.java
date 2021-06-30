package com.keller.dictionary.mapper;

import com.code4j.mybatisutil.mybatis.BaseMapper;
import com.keller.dictionary.po.TagGroup;
import org.apache.ibatis.annotations.*;

/**
 *  TagGroupMapper接口
 * @author yangkaile
 * @date 2021-06-23 20:54:04
 */
@Mapper
public interface TagGroupMapper extends BaseMapper<TagGroup>{

    /**
     *  表名
     */
    String tableName = "cy_tag_group_info";

    /**
     * 表中所有字段
     */
    String fullFields ="id, name";}

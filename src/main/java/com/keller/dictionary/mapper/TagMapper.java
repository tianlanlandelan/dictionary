package com.keller.dictionary.mapper;

import com.code4j.mybatisutil.mybatis.BaseMapper;
import com.keller.dictionary.po.Tag;
import org.apache.ibatis.annotations.*;

/**
 *  TagMapper接口
 * @author yangkaile
 * @date 2021-06-23 20:54:34
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag>{

    /**
     *  表名
     */
    String tableName = "cy_tag_info";

    /**
     * 表中所有字段
     */
    String fullFields ="group_id, id, name";}

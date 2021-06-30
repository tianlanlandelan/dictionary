package com.keller.dictionary.mapper;

import com.code4j.mybatisutil.mybatis.BaseMapper;
import com.keller.dictionary.po.IdiomTag;
import org.apache.ibatis.annotations.*;

/**
 *  IdiomTagMapper接口
 * @author yangkaile
 * @date 2021-06-22 11:14:58
 */
@Mapper
public interface IdiomTagMapper extends BaseMapper<IdiomTag>{

    /**
     *  表名
     */
    String tableName = "cy_idiom_tag_ref";

    /**
     * 表中所有字段
     */
    String fullFields ="id, tag_id, word_id";}

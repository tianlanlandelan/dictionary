package com.keller.dictionary.mapper;

import com.code4j.mybatisutil.mybatis.BaseMapper;
import com.keller.dictionary.po.Idiom;
import org.apache.ibatis.annotations.*;

/**
 *  IdiomMapper接口
 * @author yangkaile
 * @date 2021-06-29 10:39:45
 */
@Mapper
public interface IdiomMapper extends BaseMapper<Idiom>{

    /**
     *  表名
     */
    String tableName = "cy_idiom_info";

    /**
     * 表中所有字段
     */
    String fullFields ="id, name, phonetic_notation, phonetic_search, source, description";}

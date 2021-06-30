package com.keller.dictionary.mapper;

import com.code4j.mybatisutil.mybatis.BaseMapper;
import com.keller.dictionary.po.Word;
import org.apache.ibatis.annotations.*;

/**
 *  WordMapper接口
 * @author yangkaile
 * @date 2021-06-22 11:16:05
 */
@Mapper
public interface WordMapper extends BaseMapper<Word>{

    /**
     *  表名
     */
    String tableName = "cy_word_info";

    /**
     * 表中所有字段
     */
    String fullFields ="frequently_used, id, phonetic_count, phonetic_notation, phonetic_search, stroke_count, tone, unicode, word";}

package com.keller.dictionary.mapper;

import com.code4j.mybatisutil.mybatis.BaseMapper;
import com.keller.dictionary.po.IdiomRef;
import org.apache.ibatis.annotations.*;

/**
 *  IdiomRefMapper接口
 * @author yangkaile
 * @date 2021-06-29 10:40:51
 */
@Mapper
public interface IdiomRefMapper extends BaseMapper<IdiomRef>{

    /**
     *  表名
     */
    String tableName = "cy_idiom_ref";

    /**
     * 表中所有字段
     */
    String fullFields ="id, a_id, b_id, ref_type";}

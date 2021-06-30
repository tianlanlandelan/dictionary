package com.keller.dictionary.po;

import com.code4j.mybatisutil.mybatis.annotation.TableAttribute;
import com.code4j.mybatisutil.mybatis.annotation.FieldAttribute;
import lombok.Data;


/**
 *
 * @author yangkaile
 * @date 2021-06-29 10:39:45
 */
@Data
@TableAttribute(name = "cy_idiom_info")
public class Idiom{
    /**
    * id
    */
    @FieldAttribute
    private Integer id;
    /**
    * 成语
    */
    @FieldAttribute
    private String name;
    /**
    * 拼音，用来拼读，有声调
    */
    @FieldAttribute
    private String phoneticNotation;
    /**
    * 注音，用来搜索，无声调
    */
    @FieldAttribute
    private String phoneticSearch;
    /**
    * 出处
    */
    @FieldAttribute
    private String source;
    /**
    * 释义
    */
    @FieldAttribute
    private String description;


  }

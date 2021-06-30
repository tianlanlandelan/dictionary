package com.keller.dictionary.po;

import com.code4j.mybatisutil.mybatis.annotation.TableAttribute;
import com.code4j.mybatisutil.mybatis.annotation.FieldAttribute;
import lombok.Data;


/**
 *
 * @author yangkaile
 * @date 2021-06-22 11:16:05
 */
@Data
@TableAttribute(name = "cy_word_info")
public class Word{
    /**
    * 是否常用  0 不常用 1 常用
    */
    @FieldAttribute
    private Integer frequentlyUsed;
    /**
    * id
    */
    @FieldAttribute
    private Integer id;
    /**
    * 拼音数量
    */
    @FieldAttribute
    private Integer phoneticCount;
    /**
    * 拼音
    */
    @FieldAttribute
    private String phoneticNotation;
    /**
    * 去掉声调的拼音
    */
    @FieldAttribute
    private String phoneticSearch;
    /**
    * 笔划数
    */
    @FieldAttribute
    private Integer strokeCount;
    /**
    * 声调
    */
    @FieldAttribute
    private Integer tone;
    /**
    * unicode
    */
    @FieldAttribute
    private String unicode;
    /**
    * 汉字
    */
    @FieldAttribute
    private String word;


  }

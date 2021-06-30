package com.keller.dictionary.po;

import com.code4j.mybatisutil.mybatis.annotation.TableAttribute;
import com.code4j.mybatisutil.mybatis.annotation.FieldAttribute;
import lombok.Data;


/**
 *
 * @author yangkaile
 * @date 2021-06-29 10:40:51
 */
@Data
@TableAttribute(name = "cy_idiom_ref")
public class IdiomRef{
    /**
    * id
    */
    @FieldAttribute
    private Integer id;
    /**
    * 词A的id
    */
    @FieldAttribute
    private Integer aId;
    /**
    * 词B的id
    */
    @FieldAttribute
    private Integer bId;
    /**
    * 词之间的关系：same 同义词，alike 近义词，contrary 反义词
    */
    @FieldAttribute
    private String refType;


  }

package com.keller.dictionary.po;

import com.code4j.mybatisutil.mybatis.annotation.TableAttribute;
import com.code4j.mybatisutil.mybatis.annotation.FieldAttribute;
import lombok.Data;


/**
 *
 * @author yangkaile
 * @date 2021-06-23 20:54:34
 */
@Data
@TableAttribute(name = "cy_tag_info")
public class Tag{
    /**
    * 分组ID
    */
    @FieldAttribute
    private Integer groupId;
    /**
    * id
    */
    @FieldAttribute
    private Integer id;
    /**
    * 名称
    */
    @FieldAttribute
    private String name;


  }

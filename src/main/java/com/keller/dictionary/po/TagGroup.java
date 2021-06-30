package com.keller.dictionary.po;

import com.code4j.mybatisutil.mybatis.annotation.TableAttribute;
import com.code4j.mybatisutil.mybatis.annotation.FieldAttribute;
import lombok.Data;


/**
 *
 * @author yangkaile
 * @date 2021-06-23 20:54:04
 */
@Data
@TableAttribute(name = "cy_tag_group_info")
public class TagGroup{
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

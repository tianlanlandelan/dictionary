package com.keller.dictionary.po;

import com.code4j.mybatisutil.mybatis.annotation.TableAttribute;
import com.code4j.mybatisutil.mybatis.annotation.FieldAttribute;
import lombok.Data;


/**
 *
 * @author yangkaile
 * @date 2021-06-22 11:14:58
 */
@Data
@TableAttribute(name = "cy_idiom_tag_ref")
public class IdiomTag{
    /**
    * id
    */
    @FieldAttribute
    private Integer id;
    /**
    * 标签ID
    */
    @FieldAttribute
    private Integer tagId;
    /**
    * 成语ID
    */
    @FieldAttribute
    private Integer wordId;


  }

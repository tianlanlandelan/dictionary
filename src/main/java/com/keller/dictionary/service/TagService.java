package com.keller.dictionary.service;

import com.keller.webcore.response.ServiceResponse;
import com.code4j.mybatisutil.mybatis.BasePage;
import com.code4j.mybatisutil.mybatis.BaseQuery;
import com.keller.dictionary.po.Tag;
import com.keller.dictionary.mapper.TagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  TagService
 * @author yangkaile
 * @date 2021-06-23 20:54:34
 */
@Service
@Slf4j
public class TagService{


    @Resource
    private TagMapper mapper;

    /**
     * 保存数据
     * 如果存在 id 相同的数据，覆盖更新
     * 否则，添加新的数据
     * @param tag  要保存的实体对象
     * @return  ServiceResponse 统一封装的返回对象，包含影响的数据条数
     */
    public ServiceResponse save(Tag tag){
        Integer result = mapper.baseSave(tag);
        return ServiceResponse.success(result);
    }

    /**
     * 查询数据
     * @param id    id
     * @return  ServiceResponse 统一封装的返回对象，包含查询出的具体数据
     */
    public ServiceResponse getById(Integer id){
        if(id == null){
            return ServiceResponse.noParams();
        }
        Tag tag = new Tag();
        tag.setId(id);
        tag = mapper.baseSelectById(tag);
        return ServiceResponse.success(tag);
    }

    /**
     * 查询列表
     * @param query 查询对象
     * @return  列表数据
     */
    public ServiceResponse queryList(BaseQuery<Tag> query){
        List<Tag> result = mapper.baseSelectList(query);
        return ServiceResponse.success(result);
    }

    /**
     * 查询列表，可指定查询条件
     * 查询对象中包含实体对象
     * 如果查询对象中有自定义的查询条件，则使用自定义查询条件；
     * 否则，使用实体对象中的非空字段作为查询条件，多个查询条件间 AND 连接
     * @param query 查询对象
     * @return  ServiceResponse，包含数据列表和分页参数
     */
    public ServiceResponse pageQuery(BaseQuery<Tag> query){
        BasePage<Tag> page = mapper.baseSelectPage(query);
        return ServiceResponse.success(page);
    }

    /**
     * 查询总数，可指定查询条件
     * @param query 查询对象
     * @return  ServiceResponse，包含数据总条数
     */
    public ServiceResponse count(BaseQuery<Tag> query){
        Integer result = mapper.baseSelectCount(query);
        return ServiceResponse.success(result);
    }

    /**
     * 删除数据
     * @param id    id
     * @return  ServiceResponse，包含受影响的数据条数
     */
    public ServiceResponse deleteById(Integer id){
        if(id == null){
            return ServiceResponse.noParams();
        }
        Tag tag = new Tag();
        tag.setId(id);
        Integer result = mapper.baseDeleteById(tag);
        return ServiceResponse.success(result);
    }

}

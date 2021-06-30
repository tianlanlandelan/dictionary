package com.keller.dictionary.service;

import com.keller.webcore.response.ServiceResponse;
import com.code4j.mybatisutil.mybatis.BasePage;
import com.code4j.mybatisutil.mybatis.BaseQuery;
import com.keller.dictionary.po.IdiomRef;
import com.keller.dictionary.mapper.IdiomRefMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  IdiomRefService
 * @author yangkaile
 * @date 2021-06-29 10:40:51
 */
@Service
@Slf4j
public class IdiomRefService{


    @Resource
    private IdiomRefMapper mapper;

    /**
     * 保存数据
     * 如果存在 id 相同的数据，覆盖更新
     * 否则，添加新的数据
     * @param idiomRef  要保存的实体对象
     * @return  ServiceResponse 统一封装的返回对象，包含影响的数据条数
     */
    public ServiceResponse save(IdiomRef idiomRef){
        Integer result = mapper.baseSave(idiomRef);
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
        IdiomRef idiomRef = new IdiomRef();
        idiomRef.setId(id);
        idiomRef = mapper.baseSelectById(idiomRef);
        return ServiceResponse.success(idiomRef);
    }

    /**
     * 查询列表
     * @param query 查询对象
     * @return  列表数据
     */
    public ServiceResponse queryList(BaseQuery<IdiomRef> query){
        List<IdiomRef> result = mapper.baseSelectList(query);
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
    public ServiceResponse pageQuery(BaseQuery<IdiomRef> query){
        BasePage<IdiomRef> page = mapper.baseSelectPage(query);
        return ServiceResponse.success(page);
    }

    /**
     * 查询总数，可指定查询条件
     * @param query 查询对象
     * @return  ServiceResponse，包含数据总条数
     */
    public ServiceResponse count(BaseQuery<IdiomRef> query){
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
        IdiomRef idiomRef = new IdiomRef();
        idiomRef.setId(id);
        Integer result = mapper.baseDeleteById(idiomRef);
        return ServiceResponse.success(result);
    }

}

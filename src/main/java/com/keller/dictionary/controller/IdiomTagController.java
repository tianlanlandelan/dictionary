package com.keller.dictionary.controller;

import com.keller.webcore.response.ServiceResponse;
import com.code4j.mybatisutil.mybatis.BaseQuery;
import com.keller.dictionary.po.IdiomTag;
import com.keller.dictionary.service.IdiomTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *  IdiomTagController
 * @author yangkaile
 * @date 2021-06-22 11:14:58
 */
@Slf4j
@RestController
@RequestMapping("/idiomTag")
public class IdiomTagController {

    @Resource
    private IdiomTagService service;

    /**
     * 保存数据
     * @param idiomTag
     * @return
     */
    @PostMapping
    public ServiceResponse save(@RequestBody IdiomTag idiomTag){
        log.info("====== idiomTag save ====== {}",idiomTag);
        return service.save(idiomTag);
    }

    /**
     * 查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ServiceResponse getById(@PathVariable Integer id){
        log.info("====== idiomTag getById ====== {}",id);
        return service.getById(id);
    }

    /**
     * 查询列表
     * @param idiomTag
     * @return
     */
    @GetMapping
    public ServiceResponse pageQuery(IdiomTag idiomTag,BaseQuery<IdiomTag> query){
        log.info("====== idiomTag pageQuery ====== {},{}",idiomTag,query);
        if(idiomTag == null){
            idiomTag = new IdiomTag();
        }
        if(query == null){
            query = new BaseQuery<>(idiomTag);
        }
        query.setEntity(idiomTag);
        return service.pageQuery(query);
    }

    /**
     * 查询总数
     * @param idiomTag
     * @return
     */
    @GetMapping("/count")
    public ServiceResponse count(IdiomTag idiomTag){
        log.info("====== idiomTag count ====== {}",idiomTag);
        if(idiomTag == null){
            idiomTag = new IdiomTag();
        }
        BaseQuery query = new BaseQuery<>(idiomTag);
        query.setEntity(idiomTag);
        return service.count(query);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ServiceResponse deleteById(@PathVariable Integer id){
        log.info("====== idiomTag deleteById ======  {}",id);
        return service.deleteById(id);
    }
}

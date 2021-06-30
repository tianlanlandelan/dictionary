package com.keller.dictionary.controller;

import com.keller.webcore.response.ServiceResponse;
import com.code4j.mybatisutil.mybatis.BaseQuery;
import com.keller.dictionary.po.IdiomRef;
import com.keller.dictionary.service.IdiomRefService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *  IdiomRefController
 * @author yangkaile
 * @date 2021-06-29 10:40:51
 */
@Slf4j
@RestController
@RequestMapping("/idiomRef")
public class IdiomRefController {

    @Resource
    private IdiomRefService service;

    /**
     * 保存数据
     * @param idiomRef
     * @return
     */
    @PostMapping
    public ServiceResponse save(@RequestBody IdiomRef idiomRef){
        log.info("====== idiomRef save ====== {}",idiomRef);
        return service.save(idiomRef);
    }

    /**
     * 查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ServiceResponse getById(@PathVariable Integer id){
        log.info("====== idiomRef getById ====== {}",id);
        return service.getById(id);
    }

    /**
     * 查询列表，不分页
     * @param idiomRef
     * @return
     */
    @GetMapping("/list")
    public ServiceResponse queryList(IdiomRef idiomRef){
        log.info("====== idiomRef count ====== {}",idiomRef);
        if(idiomRef == null){
            idiomRef = new IdiomRef();
        }
        BaseQuery query = new BaseQuery<>(idiomRef);
        query.setEntity(idiomRef);
        return service.queryList(query);
    }

    /**
     * 分页查询
     * @param idiomRef
     * @return
     */
    @GetMapping("/page")
    public ServiceResponse pageQuery(IdiomRef idiomRef,BaseQuery<IdiomRef> query){
        log.info("====== idiomRef pageQuery ====== {},{}",idiomRef,query);
        if(idiomRef == null){
            idiomRef = new IdiomRef();
        }
        if(query == null){
            query = new BaseQuery<>(idiomRef);
        }
        query.setEntity(idiomRef);
        return service.pageQuery(query);
    }

    /**
     * 查询总数
     * @param idiomRef
     * @return
     */
    @GetMapping("/count")
    public ServiceResponse count(IdiomRef idiomRef){
        log.info("====== idiomRef count ====== {}",idiomRef);
        if(idiomRef == null){
            idiomRef = new IdiomRef();
        }
        BaseQuery query = new BaseQuery<>(idiomRef);
        query.setEntity(idiomRef);
        return service.count(query);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ServiceResponse deleteById(@PathVariable Integer id){
        log.info("====== idiomRef deleteById ======  {}",id);
        return service.deleteById(id);
    }
}

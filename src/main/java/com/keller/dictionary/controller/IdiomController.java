package com.keller.dictionary.controller;

import com.keller.webcore.response.ServiceResponse;
import com.code4j.mybatisutil.mybatis.BaseQuery;
import com.keller.dictionary.po.Idiom;
import com.keller.dictionary.service.IdiomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *  IdiomController
 * @author yangkaile
 * @date 2021-06-29 10:39:45
 */
@Slf4j
@RestController
@RequestMapping("/idiom")
public class IdiomController {

    @Resource
    private IdiomService service;

    /**
     * 保存数据
     * @param idiom
     * @return
     */
    @PostMapping
    public ServiceResponse save(@RequestBody Idiom idiom){
        log.info("====== idiom save ====== {}",idiom);
        return service.save(idiom);
    }

    /**
     * 查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ServiceResponse getById(@PathVariable Integer id){
        log.info("====== idiom getById ====== {}",id);
        return service.getById(id);
    }

    /**
     * 查询列表，不分页
     * @param idiom
     * @return
     */
    @GetMapping("/list")
    public ServiceResponse queryList(Idiom idiom){
        log.info("====== idiom count ====== {}",idiom);
        if(idiom == null){
            idiom = new Idiom();
        }
        BaseQuery query = new BaseQuery<>(idiom);
        query.setEntity(idiom);
        return service.queryList(query);
    }

    /**
     * 分页查询
     * @param idiom
     * @return
     */
    @GetMapping("/page")
    public ServiceResponse pageQuery(Idiom idiom,BaseQuery<Idiom> query){
        log.info("====== idiom pageQuery ====== {},{}",idiom,query);
        if(idiom == null){
            idiom = new Idiom();
        }
        if(query == null){
            query = new BaseQuery<>(idiom);
        }
        query.setEntity(idiom);
        return service.pageQuery(query);
    }

    /**
     * 查询总数
     * @param idiom
     * @return
     */
    @GetMapping("/count")
    public ServiceResponse count(Idiom idiom){
        log.info("====== idiom count ====== {}",idiom);
        if(idiom == null){
            idiom = new Idiom();
        }
        BaseQuery query = new BaseQuery<>(idiom);
        query.setEntity(idiom);
        return service.count(query);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ServiceResponse deleteById(@PathVariable Integer id){
        log.info("====== idiom deleteById ======  {}",id);
        return service.deleteById(id);
    }
}

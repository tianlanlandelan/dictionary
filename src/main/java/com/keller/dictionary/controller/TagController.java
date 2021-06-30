package com.keller.dictionary.controller;

import com.keller.webcore.response.ServiceResponse;
import com.code4j.mybatisutil.mybatis.BaseQuery;
import com.keller.dictionary.po.Tag;
import com.keller.dictionary.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *  TagController
 * @author yangkaile
 * @date 2021-06-23 20:54:34
 */
@Slf4j
@RestController
@RequestMapping("/tag")
public class TagController {

    @Resource
    private TagService service;

    /**
     * 保存数据
     * @param tag
     * @return
     */
    @PostMapping
    public ServiceResponse save(@RequestBody Tag tag){
        log.info("====== tag save ====== {}",tag);
        return service.save(tag);
    }

    /**
     * 查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ServiceResponse getById(@PathVariable Integer id){
        log.info("====== tag getById ====== {}",id);
        return service.getById(id);
    }

    /**
     * 查询列表，不分页
     * @param tag
     * @return
     */
    @GetMapping("/list")
    public ServiceResponse queryList(Tag tag){
        log.info("====== tag count ====== {}",tag);
        if(tag == null){
            tag = new Tag();
        }
        BaseQuery query = new BaseQuery<>(tag);
        query.setEntity(tag);
        return service.queryList(query);
    }

    /**
     * 分页查询
     * @param tag
     * @return
     */
    @GetMapping("/page")
    public ServiceResponse pageQuery(Tag tag,BaseQuery<Tag> query){
        log.info("====== tag pageQuery ====== {},{}",tag,query);
        if(tag == null){
            tag = new Tag();
        }
        if(query == null){
            query = new BaseQuery<>(tag);
        }
        query.setEntity(tag);
        return service.pageQuery(query);
    }

    /**
     * 查询总数
     * @param tag
     * @return
     */
    @GetMapping("/count")
    public ServiceResponse count(Tag tag){
        log.info("====== tag count ====== {}",tag);
        if(tag == null){
            tag = new Tag();
        }
        BaseQuery query = new BaseQuery<>(tag);
        query.setEntity(tag);
        return service.count(query);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ServiceResponse deleteById(@PathVariable Integer id){
        log.info("====== tag deleteById ======  {}",id);
        return service.deleteById(id);
    }
}

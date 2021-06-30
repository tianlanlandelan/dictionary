package com.keller.dictionary.controller;

import com.keller.webcore.response.ServiceResponse;
import com.code4j.mybatisutil.mybatis.BaseQuery;
import com.keller.dictionary.po.TagGroup;
import com.keller.dictionary.service.TagGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *  TagGroupController
 * @author yangkaile
 * @date 2021-06-23 20:54:04
 */
@Slf4j
@RestController
@RequestMapping("/tagGroup")
public class TagGroupController {

    @Resource
    private TagGroupService service;

    /**
     * 保存数据
     * @param tagGroup
     * @return
     */
    @PostMapping
    public ServiceResponse save(@RequestBody TagGroup tagGroup){
        log.info("====== tagGroup save ====== {}",tagGroup);
        return service.save(tagGroup);
    }

    /**
     * 查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ServiceResponse getById(@PathVariable Integer id){
        log.info("====== tagGroup getById ====== {}",id);
        return service.getById(id);
    }

    /**
     * 查询列表，不分页
     * @param tagGroup
     * @return
     */
    @GetMapping("/list")
    public ServiceResponse queryList(TagGroup tagGroup){
        log.info("====== tagGroup count ====== {}",tagGroup);
        if(tagGroup == null){
            tagGroup = new TagGroup();
        }
        BaseQuery query = new BaseQuery<>(tagGroup);
        query.setEntity(tagGroup);
        return service.queryList(query);
    }

    /**
     * 分页查询
     * @param tagGroup
     * @return
     */
    @GetMapping("/page")
    public ServiceResponse pageQuery(TagGroup tagGroup,BaseQuery<TagGroup> query){
        log.info("====== tagGroup pageQuery ====== {},{}",tagGroup,query);
        if(tagGroup == null){
            tagGroup = new TagGroup();
        }
        if(query == null){
            query = new BaseQuery<>(tagGroup);
        }
        query.setEntity(tagGroup);
        return service.pageQuery(query);
    }

    /**
     * 查询总数
     * @param tagGroup
     * @return
     */
    @GetMapping("/count")
    public ServiceResponse count(TagGroup tagGroup){
        log.info("====== tagGroup count ====== {}",tagGroup);
        if(tagGroup == null){
            tagGroup = new TagGroup();
        }
        BaseQuery query = new BaseQuery<>(tagGroup);
        query.setEntity(tagGroup);
        return service.count(query);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ServiceResponse deleteById(@PathVariable Integer id){
        log.info("====== tagGroup deleteById ======  {}",id);
        return service.deleteById(id);
    }
}

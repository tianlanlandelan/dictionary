package com.keller.dictionary.controller;

import com.keller.webcore.response.ServiceResponse;
import com.code4j.mybatisutil.mybatis.BaseQuery;
import com.keller.dictionary.po.Word;
import com.keller.dictionary.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *  WordController
 * @author yangkaile
 * @date 2021-06-22 11:16:05
 */
@Slf4j
@RestController
@RequestMapping("/word")
public class WordController {

    @Resource
    private WordService service;

    /**
     * 保存数据
     * @param word
     * @return
     */
    @PostMapping
    public ServiceResponse save(@RequestBody Word word){
        log.info("====== word save ====== {}",word);
        return service.save(word);
    }

    /**
     * 查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ServiceResponse getById(@PathVariable Integer id){
        log.info("====== word getById ====== {}",id);
        return service.getById(id);
    }

    /**
     * 查询列表
     * @param word
     * @return
     */
    @GetMapping
    public ServiceResponse pageQuery(Word word,BaseQuery<Word> query){
        log.info("====== word pageQuery ====== {},{}",word,query);
        if(word == null){
            word = new Word();
        }
        if(query == null){
            query = new BaseQuery<>(word);
        }
        query.setEntity(word);
        return service.pageQuery(query);
    }

    /**
     * 查询总数
     * @param word
     * @return
     */
    @GetMapping("/count")
    public ServiceResponse count(Word word){
        log.info("====== word count ====== {}",word);
        if(word == null){
            word = new Word();
        }
        BaseQuery query = new BaseQuery<>(word);
        query.setEntity(word);
        return service.count(query);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ServiceResponse deleteById(@PathVariable Integer id){
        log.info("====== word deleteById ======  {}",id);
        return service.deleteById(id);
    }
}

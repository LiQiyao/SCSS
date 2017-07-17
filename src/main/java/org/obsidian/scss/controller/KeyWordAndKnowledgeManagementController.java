package org.obsidian.scss.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.obsidian.scss.bean.KnowledgeAndKeyword;
import org.obsidian.scss.bean.Show;
import org.obsidian.scss.entity.Knowledge;
import org.obsidian.scss.entity.KnowledgeKeyword;
import org.obsidian.scss.service.KeywordService;
import org.obsidian.scss.service.KnowledgeKeywordService;
import org.obsidian.scss.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hp on 2017/7/16.
 */
@Controller
public class KeyWordAndKnowledgeManagementController {
    @Autowired
    KnowledgeService knowledgeService;
    @Autowired
    KnowledgeKeywordService knowledgeKeywordService;
    @Autowired
    KeywordService keywordService;
    @Autowired
    KeywordService keywordService;
    
    @RequestMapping("knowledgeSearch")
    @ResponseBody
    public Show knowledgeManagement(@RequestParam("keyword") String searchKeyword){
        List<Knowledge> knowledges = knowledgeService.getKnowledgeByContent(searchKeyword);
        List<KnowledgeAndKeyword> res = new ArrayList<KnowledgeAndKeyword>();
        Show show = new Show();
       if (knowledges.size() != 0){
           for (int i = 0 ; i < knowledges.size();i++){
               KnowledgeAndKeyword knowledgeAndKeyword = new KnowledgeAndKeyword();
               Knowledge knowledge = knowledges.get(i);
               knowledgeAndKeyword.setKnowledge(knowledge);
               knowledgeAndKeyword.setKeywords(new ArrayList<org.obsidian.scss.entity.Keyword>());
               List<KnowledgeKeyword> kList = knowledgeKeywordService.selectKeywordId(knowledge.getKnowledgeId());
               System.out.println(kList.size()+"!!"+kList.get(0).getKeywordId());
               for (int j=0 ; j < kList.size();j++){
                   org.obsidian.scss.entity.Keyword key = keywordService.selectKeyword(kList.get(j).getKeywordId());
                   knowledgeAndKeyword.getKeywords().add(key);
               }
               res.add(knowledgeAndKeyword);
           }
           show.setData(res);
       }else{
           show.setStatus(0);
           show.setMessage("没有类似的知识条目");
       }
        return show;
    }
    
    @RequestMapping("deleteKnowledgeTag")
    @ResponseBody
    public Show deleteKnowledgeTag(@RequestParam("tagName") String tagName,@RequestParam("knowledgeId") int knowledgeId){
        org.obsidian.scss.entity.Keyword keyword = keywordService.selectByValue(tagName);
        int res = knowledgeKeywordService.selectKeywordIdNum(keyword.getKeywordId());
        if (res == 0){
          keywordService.deleteByKeywordId(keyword.getKeywordId());
          knowledgeKeywordService.delete(keyword.getKeywordId(),knowledgeId);
        }else{
            knowledgeKeywordService.delete(keyword.getKeywordId(),knowledgeId);
        }
        Show show = new Show();
        show.setMessage("删除成功");
        return show;
    }
    
    @RequestMapping("addKnowledge")
    @ResponseBody
    public Show addKnowledge(@RequestParam("question") String ques,
                             @RequestParam("ans") String ans,
                             @RequestParam("tag") String tag,
                             @RequestParam("level") int level){
        List<String> keyList = new ArrayList<String>();
        String [] arr = tag.split("\\s+");
        for(String ss : arr){
            System.out.println("!!!!!"+ss);
            keyList.add(ss);
        }
        int res = knowledgeService.addKnowledgeAndKey(ques,ans,level,1,0,keyList);
        Show show = new Show();
        if(res ==0){
            show.setStatus(0);
            show.setMessage("插入失败!");
        }
        return show;
    }
    
}

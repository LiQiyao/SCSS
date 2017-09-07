package org.obsidian.scss.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    private KnowledgeService knowledgeService;
    @Autowired
    private KnowledgeKeywordService knowledgeKeywordService;
    @Autowired
    private  KeywordService keywordService;

    
    @RequestMapping("knowledgeSearch")
    @ResponseBody
    public Show knowledgeManagement(@RequestParam(value = "keyword",defaultValue = "") String searchKeyword){
        List<Knowledge> knowledges;
        if (searchKeyword.equals("")){
            knowledges = knowledgeService.selectKnowledge();
        }else{
            knowledges = knowledgeService.selectKnowledgeBySearchName(searchKeyword);
        }
        List<KnowledgeAndKeyword> res = new ArrayList<KnowledgeAndKeyword>();
        System.out.println(knowledges.size()+"             cjncjncjcncjnc" + searchKeyword);
        Show show = new Show();
       if (knowledges.size() != 0){
           for (int i = 0 ; i < knowledges.size();i++){
               KnowledgeAndKeyword knowledgeAndKeyword = new KnowledgeAndKeyword();
               Knowledge knowledge = knowledges.get(i);
               knowledgeAndKeyword.setKnowledge(knowledge);
               knowledgeAndKeyword.setKeywords(new ArrayList<org.obsidian.scss.entity.Keyword>());
               List<KnowledgeKeyword> kList = knowledgeKeywordService.selectKeywordId(knowledge.getKnowledgeId());
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
    
    @RequestMapping("addKnowledgeTag")
    @ResponseBody
    public Show addKnowledgeTag(@RequestParam("tagName") String tagName,@RequestParam("knowledgeId") int knowledgeId){
        Show show = new Show();
        int flag = 0;
        org.obsidian.scss.entity.Keyword keyword = keywordService.selectByValue(tagName);
        if (keyword==null) {
                KnowledgeKeyword knowledgeKeyword = new KnowledgeKeyword();
                knowledgeKeyword.setKnowledgeId(knowledgeId);
                keywordService.insertKeyword(tagName);
                int keyId = keywordService.selectByValue(tagName).getKeywordId();
                knowledgeKeyword.setKeywordId(keyId);
                flag = knowledgeKeywordService.insert(knowledgeKeyword);
            }else {
                KnowledgeKeyword knowledgeKeyword = new KnowledgeKeyword();
                knowledgeKeyword.setKnowledgeId(knowledgeId);
                int keyId = keywordService.selectByValue(tagName).getKeywordId();
                knowledgeKeyword.setKeywordId(keyId);
                flag = knowledgeKeywordService.insert(knowledgeKeyword);
            }
        if (flag==0){
            show.setStatus(0);
            show.setMessage("添加失败");
        }else{
            show.setData(keywordService.selectByValue(tagName));
        }
        return show;
    }
    
    @RequestMapping("deleteKnowledgeTag")
    @ResponseBody
    public Show deleteKnowledgeTag(@RequestParam("tagName") String tagName,@RequestParam("knowledgeId") int knowledgeId){
        org.obsidian.scss.entity.Keyword keyword = keywordService.selectByValue(tagName);
        Show show = new Show();
        if (keyword!=null){
            int res = knowledgeKeywordService.selectKeywordIdNum(keyword.getKeywordId());
            System.out.println(res);
            if (res == 1){
                knowledgeKeywordService.delete(keyword.getKeywordId(),knowledgeId);
                keywordService.deleteByKeywordId(keyword.getKeywordId());
            }else{
                knowledgeKeywordService.delete(keyword.getKeywordId(),knowledgeId);
            }
        }else{
            show.setStatus(0);
            show.setMessage("删除失败");
        }
           
       
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
        }else{
            Knowledge knowledge = knowledgeService.selectByContent(ques);
            List<org.obsidian.scss.entity.Keyword> keywords = new ArrayList<org.obsidian.scss.entity.Keyword>();
            for (int i=0;i<keyList.size();i++){
                keywords.add(keywordService.selectByValue(keyList.get(i)));
            }
            knowledge.setKeywordList(keywords);
            show.setData(knowledge);
        }
        return show;
    }
    @RequestMapping("updateKnowledge")
    @ResponseBody
    public Show updateKnowledge(@RequestParam("knowledge") String knowledgeStr){
        Show show = new Show();
        Gson gson = new Gson();
        System.out.println(knowledgeStr);
        Knowledge knowledge = gson.fromJson(knowledgeStr,new TypeToken<Knowledge>(){}.getType());
        int res = knowledgeService.updateKnowledge(knowledge);
        if (res == 0){
            show.setMessage("更新失败");
            show.setStatus(0);
        }
        return show;
    }
    
    
    
    @RequestMapping("deleteKnowledge")
    @ResponseBody
    public Show deleteKnowledge(@RequestParam("knowledgeId") int id){
        Show show = new Show();
        int res = knowledgeService.removeKnowledge(id);
        if (res == 0){
            show.setStatus(0);
            show.setMessage("操作成功");
        }
        return show;
    }
}

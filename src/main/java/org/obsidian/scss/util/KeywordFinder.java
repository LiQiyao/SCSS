package org.obsidian.scss.util;

import org.obsidian.scss.entity.Flag;
import org.obsidian.scss.entity.Keyword;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Lee on 2017/7/9.
 * 根据内容用字典树匹配关键词
 */
public class KeywordFinder {

    public static List<Keyword> findKeywordInContent(String content, Trie trie){
        List<Keyword> keywordList = new ArrayList<Keyword>();
        Set<String> stringSet = new HashSet<String>();
        Keyword keyword;
        int len = content.length();
        for (int i = 0; i < len; i++){
            for (int j =i + 1; j <= len; j++){
                stringSet.add(content.substring(i, j));
            }
        }
        for (String s : stringSet){
            if (trie.search(s) != null){
                keyword = new Keyword(trie.search(s), s);
                keywordList.add(keyword);
            }
        }
        return keywordList;
    }

    public static List<Flag> findFlagInContent(String content,Trie trie){
        List<Flag> flagList = new ArrayList<Flag>();
        Set<String> stringSet = new HashSet<String>();
        int len = content.length();
        for(int i=0;i<len;i++){
            for(int j=i;j<=len;j++){
                stringSet.add(content.substring(i,j));
            }
        }
        for(String s:stringSet){
            if(trie.search(s) != null){
                Flag flag = new Flag();
                flag.setFlagId(trie.search(s));
                flag.setName(s);
                flagList.add(flag);
            }
        }
        return flagList;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("房地产", 1);
        trie.insert("房产", 2);
        trie.insert("买房", 3);
        trie.insert("售楼部", 4);
        trie.insert("钱", 5);
        trie.insert("平方", 6);
        trie.insert("房价", 7);
        String s = "我要买房价买房，售楼部在哪里啊，多少钱一平方啊？";
        System.out.println(findKeywordInContent(s, new Trie()));
    }
}

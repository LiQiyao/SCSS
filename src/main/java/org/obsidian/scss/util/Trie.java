package org.obsidian.scss.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2017/7/9.
 * 字典树，存储与知识库对应的关键词，以及关键词编号
 */
public class Trie {

    private TrieNode root;

    public Trie(){
        root = new TrieNode();
        root.wordEnd = false;
    }

    public void insert(String keyWord, int keyId){
        TrieNode node = root;
        for (int i = 0; i < keyWord.length(); i++){
            Character character = keyWord.charAt(i);
            if (!node.children.containsKey(character)){
                node.children.put(character, new TrieNode(character, keyId));
            }
            node = node.children.get(character);
        }
        node.wordEnd = true;
    }

    public Integer search(String keyName){
        TrieNode node = root;
        Integer keyId = null;
        for (int i = 0; i < keyName.length(); i++){
            Character character = keyName.charAt(i);
            if (!node.children.containsKey(character)){
                return null;
            }
            node = node.children.get(character);
        }
        if (node.wordEnd){
            keyId =  node.keyId;
        }
        return keyId;
    }

    public static void main(String[] args) {
        /*Trie trie = new Trie();
        trie.insert("房地产", 1);
        trie.insert("房产", 2);
        trie.insert("买房", 3);
        trie.insert("售楼部", 4);
        trie.insert("钱", 5);
        trie.insert("平方", 6);
        trie.insert("房价", 7);
        String s = "我要买房价，售楼部在哪里啊，多少钱一平方啊";
        List<KnowledgeMap> knowledgeMapList = new ArrayList<KnowledgeMap>();
        int len = s.length();
        for (int i = 0; i < len; i++){
            for (int j =i + 1; j <= len; j++){
                if (trie.search(s.substring(i, j)) != null){
                    System.out.println(trie.search(s.substring(i, j)));
                }
            }
        }*/
    }
}

package org.obsidian.scss.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lee on 2017/7/9.
 * 字典树节点
 */
public class TrieNode {

    Map<Character, TrieNode> children;

    boolean wordEnd;

    Integer keyId;

    Character character;

    public TrieNode(Character character, Integer keyId){
        this.children = new HashMap<Character, TrieNode>();
        this.wordEnd = false;
        this.keyId = keyId;
        this.character = character;
    }

    public TrieNode(){
        this.children = new HashMap<Character, TrieNode>();
        this.wordEnd = false;
    }
}

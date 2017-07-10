package org.obsidian.scss.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2017/7/9.
 * 根据内容用字典树匹配关键词
 */
public class KeyFinder {

/*    public static List<Key> findKeyInContent(String content, Trie trie){
        List<Key> keyList = new ArrayList<Key>();
        Key key;
        int len = content.length();
        for (int i = 0; i < len; i++){
            for (int j =i + 1; j <= len; j++){

                if (trie.search(content.substring(i, j)) != null){
                    key = new Key(trie.search(content.substring(i, j)), content.substring(i, j));
                    System.out.println("发现关键词：" + key.getKeyWord() + "关键词编号：" + key.getKeyId());
                    keyList.add(key);
                }
            }
        }
        return keyList;
    }*/
}

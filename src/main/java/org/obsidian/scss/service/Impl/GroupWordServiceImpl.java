package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.GroupWordMapper;
import org.obsidian.scss.entity.GroupWord;
import org.obsidian.scss.entity.ServiceGroup;
import org.obsidian.scss.service.GroupWordService;
import org.obsidian.scss.util.Trie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lee on 2017/7/15.
 */
public class GroupWordServiceImpl implements GroupWordService {

    @Autowired
    private GroupWordMapper groupWordMapper;

    @Transactional
    public Trie getTrie() {
        Trie trie = new Trie();
        List<GroupWord> groupWordList = groupWordMapper.selectAll();
        for (GroupWord g : groupWordList){
            trie.insert(g.getWord(),g.getGroupId());
        }
        return trie;
    }

    public int getServiceGroupIdByContent(String content) {
        Trie trie = getTrie();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<String> stringList = new ArrayList<String>();
        int len = content.length();
        for (int i = 0; i < len; i++){
            for (int j =i + 1; j <= len; j++){
                stringList.add(content.substring(i, j));
            }
        }
        for (String s : stringList){
            if (trie.search(s) != null){
                if (map.get(trie.search(s)) == null){
                    map.put(trie.search(s), 1);
                } else {
                    map.put(trie.search(s), 1 + map.get(trie.search(s))) ;
                }
            }
        }
        return 0;
    }
}

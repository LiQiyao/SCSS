package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.GroupWordMapper;
import org.obsidian.scss.entity.GroupWord;
import org.obsidian.scss.entity.GroupWordExample;
import org.obsidian.scss.entity.ServiceGroup;
import org.obsidian.scss.service.GroupWordService;
import org.obsidian.scss.util.Trie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lee on 2017/7/15.
 */
@Service
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

    @Transactional
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
        int maxHeat = 1;
        int maxId = 0;
        for (String s : stringList){
            if (trie.search(s) != null){
                if (map.get(trie.search(s)) == null){
                    map.put(trie.search(s), 1);
                } else {
                    if (maxHeat < 1 + map.get(trie.search(s))){
                        maxHeat = 1 + map.get(trie.search(s));
                        maxId = trie.search(s);
                    }
                    map.put(trie.search(s), 1 + map.get(trie.search(s)));
                }
            }
        }
        return maxId;
    }

    /**
     * create By Cjn
     * @param groupWord
     * @return
     */
    @Transactional
    public int insertGroupWord(GroupWord groupWord){
        return groupWordMapper.insert(groupWord);
    }

    /**
     * Create By cjn
     * @param groupWord
     * @return
     */
    @Transactional
    public int deleteGroupWord(GroupWord groupWord) {
        GroupWordExample example  = new GroupWordExample();
        GroupWordExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupWord.getGroupId());
        criteria.andWordEqualTo(groupWord.getWord());
        return groupWordMapper.deleteByExample(example);
    }
    
    @Transactional
    public List<GroupWord> selectGroupTag(int id) {
        GroupWordExample example = new GroupWordExample();
        GroupWordExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(id);
        return groupWordMapper.selectByExample(example);
    }

    public int selectTagIsExit(int groupId,String content) {
        GroupWordExample example = new GroupWordExample();
        GroupWordExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        criteria.andWordEqualTo(content);
        List<GroupWord> list = groupWordMapper.selectByExample(example);
        if (list !=null && list.size() != 0 ){
            return 1;
        }else{
            return 0;
        }
       
    }
}

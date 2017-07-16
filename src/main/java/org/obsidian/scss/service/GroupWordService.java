package org.obsidian.scss.service;

import org.obsidian.scss.entity.GroupWord;
import org.obsidian.scss.entity.ServiceGroup;
import org.obsidian.scss.util.Trie;

import java.security.acl.Group;

/**
 * Created by Lee on 2017/7/15.
 */
public interface GroupWordService {

    Trie getTrie();

    int getServiceGroupIdByContent(String content);
    /**
     * Create By Cjn
     */
    int insertGroupWord(GroupWord groupWord);
    int deleteGroupWord(GroupWord groupWord);
}

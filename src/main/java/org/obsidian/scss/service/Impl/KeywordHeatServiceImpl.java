package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.KeywordHeatMapper;
import org.obsidian.scss.entity.KeywordAndHeat;
import org.obsidian.scss.entity.KeywordHeat;
import org.obsidian.scss.service.KeywordHeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hp on 2017/7/15.
 */
@Service
public class KeywordHeatServiceImpl implements KeywordHeatService{
    @Autowired
    KeywordHeatMapper keywordHeatMapper;
    public List<KeywordAndHeat> getHeatWord() {
        return keywordHeatMapper.getDayHeat(System.currentTimeMillis());
    }
}

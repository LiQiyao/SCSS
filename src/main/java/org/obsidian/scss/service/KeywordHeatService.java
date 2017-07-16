package org.obsidian.scss.service;

import org.obsidian.scss.entity.KeywordAndHeat;

import java.util.List;

/**
 * Created by hp on 2017/7/15.
 */
public interface KeywordHeatService {
    List<KeywordAndHeat> getHeatWord();
}

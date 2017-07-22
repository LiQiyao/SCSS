package org.obsidian.scss.service;

import java.util.List;
import org.obsidian.scss.entity.AdvFlag;

/**
 * Created by hp on 2017/7/18.
 */
public interface AdvFlagService {
    List<AdvFlag> getFlagId(int advId);
    int insertAdvFlag(int advId,int flagId);
    int selectIsExit(int advId,int flagId);
    int deleteAdvFlag(int advId);
    int deleteAdvOneFlag(int advId,int flagId);
}

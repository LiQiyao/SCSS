package org.obsidian.scss.service.Impl;

import org.obsidian.scss.bean.IdList;
import org.obsidian.scss.dao.AdvFlagMapper;
import org.obsidian.scss.entity.AdvFlag;
import org.obsidian.scss.entity.AdvFlagExample;
import org.obsidian.scss.service.AdvFlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hp on 2017/7/18.
 */
@Service
public class AdvFlagServiceImpl implements AdvFlagService {
    @Autowired
    private AdvFlagMapper advFlagMapper;
    
    @Transactional
    public List<AdvFlag> getFlagId(int advId) {
        AdvFlagExample example = new AdvFlagExample();
        AdvFlagExample.Criteria criteria = example.createCriteria();
        criteria.andAdvIdEqualTo(advId);
        List<AdvFlag> list = advFlagMapper.selectByExample(example);
        return list;
    }

    @Transactional
    public int insertAdvFlag(int advId, int flagId) {
        int res = selectIsExit(advId,flagId);
        if (res == 0){
            AdvFlag advFlag = new AdvFlag();
            advFlag.setAdvId(advId);
            advFlag.setFlagId(flagId);
            System.out.println("!"+advId+"!"+flagId);
            advFlagMapper.insert(advFlag);
        }
        return 1;
    }
    
    @Transactional
    public int selectIsExit(int advId, int flagId) {
       AdvFlagExample example = new AdvFlagExample();
       AdvFlagExample.Criteria criteria = example.createCriteria();
       criteria.andAdvIdEqualTo(advId);
       criteria.andFlagIdEqualTo(flagId);
        return advFlagMapper.selectByExample(example).size();
    }

    @Transactional
    public int deleteAdvFlag(int advId) {
        AdvFlagExample example = new AdvFlagExample();
        AdvFlagExample.Criteria criteria = example.createCriteria();
        criteria.andAdvIdEqualTo(advId);
        return advFlagMapper.deleteByExample(example);
    }

    @Transactional
    public int deleteAdvOneFlag(int advId, int flagId) {
        AdvFlagExample example = new AdvFlagExample();
        AdvFlagExample.Criteria criteria = example.createCriteria();
        criteria.andAdvIdEqualTo(advId);
        criteria.andFlagIdEqualTo(flagId);
        return advFlagMapper.deleteByExample(example);
    }
}

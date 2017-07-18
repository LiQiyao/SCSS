package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.FlagMapper;
import org.obsidian.scss.entity.Flag;
import org.obsidian.scss.entity.FlagExample;
import org.obsidian.scss.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
@Service
public class FlagServiceImpl implements FlagService {

    @Autowired
    private FlagMapper flagMapper;

    /**
     * 新增标签
     * @param name
     * @return
     */
    @Transactional
    public int insertFlag(String name) {
        Flag flag = new Flag();
        flag.setName(name);
        return flagMapper.insertSelective(flag);
    }

    /**
     * 删除标签
     * @param flagId
     * @return
     */
    @Transactional
    public int deleteFlag(int flagId) {
        return flagMapper.deleteFlag(flagId);
    }

    /**
     * 更新标签
     * @param flagId
     * @param name
     * @return
     */
    @Transactional
    public int updateFlag(int flagId, String name) {
        Flag flag = new Flag();
        flag.setFlagId(flagId);
        flag.setName(name);
        return flagMapper.updateFlag(flag);
    }

    /**
     * 查询标签
     * @param flagId
     * @return
     */
    @Transactional
    public String selectName(int flagId){
        String name = flagMapper.selectName(flagId);
        if(name.length() <= 0 || name.equals("")){
            return null;
        }
        return name;
    }

    /**
     * 按标签名查询标签ID
     * @param name
     * @return
     */
    @Transactional
    public int selectFlagId(String name) {
        Object object = flagMapper.selectFlagId(name);
        if(object == null){
            return 0;
        }
        return (Integer)object;
    }

    /**
     * 按客户ID查询所有标签
     * @param clientId
     * @return
     */
    @Transactional
    public List<Flag> selectAllFlag(int clientId) {
        List<Flag> list = flagMapper.selectAllFlag(clientId);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list;
    }

    @Transactional
    public List<Flag> selectAllUnusedFlag(int clientId){
        List<Flag> list = flagMapper.selectAllUnusedFlag(clientId);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list;
    }

    public Flag selectAdv(int flagId) {
        FlagExample example = new FlagExample();
        FlagExample.Criteria criteria = example.createCriteria();
        criteria.andFlagIdEqualTo(flagId);
        return flagMapper.selectByExample(example).get(0);
    }
}

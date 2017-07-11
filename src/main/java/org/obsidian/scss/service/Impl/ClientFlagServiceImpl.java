package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.ClientFlagMapper;
import org.obsidian.scss.entity.ClientFlag;
import org.obsidian.scss.service.ClientFlagService;
import org.obsidian.scss.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/7/11.
 */
@Service
public class ClientFlagServiceImpl implements ClientFlagService {

    @Autowired
    private ClientFlagMapper clientFlagMapper;

    @Autowired
    private FlagService flagService;

    @Transactional
    public int insertClientFlag(int clientId, String name) {
        int selectSum = flagService.selectFlagId(name);
        if(selectSum == 0){
            flagService.insertFlag(name);
        }
        int flagId = flagService.selectFlagId(name);
        ClientFlag clientFlag = new ClientFlag();
        clientFlag.setClientId(clientId);
        clientFlag.setFlagId(flagId);
        int insertSum = clientFlagMapper.insertSelective(clientFlag);
        return insertSum;
    }

    @Transactional
    public int deleteClientFlag(int clientId,int flagId){
        int deleteSum = clientFlagMapper.deleteClientFlag(clientId,flagId);
        return deleteSum;
    }
}

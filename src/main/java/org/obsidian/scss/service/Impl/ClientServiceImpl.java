package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.ClientMapper;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.ClientAndImlInfo;
import org.obsidian.scss.entity.ClientExample;
import org.obsidian.scss.entity.Flag;
import org.obsidian.scss.service.ClientFlagService;
import org.obsidian.scss.service.ClientService;
import org.obsidian.scss.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientFlagService clientFlagService;

    @Autowired
    private FlagService flagService;

    @Transactional
    public List<Client> selectAllByName(String name) {
        String clientName = "%" + name + "%";
        ClientExample example = new ClientExample();
        example.or().andNameLike(clientName);
        return clientMapper.selectByExample(example);
    }

    /**
     * 新增客户
     * @param name
     * @param address
     * @param email
     * @param telephone
     * @param sex
     * @return
     */
    @Transactional
    public int insertClient(String name,String address,String email,Long telephone,int sex) {
        Client client = new Client();
        client.setName(name);
        client.setAddress(address);
        client.setEmail(email);
        client.setTelephone(telephone);
        client.setSex(sex);
        int insertSum = clientMapper.insert(client);
        return clientMapper.selectLastId();
    }

    /**
     * 删除客户
     * @param clientId
     * @return
     */
    @Transactional
    public int deleteClient(int clientId) {
        int deleteSum = clientMapper.deleteClient(clientId);
        return deleteSum;
    }

    /**
     * 更新客户
     * @param clientId
     * @param name
     * @param address
     * @param email
     * @param telephone
     * @param sex
     * @return
     */
    @Transactional
    public int updateClient(int clientId,String name,String address,String email,Long telephone,int sex) {
        Client client = new Client();
        client.setClientId(clientId);
        client.setName(name);
        client.setAddress(address);
        client.setEmail(email);
        client.setTelephone(telephone);
        client.setSex(sex);
        int updateSum = clientMapper.updateClient(client);
        return updateSum;
    }

    /**
     * 查询客户
     * @param clientId
     * @return
     */
    @Transactional
    public Client selectClientByClientId(int clientId) {
        ClientExample example = new ClientExample();
        example.or().andClientIdEqualTo(clientId);
        List<Client> list = clientMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list.get(0);
    }

    /**
     * 新增客户标签
     * @param clientId
     * @param flagName
     * @return
     */
    @Transactional
    public int addFlag(int clientId,String flagName){
        int addSum = clientFlagService.insertClientFlag(clientId,flagName);
        return addSum;
    }

    /***
     * 删除客户标签
     * @param clientId
     * @param flagId
     * @return
     */
    @Transactional
    public int deleteFlag(int clientId,int flagId){
        int deleteSum = clientFlagService.deleteClientFlag(clientId,flagId);
        return deleteSum;
    }

    /**
     * 查询客户所有标签
     * @param clientId
     * @return
     */
    @Transactional
    public List<Flag> selectAllFlag(int clientId) {
        List<Flag> list = flagService.selectAllFlag(clientId);
        if( list == null || list.size() == 0){ //修改了一波顺序原先的如果为空就会出错
            return null;
        }
        return list;
    }

    @Transactional
    public List<Flag> selectAllUnusedFlag(int clientId){
        List<Flag> list = flagService.selectAllUnusedFlag(clientId);
        if(list == null || list.size() == 0){
            return null;
        }
        return list;
    }
    @Transactional
    public List<Client> selectAllClient() {
        ClientExample clientExample = new ClientExample();
        return  clientMapper.selectByExample(clientExample);
    }

    public List<ClientAndImlInfo> selectClientAndImlInfo(int clientId) {
        List<ClientAndImlInfo> list = clientMapper.selectClientDetail(clientId);
        if(list == null || list.size() == 0){
            return null;
        }
        return list;
    }
}

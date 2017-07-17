package org.obsidian.scss.service;

import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.Flag;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
public interface ClientService {
    List<Client> selectAllByName(String name);

    int insertClient(String name,String address,String email,Long telephone,int sex);

    int deleteClient(int clientId);

    int updateClient(int clientId,String name,String address,String email,Long telephone,int sex);

    Client selectClientByClientId(int clientId);

    int addFlag(int clientId,String flagName);

    int deleteFlag(int clientId,int flagId);

    List<Flag> selectAllFlag(int clientId);

    List<Flag> selectAllUnusedFlag(int clientId);

    /**
     * creaete By cjn
     */
    List<Client> selectAllClient();
}

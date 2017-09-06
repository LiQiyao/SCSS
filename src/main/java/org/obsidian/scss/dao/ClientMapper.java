package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.ClientAndImlInfo;
import org.obsidian.scss.entity.ClientExample;

import java.util.List;

public interface ClientMapper {
    int updateClient(Client client);

    int deleteClient(int clientId);

    long countByExample(ClientExample example);

    int deleteByExample(ClientExample example);

    int insert(Client record);

    int insertSelective(Client record);

    List<Client> selectByExample(ClientExample example);

    int updateByExampleSelective(@Param("record") Client record, @Param("example") ClientExample example);

    int updateByExample(@Param("record") Client record, @Param("example") ClientExample example);

    int selectLastId();
    
    List<ClientAndImlInfo> selectClientDetail(@Param("clientId")int  clientId);
}
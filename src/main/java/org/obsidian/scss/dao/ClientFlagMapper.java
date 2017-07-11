package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.ClientFlag;
import org.obsidian.scss.entity.ClientFlagExample;

import java.util.List;

public interface ClientFlagMapper {
    int deleteClientFlag(@Param("clientId") int clientId,@Param("flagId") int flagId);

    long countByExample(ClientFlagExample example);

    int deleteByExample(ClientFlagExample example);

    int insert(ClientFlag record);

    int insertSelective(ClientFlag record);

    List<ClientFlag> selectByExample(ClientFlagExample example);

    int updateByExampleSelective(@Param("record") ClientFlag record, @Param("example") ClientFlagExample example);

    int updateByExample(@Param("record") ClientFlag record, @Param("example") ClientFlagExample example);
}
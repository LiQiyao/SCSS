package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.ClientFlag;
import org.obsidian.scss.entity.ClientFlagExample;

public interface ClientFlagMapper {
    long countByExample(ClientFlagExample example);

    int deleteByExample(ClientFlagExample example);

    int insert(ClientFlag record);

    int insertSelective(ClientFlag record);

    List<ClientFlag> selectByExample(ClientFlagExample example);

    int updateByExampleSelective(@Param("record") ClientFlag record, @Param("example") ClientFlagExample example);

    int updateByExample(@Param("record") ClientFlag record, @Param("example") ClientFlagExample example);
}
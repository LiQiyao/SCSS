package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.JoinUp;
import org.obsidian.scss.entity.JoinUpExample;

public interface JoinUpMapper {
    long countByExample(JoinUpExample example);

    int deleteByExample(JoinUpExample example);

    int insert(JoinUp record);

    int insertSelective(JoinUp record);

    List<JoinUp> selectByExample(JoinUpExample example);

    int updateByExampleSelective(@Param("record") JoinUp record, @Param("example") JoinUpExample example);

    int updateByExample(@Param("record") JoinUp record, @Param("example") JoinUpExample example);

    List<JoinUp> selectByClientId(int clientId);

    JoinUp selectByRecord(@Param("accessId") int accessId,@Param("account") String account);

    JoinUp selectLastJoinByClientId(int clientId);
}
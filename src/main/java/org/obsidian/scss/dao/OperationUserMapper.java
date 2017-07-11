package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.OperationUser;
import org.obsidian.scss.entity.OperationUserExample;

import java.util.List;

public interface OperationUserMapper {
    int deleteOperationUser(int userId);

    int updateOperationUser(OperationUser operationUser);

    List<OperationUser> selectUserIdByUsername(String username);

    String selectUsernameByUserId(int userId);

    long countByExample(OperationUserExample example);

    int deleteByExample(OperationUserExample example);

    int insert(OperationUser record);

    int insertSelective(OperationUser record);

    List<OperationUser> selectByExample(OperationUserExample example);

    int updateByExampleSelective(@Param("record") OperationUser record, @Param("example") OperationUserExample example);

    int updateByExample(@Param("record") OperationUser record, @Param("example") OperationUserExample example);
}
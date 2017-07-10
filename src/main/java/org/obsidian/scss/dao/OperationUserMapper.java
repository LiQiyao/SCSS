package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.OperationUser;
import org.obsidian.scss.entity.OperationUserExample;

public interface OperationUserMapper {
    long countByExample(OperationUserExample example);

    int deleteByExample(OperationUserExample example);

    int insert(OperationUser record);

    int insertSelective(OperationUser record);

    List<OperationUser> selectByExample(OperationUserExample example);

    int updateByExampleSelective(@Param("record") OperationUser record, @Param("example") OperationUserExample example);

    int updateByExample(@Param("record") OperationUser record, @Param("example") OperationUserExample example);
}
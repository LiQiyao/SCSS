package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.JoinUp;
import org.obsidian.scss.entity.JoinUpExample;

import java.util.List;

public interface JoinUpMapper {
    int updateJoinUp(@Param("clientId")int clientId,@Param("qq") String wx,@Param("wx") String qq,@Param("weibo") String weibo,@Param("taobao") String taobao,@Param("alipay") String alipay);

    long countByExample(JoinUpExample example);

    int deleteByExample(JoinUpExample example);

    int insert(JoinUp record);

    int insertSelective(JoinUp record);

    List<JoinUp> selectByExample(JoinUpExample example);

    int updateByExampleSelective(@Param("record") JoinUp record, @Param("example") JoinUpExample example);

    int updateByExample(@Param("record") JoinUp record, @Param("example") JoinUpExample example);

    List<JoinUp> selectByClientId(int clientId);

    List<JoinUp> selectByRecord(@Param("accessId") int accessId,@Param("account") String account);

    JoinUp selectLastJoinByClientId(int clientId);
}
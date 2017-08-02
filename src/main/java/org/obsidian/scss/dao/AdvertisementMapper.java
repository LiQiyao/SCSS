package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.Advertisement;
import org.obsidian.scss.entity.AdvertisementExample;
import org.obsidian.scss.entity.Client;

import java.util.List;

public interface AdvertisementMapper {
    int countByExample(AdvertisementExample example);

    int deleteByExample(AdvertisementExample example);

    int deleteByPrimaryKey(Integer advId);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    List<Advertisement> selectByExample(AdvertisementExample example);

    Advertisement selectByPrimaryKey(Integer advId);

    int updateByExampleSelective(@Param("record") Advertisement record, @Param("example") AdvertisementExample example);

    int updateByExample(@Param("record") Advertisement record, @Param("example") AdvertisementExample example);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
    /**
     * Create By cjn
     */
    int countAdv();
    int insertAdv(@Param("content") String  content);
    List<Advertisement> selectId(@Param("content") String  content);
    List<Client> selectAdvClient(@Param("advId") int  advId);
}
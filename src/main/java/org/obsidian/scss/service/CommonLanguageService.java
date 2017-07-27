package org.obsidian.scss.service;

import org.obsidian.scss.entity.CommonLanguage;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public interface CommonLanguageService {

    int insertCommonLanguage(String content);

    int insertServiceLanguage(int serviceId,String content);

    int deleteCommonLanguage(int commonLanguageId);

    int addCommonLanguageFrequency(int commonLanguageId);

    int updateCommonLanguage(int commonLanguageId,String content);

    List<CommonLanguage> selectAllCommonLanguage();

    List<CommonLanguage> selectAllServiceLanguage(int serviceId);

    List<CommonLanguage> selectAllServiceAndCommonLanguage(int serviceId);
}

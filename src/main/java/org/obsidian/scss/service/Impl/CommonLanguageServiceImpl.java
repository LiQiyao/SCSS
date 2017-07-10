package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.CommonLanguageMapper;
import org.obsidian.scss.entity.CommonLanguage;
import org.obsidian.scss.entity.CommonLanguageExample;
import org.obsidian.scss.service.CommonLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Administrator on 2017/7/10.
 */
@Service
public class CommonLanguageServiceImpl implements CommonLanguageService {

    @Autowired
    private CommonLanguageMapper commonLanguageMapper;

    /**
     * 新增公共常用语
     * @param content
     * @return
     */
    @Transactional
    public int insertCommonLanguage(String content){
        CommonLanguage commonLanguage = new CommonLanguage();
        commonLanguage.setContent(content);
        commonLanguage.setFrequency(0);
        int insertSum = commonLanguageMapper.insertSelective(commonLanguage);
        return insertSum;
    }

    /**
     * 新增客服个人常用语
     * @param serviceId
     * @param content
     * @return
     */
    @Transactional
    public int insertServiceLanguage(int serviceId, String content) {
        CommonLanguage commonLanguage = new CommonLanguage();
        commonLanguage.setFrequency(0);
        commonLanguage.setContent(content);
        commonLanguage.setServiceId(serviceId);
        int insertSum = commonLanguageMapper.insertSelective(commonLanguage);
        return insertSum;
    }

    /**
     * 删除常用语
     * @param commonLanguageId
     * @return
     */
    @Transactional
    public int deleteCommonLanguage(int commonLanguageId){
        int deleteSum = commonLanguageMapper.deleteCommonLanguage(commonLanguageId);
        return deleteSum;
    }

    /**
     * 增加常用语频率
     * @param commonLanguageId
     * @return
     */
    @Transactional
    public int addCommonLanguageFrequency(int commonLanguageId) {
        int updateSum = commonLanguageMapper.addCommonLanguageFrequency(commonLanguageId);
        return updateSum;
    }

    /**
     * 修改常用语内容
     * @param commonLanguageId
     * @param content
     * @return
     */
    public int updateCommonLanguage(int commonLanguageId, String content) {
        CommonLanguage commonLanguage = new CommonLanguage();
        commonLanguage.setCommonLanguageId(commonLanguageId);
        commonLanguage.setContent(content);
        int updateSum = commonLanguageMapper.updateCommonLanguage(commonLanguage);
        return updateSum;
    }

    /**
     * 查询所有公共常用语
     * @return
     */
    public List<CommonLanguage> selectAllCommonLanguage() {
        List<CommonLanguage> list;
        CommonLanguageExample example = new CommonLanguageExample();
        example.or().andServiceIdIsNull();
        example.setOrderByClause("frequency desc");
        list = commonLanguageMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list;
    }

    /**
     * 查询所有客服个人常用语
     * @param serviceId
     * @return
     */
    public List<CommonLanguage> selectAllServiceLanguage(int serviceId) {
        List<CommonLanguage> list;
        CommonLanguageExample example = new CommonLanguageExample();
        example.or().andServiceIdEqualTo(serviceId);
        example.setOrderByClause("frequency desc");
        list = commonLanguageMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list;
    }
}

package org.obsidian.scss.service.Impl;

import org.obsidian.scss.bean.IdList;
import org.obsidian.scss.dao.AdvertisementMapper;
import org.obsidian.scss.entity.Advertisement;
import org.obsidian.scss.entity.AdvertisementExample;
import org.obsidian.scss.service.AdvFlagService;
import org.obsidian.scss.service.AdvertisementService;
import org.obsidian.scss.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2017/7/15.
 */
@Service
public class AdvertisementImpl implements AdvertisementService {
    @Autowired
    AdvertisementMapper advertisementMapper;
    @Autowired
    FlagService flagService;
    @Autowired
    AdvFlagService advFlagService;
    /**
     * 广告总数
     * @return
     */
    @Transactional
    public int getTotalAdv() {
        return advertisementMapper.countAdv();
    }
    
    @Transactional
    public List<Advertisement> getTotalAdvInfo() {
        AdvertisementExample example = new AdvertisementExample();
        return advertisementMapper.selectByExample(example);
    }

    public int selectByContent(String content) {
        AdvertisementExample example = new AdvertisementExample();
        AdvertisementExample.Criteria criteria = example.createCriteria();
        criteria.andAdvContentEqualTo(content);
        return advertisementMapper.selectByExample(example).size();
    }

    @Transactional
    public int selectId(String content) {
        AdvertisementExample example =new AdvertisementExample();
        AdvertisementExample.Criteria criteria = example.createCriteria();
        criteria.andAdvContentEqualTo(content);
        return advertisementMapper.selectByExample(example).get(0).getAdvId();
    }

    @Transactional
    public int insertAdv(String content, List<String> flagList) {
        System.out.println("!!!!+++"+selectByContent(content));
        if (selectByContent(content) != 0){
            return 0;
        }else{
            Advertisement advertisement = new Advertisement();
            advertisement.setAdvContent(content);
            advertisementMapper.insertAdv(content);
            int advId = selectId(content);
            List<IdList> flagId = new ArrayList<IdList>();
            for (int i = 0; i< flagList.size() ; i++){
                if (flagService.selectFlagId(flagList.get(i)) == 0)
                    System.out.println("!!!!!!!!!"+flagService.insertFlag(flagList.get(i)));
                IdList id = new IdList();
                id.setId(flagService.selectFlagId(flagList.get(i)));
                flagId.add(id);
            }
            System.out.println(advId);
            for (int i = 0;i<flagId.size();i++){
                advFlagService.insertAdvFlag(advId,flagId.get(i).getId());
            }
            
        }
        return 1;
    }

    public int delete(int id) {
        return advertisementMapper.deleteByPrimaryKey(id);
    }

    public int updateAdv(Advertisement advertisement) {
        int res = advertisementMapper.updateByPrimaryKey(advertisement);
        return res;
    }
}

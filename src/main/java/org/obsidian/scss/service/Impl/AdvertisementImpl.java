package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.AdvertisementMapper;
import org.obsidian.scss.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hp on 2017/7/15.
 */
@Service
public class AdvertisementImpl implements AdvertisementService {
    @Autowired
    AdvertisementMapper advertisementMapper;

    /**
     * 广告总数
     * @return
     */
    public int getTotalAdv() {
        return advertisementMapper.countAdv();
    }
}

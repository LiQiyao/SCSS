package org.obsidian.scss.bean;

import java.util.List;
import org.obsidian.scss.entity.Advertisement;
import org.obsidian.scss.entity.Flag;

/**
 * Created by hp on 2017/7/18.
 */
public class AdvertisementAndFlag {
    public Advertisement advertisement;
    public List<Flag> flagList;

    public List<Flag> getFlagList() {
        return flagList;
    }

    public void setFlagList(List<Flag> flagList) {
        this.flagList = flagList;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}

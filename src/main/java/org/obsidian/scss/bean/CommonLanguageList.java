package org.obsidian.scss.bean;

import org.obsidian.scss.entity.CommonLanguage;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
public class CommonLanguageList {
    private List<CommonLanguage> commonLanguageList;

    public CommonLanguageList(List<CommonLanguage> commonLanguageList) {
        this.commonLanguageList = commonLanguageList;
    }

    public CommonLanguageList() {
    }

    @Override
    public String toString() {
        return "CommonLanguageList{" +
                "commonLanguageList=" + commonLanguageList +
                '}';
    }

    public List<CommonLanguage> getCommonLanguageList() {
        return commonLanguageList;
    }

    public void setCommonLanguageList(List<CommonLanguage> commonLanguageList) {
        this.commonLanguageList = commonLanguageList;
    }
}

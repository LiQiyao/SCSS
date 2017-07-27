package org.obsidian.scss.bean;

/**
 * Created by Administrator on 2017/7/24.
 */
public class CommonLanguageClick {
    int commonLanguageId;

    public CommonLanguageClick(int commonLanguageId) {
        this.commonLanguageId = commonLanguageId;
    }

    public CommonLanguageClick() {
    }

    @Override
    public String toString() {
        return "CommonLanguageClick{" +
                "commonLanguageId=" + commonLanguageId +
                '}';
    }

    public int getCommonLanguageId() {
        return commonLanguageId;
    }

    public void setCommonLanguageId(int commonLanguageId) {
        this.commonLanguageId = commonLanguageId;
    }
}

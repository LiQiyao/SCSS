package org.obsidian.scss.bean;

/**
 * Created by Administrator on 2017/7/17.
 */
public class SearchClientByNameReq {

    private String searchWord;

    public SearchClientByNameReq() {
    }

    public SearchClientByNameReq(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public String toString() {
        return "SearchClientByNameReq{" +
                "searchWord='" + searchWord + '\'' +
                '}';
    }
}

package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/19.
 */
public class Notification {
    private String title;

    private String notiContent;

    private long time;


    public Notification() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotiContent() {
        return notiContent;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "title='" + title + '\'' +
                ", notiContent='" + notiContent + '\'' +
                ", time=" + time +
                '}';
    }

    public void setNotiContent(String notiContent) {
        this.notiContent = notiContent;
    }

    public Notification(String title, String notiContent, long time) {
        this.title = title;
        this.notiContent = notiContent;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

package org.obsidian.scss.bean;

/**
 * Created by Cjn on 2017/7/14.
 * 负责HTTP前后端通信的接口
 */

public class Show {
    public Object data;
    public int status = 1;
    public String message = "OK";

    public Object getData() {
        return data;
    }

    public void setData(Object date) {
        this.data = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package org.obsidian.scss.bean;


/**
 * Created by Lee on 2017/7/7.
 */
public class Message<T> {

    private String type;

    private T content;

    public Message(T content){
        this.content = content;
        type = content.getClass().getName();
        type = type.substring(type.lastIndexOf(".") + 1);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

}

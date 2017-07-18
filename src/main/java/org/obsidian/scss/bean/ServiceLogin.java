package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/17.
 */
public class ServiceLogin {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ServiceLogin(String token) {
        this.token = token;
    }
}

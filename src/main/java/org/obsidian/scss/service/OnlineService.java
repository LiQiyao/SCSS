package org.obsidian.scss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lee on 2017/7/17.
 */
@Service
public class OnlineService {

    @Autowired
    private ServiceGroupPeople serviceGroupPeople;

    private Map<String, String> map = new HashMap<String, String>();

    public void online(String employeeId, HttpServletResponse resp){
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String token = base64Encoder.encode(md5.digest(employeeId.getBytes()));
            Cookie cookie = new Cookie("token", token);
            resp.addCookie(cookie);
            System.out.println("登录者token:" + token + " employeeId：" + employeeId);
            map.put(token, employeeId);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getEmployeeId(String md5Str){
        return map.get(md5Str);
    }

    public Map<String, String> getMap() {
        return map;
    }
}

package org.obsidian.scss.service;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lee on 2017/7/17.
 */
@Service
public class OnlineService {

    private Map<String, String> map = new HashMap<String, String>();

    public void online(String employeeId){
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String token = base64Encoder.encode(md5.digest(employeeId.getBytes()));
            Cookie cookie = new Cookie("token", token);
            System.out.println("token:" + token);
            map.put(token, employeeId);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getEmployeeId(String md5Str){
        return map.get(md5Str);
    }
}

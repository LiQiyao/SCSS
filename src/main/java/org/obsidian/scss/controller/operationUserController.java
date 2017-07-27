package org.obsidian.scss.controller;

import org.obsidian.scss.bean.Show;
import org.obsidian.scss.entity.OperationUser;
import org.obsidian.scss.service.OperationUserService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by hp on 2017/7/25.
 */
@Controller
public class operationUserController {
    
    @Autowired
    private OperationUserService operationUserService;
    
    @RequestMapping("opUserLogin")
    @ResponseBody
    public Show opLogin(@RequestParam("opId") int oid,@RequestParam("opsd") String opsd,HttpSession session){
        OperationUser res = operationUserService.selectUser(oid,opsd);
        Show show = new Show();
        if (res == null){
            show.setStatus(0);
            show.setMessage("您输入的账号密码有误");
        }else {
            session.setAttribute("uid", oid);
            show.setData(res);
            show.setMessage("欢迎"+res.getUsername());
        }
        return show;
    }
  
    @RequestMapping("opUserLoginOut")
    @ResponseBody
    public Show opLogin(HttpSession session){
        Show show = new Show();
        if (session.getAttribute("uid") !=null)
            session.setAttribute("uid",null);
        show.setMessage("登出成功");
        show.setStatus(1);
        return show;
    }
}

package org.obsidian.scss.controller;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.obsidian.scss.bean.Show;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.ClientAndFlag;
import org.obsidian.scss.entity.Flag;
import org.obsidian.scss.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hp on 2017/7/17.
 */
@Controller
public class ClientController {
    @Autowired
    ClientService clientService;
    
    @RequestMapping("client")
    public String client(Model model){
        List<ClientAndFlag> clientAndFlags = new ArrayList<ClientAndFlag>();
        List<Client> clients = clientService.selectAllClient();
        for (int i = 0 ; i <clients.size();i++){
            ClientAndFlag clientAndFlag = new ClientAndFlag(); 
            clientAndFlag.setClient(clients.get(i));
            if (clientService.selectAllFlag(clients.get(i).getClientId())!=null)
                clientAndFlag.setFlags(clientService.selectAllFlag(clients.get(i).getClientId()));
            clientAndFlags.add(clientAndFlag);
        }
        model.addAttribute("clientFlags",clientAndFlags);
        return "client"; 
    }
    
    @RequestMapping("clientSearchByName")
    @ResponseBody
    public Show clientSearchByName(@RequestParam("searchName") String name){
        List<ClientAndFlag> clientAndFlags = new ArrayList<ClientAndFlag>();
        List<Client> clients = clientService.selectAllClient();
        for (int i = 0 ; i <clients.size();i++){
            boolean flag = false;
            flag = clients.get(i).getName().contains(name);
            ClientAndFlag clientAndFlag = new ClientAndFlag();
            List<Flag> flags = clientService.selectAllFlag(clients.get(i).getClientId());
            if (flags != null){
                for (int j = 0;j<flags.size();j++){
                    if (flags.get(i).getName().contains(name)){
                        flag = true;
                        break;
                    }
                }
            }
            if(flag){
                clientAndFlag.setClient(clients.get(i));
                clientAndFlag.setFlags(flags);
                clientAndFlags.add(clientAndFlag);
            }
          
        }
        Show show = new Show();
        if (clientAndFlags.size()!=0){
            show.setData(clientAndFlags);
        }else{
            show.setStatus(0);
            show.setMessage("未查到对应的姓名或者标签所对应的用户");
        }
        return show;
    }
}
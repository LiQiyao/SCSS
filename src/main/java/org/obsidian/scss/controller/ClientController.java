package org.obsidian.scss.controller;

import java.util.ArrayList;
import java.util.List;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.ClientAndFlag;
import org.obsidian.scss.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            clientAndFlag.setFlags(clientService.selectAllFlag(clients.get(i).getClientId()));
        }
        model.addAttribute(clientAndFlags);
        return "client"; 
    }
}
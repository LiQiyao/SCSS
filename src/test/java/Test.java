import org.junit.runner.RunWith;
import org.obsidian.scss.bean.ClientDetailResp;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.Flag;
import org.obsidian.scss.entity.JoinUp;
import org.obsidian.scss.service.ClientService;
import org.obsidian.scss.service.JoinUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
@Transactional(transactionManager = "transactionManager")
@Rollback(value = false)
public class Test {

    @Autowired
    private ClientService clientService;

    @Autowired
    private JoinUpService joinUpService;

    @org.junit.Test
    public void test(){
        int clientId = 4;
        Client client = clientService.selectClientByClientId(clientId);
        String clientName = client.getName();
        int sex = client.getSex();
        Long phoneNum = client.getTelephone();
        String email = client.getEmail();
        String address = client.getAddress();
        String wx = "";
        String qq = "";
        String weibo = "";
        System.out.println("22222222222222222");
        List<JoinUp> joinUpList = joinUpService.getByClientId(clientId);
        for (JoinUp aJoinUpList : joinUpList) {
            if (aJoinUpList.getAccess().getName().equals("微信")) {
                wx = aJoinUpList.getAccount();
            }
            if (aJoinUpList.getAccess().getName().equals("QQ")) {
                qq = aJoinUpList.getAccount();
            }
            if (aJoinUpList.getAccess().getName().equals("微博")) {
                weibo = aJoinUpList.getAccount();
            }
        }
        List<Flag> flagList = clientService.selectAllFlag(clientId);
        List<String> tagList = new ArrayList<String>();
        if(flagList != null && flagList.size() > 0){
            for(int i=0;i<flagList.size();i++){
                tagList.add(flagList.get(i).getName());
            }
        }
        System.out.println("4444444444444444444");

        List<Flag> unusedFlagList = clientService.selectAllUnusedFlag(clientId);
        List<String> unusedTagList = new ArrayList<String>();
        if(unusedFlagList != null && unusedFlagList.size() > 0){
            for(int i=0;i<unusedFlagList.size();i++){
                unusedTagList.add(unusedFlagList.get(i).getName());
            }
        }
        ClientDetailResp clientDetailResp = new ClientDetailResp(clientId,clientName,sex,phoneNum,email,wx,qq,weibo,address,tagList,unusedTagList);
        System.out.println(clientDetailResp.toString());
    }
}
import org.junit.runner.RunWith;
import org.obsidian.scss.service.ClientService;
import org.obsidian.scss.service.JoinUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
        int clientId = 1;
        String clientName = "chen";
        int sex = 0;
        Long phoneNum = 699L;
        String email = "108490462@qq.com";
        String address = "zhejiag";
        String qq = "1084962";
        String wx = "wx122";
        String weibo = "11331123";
        String taobao = "weigobao";
        String alipay = "1566666";
        clientService.updateClient(clientId,clientName,address,email,phoneNum,sex);
        joinUpService.updateJoinUp(clientId,qq,wx,weibo,taobao,alipay);
    }
}
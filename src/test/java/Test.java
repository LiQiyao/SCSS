import org.junit.runner.RunWith;
import org.obsidian.scss.entity.JoinUp;
import org.obsidian.scss.service.JoinUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
    private JoinUpService joinUpService;

    @org.junit.Test
    public void test(){
        List<JoinUp> list = joinUpService.getByClientId(1);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }
    }
}
import org.junit.runner.RunWith;
import org.obsidian.scss.service.FlagService;
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

    @org.junit.Test
    public void test(){

    }
}
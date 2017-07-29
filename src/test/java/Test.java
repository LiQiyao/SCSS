import org.junit.runner.RunWith;
import org.obsidian.scss.bean.RecommandTags;
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
    private FlagService flagService;

    @org.junit.Test
    public void test(){
        List<String> list = flagService.recommendFlags(1,"这");
        RecommandTags recommandTags = new RecommandTags(1,list);
        System.out.println(recommandTags.toString());
        if(list == null || list.size() == 0){
            System.out.println("aaa");
        }
        else{
            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i));
            }
        }
    }
}
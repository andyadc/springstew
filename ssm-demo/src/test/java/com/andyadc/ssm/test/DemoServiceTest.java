package com.andyadc.ssm.test;

import com.andyadc.ssm.entity.Demo;
import com.andyadc.ssm.mapper.DemoMapper;
import com.andyadc.ssm.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author andy.an
 * @since 2018/9/29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class DemoServiceTest {

    @Autowired
    private DemoMapper demoMapper;
    @Autowired
    private DemoService demoService;

    @Test
    public void testQuery() {

//        List<Demo> demos = demoMapper.selectByTypeList(Arrays.asList(1, 3));
        List<Demo> demos = demoMapper.selectByTypeArray(new Integer[]{1, 2});

        for (Demo demo : demos) {
            System.out.println(Objects.toString(demo));
        }
    }

    @Test
    public void testAdd() {
        Demo demo = new Demo();
        demo.setName("u" + ThreadLocalRandom.current().nextInt());
        demo.setStatus(2);
        demo.setType(2);
        demo.setCreateTime(new Date());
        demo.setUpdateTime(new Date());
        demoService.add(demo);
    }
}

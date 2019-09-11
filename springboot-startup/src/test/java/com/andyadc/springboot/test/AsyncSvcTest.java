package com.andyadc.springboot.test;

import com.andyadc.springboot.service.BizSvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * andy.an
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AsyncSvcTest {

    @Autowired
    private BizSvc bizSvc;

    @Test
    public void testAsync() throws Exception {
        bizSvc.bizNotify();
        Thread.sleep(3 * 1000L);
    }
}

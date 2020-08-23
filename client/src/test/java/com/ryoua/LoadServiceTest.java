package com.ryoua;

import com.ryoua.system.model.CpuLoad;
import com.ryoua.system.model.Load;
import com.ryoua.system.model.MemoryLoad;
import com.ryoua.system.service.LoadService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * * @Author: RyouA
 * * @Date: 2020/8/23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoadServiceTest {
    @Autowired
    private LoadService loadService;

    @Test
    public void cpuLoadTest() throws Exception {
        CpuLoad cpuLoad = loadService.getCpuLoad();
        Assert.assertNotNull(cpuLoad);
        Assert.assertTrue(TestUtils.isAllFieldNull(cpuLoad));
    }

    @Test
    public void memoryLoadTest() throws Exception {
        MemoryLoad memoryLoad = loadService.getMemoryLoad();
        Assert.assertNotNull(memoryLoad);
        Assert.assertTrue(TestUtils.isAllFieldNull(memoryLoad));
    }

    @Test
    public void loadTest() throws Exception {
        Load load = loadService.getLoad();
        Assert.assertNotNull(load);
        Assert.assertTrue(TestUtils.isAllFieldNull(load));
    }
}

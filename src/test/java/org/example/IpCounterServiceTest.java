package org.example;

import ip.counter.IpCounterService;
import org.junit.Test;


public class IpCounterServiceTest {
    @Test
    public void simpleTest() {
        var filepath = getClass().getResource("/testIps").getPath();
        IpCounterService.INSTANCE.countIPs(filepath);
    }
}

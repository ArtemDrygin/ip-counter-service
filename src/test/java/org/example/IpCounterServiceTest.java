package org.example;

import ip.counter.IpCounterService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class IpCounterServiceTest {
    @Test
    public void simpleTest() {
        var filepath = getClass().getResource("/testIps").getPath();
        var uniqueIpsCount = IpCounterService.INSTANCE.countIPs(filepath);

        assertThat(uniqueIpsCount, equalTo(4L));
    }
}

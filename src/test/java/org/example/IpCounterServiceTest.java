package org.example;

import ip.counter.IpCounterService;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class IpCounterServiceTest {
    @Test
    public void simpleTest() throws URISyntaxException {
        var filepath = new File(getClass().getResource("/testIps").toURI()).getAbsolutePath();
        var uniqueIpsCount = IpCounterService.INSTANCE.countIPs(filepath);

        assertThat(uniqueIpsCount, equalTo(4L));
    }
}

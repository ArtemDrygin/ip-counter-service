package ip.counter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Inet4Address;

public class IpCounterService {
    private static final Long MAX_POSSIBLE_IP_COUNT = 256L * 256 * 256 * 256;
    public static final IpCounterService INSTANCE = new IpCounterService();

    private IpCounterService() {
    }

    public Long countIPs(String filepath) {
        var ipCounter = new UniqueIpCounter();

        readFile(filepath, ipCounter);

        return ipCounter.getUniqueIpCount();
    }

    private void readFile(String filepath, UniqueIpCounter counter) {
        var file = new File(filepath);

        if (!file.exists()) {
            throw new RuntimeException("Cannot find a file.");
        }

        try (var reader = new BufferedReader(new FileReader(filepath))) {
            String ipString;
            while (counter.getUniqueIpCount() != MAX_POSSIBLE_IP_COUNT && (ipString = reader.readLine()) != null) {
                var ipInteger = ipStringToInteger(ipString);
                counter.addIp(ipInteger);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer ipStringToInteger(String ip) {
        byte[] bytesIp;
        try {
            bytesIp = Inet4Address.getByName(ip).getAddress();
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("Cannot parse ip string '%s' into integer", ip),
                    e
            );
        }

        return new BigInteger(bytesIp).intValue();
    }
}

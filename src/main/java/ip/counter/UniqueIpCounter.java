package ip.counter;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.util.BitSet;

public class UniqueIpCounter {
    private static final Long MAX_POSSIBLE_IP_COUNT = 256L * 256 * 256 * 256;
    private final BitSet positiveIpSet = new BitSet(Integer.MAX_VALUE);
    private final BitSet negativeIpSet = new BitSet(Integer.MAX_VALUE);
    private long uniqueIpCount = 0L;

    public long getUniqueIpCount() {
        return uniqueIpCount;
    }

    public boolean isMaxUniqueIpCount() {
        return uniqueIpCount == MAX_POSSIBLE_IP_COUNT;
    }

    public void addIp(String ip) {
        addIp(ipStringToInteger(ip));
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

    private void addIp(Integer ip) {
        if (ip >= 0) {
            addIp(ip, positiveIpSet);
        } else {
            addIp(~ip, negativeIpSet);
        }
    }

    private void addIp(Integer ip, BitSet ipSet) {
        if (ipSet.get(ip)) {
            return;
        }

        ipSet.set(ip);
        uniqueIpCount++;
    }
}

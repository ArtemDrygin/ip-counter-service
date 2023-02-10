package ip.counter;

import java.util.BitSet;

public class UniqueIpCounter {
    private final BitSet positiveIpSet = new BitSet(Integer.MAX_VALUE);
    private final BitSet negativeIpSet = new BitSet(Integer.MAX_VALUE);
    private long uniqueIpCount = 0L;

    public long getUniqueIpCount() {
        return uniqueIpCount;
    }

    public void addIp(Integer ip) {
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

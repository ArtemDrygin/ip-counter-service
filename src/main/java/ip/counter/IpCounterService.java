package ip.counter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IpCounterService {
    public static final IpCounterService INSTANCE = new IpCounterService();

    private IpCounterService() {
    }

    public Long countIPs(String filepath) {
        var ipCounter = new UniqueIpCounter();

        readFile(filepath, ipCounter);

        return ipCounter.getUniqueIpCount();
    }

    private void readFile(String filepath, UniqueIpCounter counter) {
        var path = Path.of(filepath);

        if (Files.notExists(path)) {
            throw new RuntimeException("Cannot find a file.");
        }

        try (var ips = Files.lines(path)) {
            ips.takeWhile(ignore -> !counter.isMaxUniqueIpCount()).forEach(counter::addIp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

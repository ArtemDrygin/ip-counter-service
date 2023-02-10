package ip.counter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws InterruptedException {
        String filePath = getFilePath(args);

        LOG.info("start");
        var start = LocalDateTime.now();

        var uniqueIpCount = IpCounterService.INSTANCE.countIPs(filePath);

        var end = LocalDateTime.now();

        var duration = Duration.between(start, end);
        String itTakes = String.format(
                "%d:%02d:%02d",
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart()
        );

        LOG.info("end");
        var endMess = "It takes: " + itTakes;
        LOG.info(endMess);

        var successMessage = String.format("File contains %d unique ips", uniqueIpCount);
        LOG.info(successMessage);
    }

    private static String getFilePath(String[] args) throws InterruptedException {
        if (args.length == 2 && "-filepath".equals(args[0])) {
            return args[1];
        }

        LOG.info("Cannot find program argument '-filepath'");

        try (var scanner = new Scanner(System.in)) {
            LOG.info("Enter filepath to the text file with IPs...");

            for (int i = 0; i < 180; i++) {
                if (scanner.hasNextLine()) {
                    return scanner.nextLine().trim();
                } else {
                    Thread.sleep(1000L);
                }
            }
        }

        LOG.warning("Time to enter filepath is over");
        System.exit(1);

        throw new RuntimeException("unreachable state");
    }
}

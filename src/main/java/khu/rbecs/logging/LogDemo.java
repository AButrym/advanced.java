package khu.rbecs.logging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

@Slf4j(topic = "LogDemo")
public class LogDemo {

    private static final Logger log1 = LoggerFactory.getLogger(LogDemo.class);
    private static final Logger log2 = LoggerFactory.getLogger("LogDemo");

    public static void main(String... args) {
        int i = new Random().nextInt(100);
        int j = new Random().nextInt(100);
        log.trace("trace message i = " + getI(i));
        log.atTrace().log(() -> "trace message i = " + getI(i));
        log.debug("debug message i = {}, j = {}", i, j);
        log.info("info message");
        log.warn("warn message");
        log.error("error message");
//        var julLog = java.util.logging.Logger.getGlobal();
//        julLog.logrb(Level.INFO, "LogDemo", "main", "info message");
//        julLog.info("java.util.logging.Logger.getGlobal().info message");
    }
    static int getI(int i) {
        System.out.println(">> getI was invoked");
        return i;
    }
}

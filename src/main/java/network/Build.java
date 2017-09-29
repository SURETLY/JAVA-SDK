package network;


import com.sun.javafx.tools.packager.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Build {

    public static void log(String tag, String message) {
        Logger logger = LoggerFactory.getLogger(tag);
        logger.info(message);
//        System.out.println(message);
    }
}

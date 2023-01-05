package threads.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomConnection implements Runnable {
    private final static Logger LOGGER = LogManager.getLogger(CustomConnection.class);

    @Override
    public void run() {
        LOGGER.info("This is printed from a Connection instance with run()");
    }
}

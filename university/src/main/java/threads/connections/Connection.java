package threads.connections;

import com.solvd.laba.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection implements Runnable {
    private final static Logger LOGGER = LogManager.getLogger(Connection.class);

    @Override
    public void run() {
        LOGGER.info("This is printed from a Connection instance with run()");
    }
}

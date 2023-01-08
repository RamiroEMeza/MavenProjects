package threads.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

public class ConnectionPool {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private ArrayBlockingQueue<CustomConnection> customConnectionPooll;
    private static final int INITIAL_POOL_SIZE = 5;
    private int currentCreatedConnections = -1;
    private static final int LIMIT_OF_THREADS = 5;

    public void printAvailableConnections() {
        LOGGER.info("ConnectionPool has: " + customConnectionPooll.size() + " available connections");
    }

    ConnectionPool() {
        customConnectionPooll = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
    }

    public synchronized CustomConnection connect() throws InterruptedException {//this should wait, read thread safe collection
        while (customConnectionPooll.isEmpty() && currentCreatedConnections == LIMIT_OF_THREADS - 1) {
            LOGGER.info("One or more threads waiting for a connection ********");
            Thread.sleep(1000);
        }

        if (customConnectionPooll.isEmpty() && currentCreatedConnections < LIMIT_OF_THREADS) {
            currentCreatedConnections++;
            LOGGER.info("Connection created and provided (" + currentCreatedConnections + ")");
            return new CustomConnection();
        } else if (!customConnectionPooll.isEmpty()) {
            LOGGER.info("Connection found and provided");
            return customConnectionPooll.poll();
        }
        return null;
    }

    public void disconnect(CustomConnection customConnection) {
        LOGGER.info("A thread has disconnected");
        if (customConnection != null) {
            customConnectionPooll.add(customConnection);
        }
    }

}

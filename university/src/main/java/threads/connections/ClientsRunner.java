package threads.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

public class ClientsRunner {
    private final static Logger LOGGER = LogManager.getLogger(ClientsRunner.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        LOGGER.info("START");
        ConnectionPool connectionP = new ConnectionPool();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<CustomConnection> c1 = executorService.submit(connectionP::connect);
        Future<CustomConnection> c2 = executorService.submit(connectionP::connect);
        Future<CustomConnection> c3 = executorService.submit(connectionP::connect);
        Future<CustomConnection> c4 = executorService.submit(connectionP::connect);
        Future<CustomConnection> c5 = executorService.submit(connectionP::connect);
        Thread.sleep(4000);

        if (c1.isDone() && !c1.isCancelled()) {
            LOGGER.info("c1 says: I have a connection " + c1.get());
        }

        if (c2.isDone() && !c2.isCancelled()) {
            LOGGER.info("c2 says: I have a connection " + c2.get());
        }

        if (c3.isDone() && !c3.isCancelled()) {
            LOGGER.info("c3 says: I have a connection " + c3.get());
        }

        if (c4.isDone() && !c4.isCancelled()) {
            LOGGER.info("c4 says: I have a connection " + c4.get());
        }

        if (c5.isDone() && !c5.isCancelled()) {
            LOGGER.info("c5 says: I have a connection " + c5.get());
        }
        Thread.sleep(2000);
        Future<CustomConnection> c6 = executorService.submit(connectionP::connect);
        Future<CustomConnection> c7 = executorService.submit(connectionP::connect);

        Thread.sleep(8000);
        connectionP.disconnect(c1.get());
        connectionP.printAvailableConnections();


        Thread.sleep(6000);
        connectionP.printAvailableConnections();
        connectionP.disconnect(c2.get());
        connectionP.printAvailableConnections();

        Thread.sleep(4000);
        if (c6.isDone() && !c6.isCancelled()) {
            LOGGER.info("c6 says: I have a connection " + c6.get());
        }

        if (c7.isDone() && !c7.isCancelled()) {
            LOGGER.info("c7 says: I have a connection " + c7.get());
        }

        connectionP.printAvailableConnections();

        LOGGER.info("END");
    }
}

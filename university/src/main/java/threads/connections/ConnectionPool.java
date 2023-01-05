package threads.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ConnectionPool {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    //thread safe collection
    private ArrayBlockingQueue<Connection> connectionPooll;
    private static final int INITIAL_POOL_SIZE = 5;
    private int currentCreatedConnections = -1;
    private static final int LIMIT_OF_THREADS = 5;
    private ArrayList<Long> currentWaitingThreads;


    ConnectionPool() {
        connectionPooll = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
        currentWaitingThreads = new ArrayList<>();
    }

    public synchronized Connection connect() throws InterruptedException {//this should wait, read thread safe collection
        while (connectionPooll.isEmpty() && currentCreatedConnections == LIMIT_OF_THREADS - 1) {
            LOGGER.info("One or more threads waiting for a connection ********");
            Thread.sleep(1000);
        }

        if (connectionPooll.isEmpty() && currentCreatedConnections < LIMIT_OF_THREADS) {
            currentCreatedConnections++;
            LOGGER.info("Connection created and provided (" + currentCreatedConnections + ")");
            return new Connection();
        } else if (!connectionPooll.isEmpty()) {
            LOGGER.info("Connection found and provided");
            return connectionPooll.poll();
        }
        return null;
    }

    public void disconnect(Connection connection) {
        LOGGER.info("A thread has disconnected");
        if (connection != null) {
            connectionPooll.add(connection);
        }
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        LOGGER.info("START");
        ConnectionPool connectionP = new ConnectionPool();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<Connection> c1 = executorService.submit(connectionP::connect);
        Future<Connection> c2 = executorService.submit(connectionP::connect);
        Future<Connection> c3 = executorService.submit(connectionP::connect);
        Future<Connection> c4 = executorService.submit(connectionP::connect);
        Future<Connection> c5 = executorService.submit(connectionP::connect);
        Future<Connection> c6 = executorService.submit(connectionP::connect);
        Future<Connection> c7 = executorService.submit(connectionP::connect);
        Thread.sleep(3000);

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

        if (c6.isDone() && !c6.isCancelled()) {
            LOGGER.info("c6 says: I have a connection " + c6.get());
        }

        if (c7.isDone() && !c7.isCancelled()) {
            LOGGER.info("c7 says: I have a connection " + c7.get());
        }

        Thread.sleep(8000);
        connectionP.disconnect(c1.get());


        Thread.sleep(6000);
        connectionP.disconnect(c2.get());

        if (c6.isDone() && !c6.isCancelled()) {
            LOGGER.info("c6 says: I have a connection " + c6.get());
        }

        Thread.sleep(4000);
        if (c7.isDone() && !c7.isCancelled()) {
            LOGGER.info("c7 says: I have a connection " + c7.get());
        }

        if (c6.isDone() && !c6.isCancelled()) {
            LOGGER.info("c6 says: I have a connection " + c6.get());
        }

        LOGGER.info("END");

        //*********Kinda works:

//        CompletableFuture<Connection> c1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                return connectionP.connect();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        CompletableFuture<Connection> c2 = CompletableFuture.supplyAsync(() -> {
//            try {
//                return connectionP.connect();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        CompletableFuture<Connection> c3 = CompletableFuture.supplyAsync(() -> {
//            try {
//                return connectionP.connect();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        CompletableFuture<Connection> c4 = CompletableFuture.supplyAsync(() -> {
//            try {
//                return connectionP.connect();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        CompletableFuture<Connection> c5 = CompletableFuture.supplyAsync(() -> {
//            try {
//                return connectionP.connect();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        Thread.sleep(2000);
//
//        if (c1.isDone() && !c1.isCancelled()) {
//            LOGGER.info("c1 says: I have a connection----------" + c1.get());
//        }
//        if (c2.isDone() && !c2.isCancelled()) {
//            LOGGER.info("c2 says: I have a connection----------" + c2.get());
//            // connectionP.disconnect(c2.get());
//        }
//        if (c3.isDone() && !c3.isCancelled()) {
//            LOGGER.info("c3 says: I have a connection----------" + c3.get());
//            // connectionP.disconnect(c3.get());
//        }
//        if (c4.isDone() && !c4.isCancelled()) {
//            LOGGER.info("c4 says: I have a connection----------" + c4.get());
//            // connectionP.disconnect(c4.get());
//        }
//        if (c5.isDone() && !c5.isCancelled()) {
//            LOGGER.info("c5 says: I have a connection----------" + c5.get());
//            //connectionP.disconnect(c5.get());
//        }
//
//
//        CompletableFuture<Connection> c6 = CompletableFuture.supplyAsync(() -> {
//            try {
//                return connectionP.connect();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        CompletableFuture<Connection> c7 = CompletableFuture.supplyAsync(() -> {
//            try {
//                return connectionP.connect();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        Thread.sleep(2000);
//        if (c1.isDone() && !c1.isCancelled()) {
//            connectionP.disconnect(c1.get());
//        }
//        Thread.sleep(2000);
//
//
//        if (c6.isDone() && !c6.isCancelled()) {
//            LOGGER.info("c6 says: I have a connection----------" + c6.get());
//            //connectionP.disconnect(c6.get());
//        }
//
//        if (c7.isDone() && !c7.isCancelled()) {
//            LOGGER.info("c7 says: I have a connection----------" + c7.get());
//            //connectionP.disconnect(c6.get());
//        }

    }

}

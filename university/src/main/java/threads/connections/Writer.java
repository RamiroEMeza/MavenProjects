package threads.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Writer implements Runnable {
    private int id;
    private final static Logger LOGGER = LogManager.getLogger(Writer.class);

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    Writer(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LocalTime time = LocalTime.now();
        LOGGER.info("This is written by a different thread ( " + this.id + " )" + time.toString());
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> message = CompletableFuture.supplyAsync(() -> {
            if (Writer.getRandomInt(1, 8) % 2 == 0) {
                return "Info is even";
            }
            return "Info is odd";
        });
        if (message.isDone() && !message.isCancelled()) {
            LOGGER.info("The completable future says: " + message.get() + "----------");
        } else {
            LOGGER.info("The completable future says: " + "***********");
        }

        ExecutorService es = Executors.newFixedThreadPool(5);
        ArrayList<Writer> writers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            writers.add(new Writer(i));
        }
        writers.forEach(es::execute);
//        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            LOGGER.info("This is written without threads");
//        }
        es.shutdown();//this guarantees that all logic is executed
        LocalTime time = LocalTime.now();
        LOGGER.info("This is written by main " + time.toString());

        if (message.isDone() && !message.isCancelled()) {
            LOGGER.info("The completable future says: " + message.get() + "----------");
        }
    }
}
